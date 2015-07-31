package net.myacxy.jgsq.protocols;

import net.myacxy.jgsq.factories.GameFactory;
import net.myacxy.jgsq.helpers.ServerResponseStatus;
import net.myacxy.jgsq.utils.Utilities;
import net.myacxy.jgsq.models.Game;
import net.myacxy.jgsq.models.GameServer;
import net.myacxy.jgsq.models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class Quake3Test {

    private Game jk2;
    private Quake3 quake3Protocol;
    private GameServer server;

    @Before
    public void setUp()
    {
        GameFactory gf = new GameFactory();
        gf.loadConfig(Utilities.getResourceAsStream("games.conf.json"));
        jk2 = gf.getGame("JK2");
        quake3Protocol = new Quake3(jk2);
    }

    @After
    public void tearDown()
    {
        server.disconnect();
    }

    @Test
    //@Ignore("avoid querying the server too frequently")
    public void queryRealServer()
    {
        server = new GameServer(jk2, quake3Protocol);
        server.connect("myacxy.net", 28070);
        if(quake3Protocol.query("getstatus") == ServerResponseStatus.OK)
        {
            assertEquals(server.parameters.size(), 0);

            server.update();
            assertEquals(server.hostName, "v1.03");
            assertTrue(server.parameters.size() > 0);
            assertEquals(server.hostName, server.parameters.get("sv_hostname"));
        }
    }

    @Test
    public void queryWrongAddress()
    {
        server = new GameServer(jk2, quake3Protocol);
        // valid address + wrong port
        assertEquals(server.connect("myacxy.net", 28071), ServerResponseStatus.Connected);
        assertNotEquals(server.update(), ServerResponseStatus.OK);

        // valid address + port out of range
        assertEquals(server.connect("myacxy.net", 99999), ServerResponseStatus.IllegalArgumentException);
        assertEquals(server.update(), ServerResponseStatus.IllegalArgumentException);

        // invalid address + valid port
        assertEquals(server.connect("myacxy.netx", 28070), ServerResponseStatus.UnknownHostException);
    }

    @Test
    public void queryWithFakeResponse()
    {
        server = new GameServer(jk2, "85.25.149.26", 28070, quake3Protocol);

        try {
            URL resource = Utilities.class.getClassLoader().getResource("response.example");
            Path path = Paths.get(resource.getPath().substring(1));
            quake3Protocol.response = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.connect();
        quake3Protocol.updateServerInfo(server);
        assertTrue(server.hostName.contains("TFJ"));
        assertEquals(server.players.size(), 5);

        boolean isZediOnline = false;
        for(Player player : server.players)
        {
            if(player.name.equals("zedi.bot"))
            {
                isZediOnline = true;
                assertTrue(player.score == 10);
                assertTrue(player.ping == 0);
                break;
            }
        }
        assertTrue(isZediOnline);
    }
} // Quake3Test