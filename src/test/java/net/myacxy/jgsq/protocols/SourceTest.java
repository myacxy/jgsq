package net.myacxy.jgsq.protocols;

import net.myacxy.jgsq.factories.GameFactory;
import net.myacxy.jgsq.helpers.ServerResponseStatus;
import net.myacxy.jgsq.models.Game;
import net.myacxy.jgsq.models.GameServer;
import net.myacxy.jgsq.utils.ResourceUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SourceTest {

    private Game csgo;
    private Source sourceProtocol;
    private GameServer server;

    @Before
    public void setUp() {
        GameFactory gf = new GameFactory();
        gf.loadConfig(ResourceUtil.getResourceAsStream("games.conf.json"));
        csgo = gf.getGame("csgo");
        sourceProtocol = new Source(csgo);
    }

    @After
    public void tearDown() {
        server.disconnect();
    }

    @Test
    //@Ignore("avoid querying the server too frequently")
    public void queryRealServer() {
        server = new GameServer(csgo, sourceProtocol);
        server.connect("198.23.75.134", 27015);
        if (sourceProtocol.update() == ServerResponseStatus.OK) {
            assertEquals(server.parameters.size(), 0);

            sourceProtocol.updateServerInfo(server);

            assertEquals(server.hostName, "Clanwarz : San Jose - 100 Tickrate");
            assertTrue(server.parameters.size() > 0);
            assertEquals(server.hostName, server.parameters.get("sv_hostname"));
        }
    }
} // SourceTest
