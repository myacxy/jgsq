package net.myacxy.jgsq.factory;

import net.myacxy.jgsq.utils.Utilities;
import net.myacxy.jgsq.model.Game;
import net.myacxy.jgsq.model.GameServer;
import net.myacxy.jgsq.protocol.Quake3;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameServerFactoryTest
{

    private GameFactory gf;
    private GameServerFactory gsf;

    @Before
    public void setUp()
    {
        gf  = new GameFactory();
        gf.loadConfig(Utilities.getResourceAsStream("games.conf.json"));
        gsf = new GameServerFactory();
    }


    @Test
    public void getGameServer()
    {
        Game q3 = gf.getGame("Q3");
        GameServer quake3Server = gsf.getGameServer(q3);

        assertTrue(quake3Server.getProtocol() instanceof Quake3);
    }
} // GameServerFactoryTest