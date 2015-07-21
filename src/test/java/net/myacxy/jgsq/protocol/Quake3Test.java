package net.myacxy.jgsq.protocol;

import net.myacxy.jgsq.factory.GameFactory;
import net.myacxy.jgsq.misc.Utilities;
import net.myacxy.jgsq.model.Game;
import net.myacxy.jgsq.model.GameServer;
import net.myacxy.jgsq.model.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class Quake3Test {

    private Quake3 protocol;
    private GameServer server;

    @Before
    public void setUp()
    {
        GameFactory gf = new GameFactory();
        gf.loadConfig(Utilities.getAbsoluteResourceFilePath("games.conf.json"));
        Game jk2 = gf.getGame("JK2");
        protocol = new Quake3(jk2);
    }

    @After
    public void tearDown()
    {
        server.disconnect();
    }

    @Test
    @Ignore("avoid querying the server too frequently")
    public void queryRealServer()
    {
        server = new GameServer(protocol);
        server.connect("myacxy.net", 28070);
        byte[] response = protocol.query("getstatus", false);
        assertNotNull(response);
        assertEquals(server.parameters.size(), 0);

        protocol.updateServerInfo(server);
        assertEquals(server.hostName, "v1.03");
        assertTrue(server.parameters.size() > 0);
        assertEquals(server.hostName, server.parameters.get("sv_hostname"));
    }

    @Test
    public void queryWithFakeResponse()
    {
        server = new GameServer("85.25.149.26", 28070, protocol);

        try {
            protocol.response = Files.readAllBytes(Utilities.getAbsoluteResourceFilePath("response.example"));
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