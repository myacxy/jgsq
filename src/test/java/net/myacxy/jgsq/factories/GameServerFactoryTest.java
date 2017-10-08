package net.myacxy.jgsq.factories;

import net.myacxy.jgsq.models.Game;
import net.myacxy.jgsq.models.GameServer;
import net.myacxy.jgsq.protocols.Quake3;
import net.myacxy.jgsq.utils.ResourceUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameServerFactoryTest {

    private GameFactory gf;
    private GameServerFactory gsf;

    @Before
    public void setUp() {
        gf = new GameFactory();
        gf.loadConfig(ResourceUtil.getResourceAsStream("games.conf.json"));
        gsf = new GameServerFactory();
    }


    @Test
    public void getGameServer() {
        Game q3 = gf.getGame("Q3");
        GameServer quake3Server = gsf.getGameServer(q3);

        assertTrue(quake3Server.getProtocol() instanceof Quake3);
    }
} // GameServerFactoryTest
