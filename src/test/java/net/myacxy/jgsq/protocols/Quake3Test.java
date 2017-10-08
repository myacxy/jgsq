package net.myacxy.jgsq.protocols;

import net.myacxy.jgsq.factories.GameFactory;
import net.myacxy.jgsq.helpers.ServerResponseStatus;
import net.myacxy.jgsq.models.Game;
import net.myacxy.jgsq.models.GameServer;
import net.myacxy.jgsq.models.Player;
import net.myacxy.jgsq.utils.ResourceUtil;
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
    private Quake3 quake3Protocol;
    private GameServer server;

    @Before
    public void setUp() {
        GameFactory gf = new GameFactory();
        gf.loadConfig(ResourceUtil.getResourceAsStream("games.conf.json"));
        jk2 = gf.getGame("JK2");
        quake3Protocol = new Quake3(jk2);
    }

    @After
    public void tearDown() {
        server.disconnect();
    }

    @Test
    //@Ignore("avoid querying the server too frequently")
    public void queryRealServer() {
        server = new GameServer(jk2, quake3Protocol);
        server.connect("myacxy.net", 28070);
        if (quake3Protocol.update() == ServerResponseStatus.OK) {
            assertEquals(server.parameters.size(), 0);

            quake3Protocol.updateServerInfo(server);

            assertEquals("v1.03", server.hostName);
            assertTrue(server.parameters.size() > 0);
            assertEquals(server.parameters.get("sv_hostname"), server.hostName);
        }
    }

    @Test
    //@Ignore("avoid querying the server too frequently")
    public void queryTfj() {
        server = new GameServer(jk2, quake3Protocol);
        server.connect("85.25.149.26", 28070);
        if (quake3Protocol.update() == ServerResponseStatus.OK) {
            assertEquals(server.parameters.size(), 0);

            quake3Protocol.updateServerInfo(server);

            assertEquals("\u0001\u0001^5-<(TFJ)>-^4FFA^71.03^7", server.coloredHostName);
            assertTrue(server.parameters.size() > 0);
            assertEquals(server.parameters.get("sv_hostname"), server.hostName);
        }
    }

    @Test
    public void queryWrongAddress() {
        server = new GameServer(jk2, quake3Protocol);
        // valid address + wrong port
        assertEquals(ServerResponseStatus.CONNECTED, server.connect("myacxy.net", 28071));
        assertNotEquals(ServerResponseStatus.OK, server.update());

        // valid address + port out of range
        assertEquals(ServerResponseStatus.ILLEGAL_ARGUMENT_EXCEPTION, server.connect("myacxy.net", 99999));
        assertEquals(ServerResponseStatus.ILLEGAL_ARGUMENT_EXCEPTION, server.update());

        // invalid address + valid port
        assertEquals(ServerResponseStatus.UNKNOWN_HOST_EXCEPTION, server.connect("myacxy.netx", 28070));
    }

    @Test
    public void queryWithFakeResponse() {
        server = new GameServer(jk2, "85.25.149.26", 28070, quake3Protocol);

        try {
            URL resource = getClass().getClassLoader().getResource("jk2.response");
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
        for (Player player : server.players) {
            if (player.name.equals("zedi.bot")) {
                isZediOnline = true;
                assertTrue(player.score == 10);
                assertTrue(player.ping == 0);
                break;
            }
        }
        assertTrue(isZediOnline);
    }
} // Quake3Test
