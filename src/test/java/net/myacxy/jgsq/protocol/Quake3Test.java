package net.myacxy.jgsq.protocol;

import net.myacxy.jgsq.factory.GameFactory;
import net.myacxy.jgsq.utils.Utilities;
import net.myacxy.jgsq.model.Game;
import net.myacxy.jgsq.model.GameServer;
import net.myacxy.jgsq.model.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class Quake3Test {

    private Game jk2;
    private Quake3 protocol;
    private GameServer server;

    @Before
    public void setUp()
    {
        GameFactory gf = new GameFactory();
        gf.loadConfig(Utilities.getResourceAsStream("games.conf.json"));
        jk2 = gf.getGame("JK2");
        protocol = new Quake3(jk2);
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
        server = new GameServer(jk2, protocol);
        server.connect("myacxy.net", 28070);
        if(protocol.query("getstatus", false) != null)
        {
            assertEquals(server.parameters.size(), 0);

            server.update();
            assertEquals(server.hostName, "v1.03");
            assertTrue(server.parameters.size() > 0);
            assertEquals(server.hostName, server.parameters.get("sv_hostname"));
        }
    }

    @Test
    public void queryWithFakeResponse()
    {
        server = new GameServer(jk2, "85.25.149.26", 28070, protocol);

        try {
            URL resource = Utilities.class.getClassLoader().getResource("response.example");
            Path path = Paths.get(resource.getPath().substring(1));
            protocol.response = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        protocol.updateServerInfo(server);
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