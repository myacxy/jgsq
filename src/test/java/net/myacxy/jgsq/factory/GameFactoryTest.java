package net.myacxy.jgsq.factory;

import net.myacxy.jgsq.utils.Utilities;
import net.myacxy.jgsq.model.Game;
import net.myacxy.jgsq.model.ServerProtocolType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameFactoryTest
{
    private GameFactory factory;

    @Before
    public void setUp()
    {
        factory = new GameFactory();
    }

    @Test
    public void testGetGame()
    {
        loadConfig();

        Game jk2 = factory.getGame("JK2");
        Game jediKnight2 = factory.getGame("Jedi Knight 2");
        Game swjk2jo = factory.supportedGames.get("Star Wars Jedi Knight 2: Jedi Outcast");
        Game q3 = factory.getGame("Q3");
        Game callOfDuty = factory.getGame("Call of Duty");
        Game cod = factory.getGame("cod");
        Game CoD = factory.getGame("CoD");

        // compare jk2, jediKnight2 and swjk2jo
        assertEquals(jk2.alternativeName, "Jedi Knight 2");
        assertEquals(jk2.alternativeName, jediKnight2.alternativeName);
        assertEquals(jk2.name, jediKnight2.name);
        assertEquals(jk2, jediKnight2);
        assertEquals(jk2, swjk2jo);

        // check if all use the same server protocol (Quake3)
        assertEquals(jk2.serverProtocolType, q3.serverProtocolType);
        assertEquals(jk2.serverProtocolType, callOfDuty.serverProtocolType);

        // check ports
        assertEquals(callOfDuty.defaultPort, 28960);
        assertEquals(jediKnight2.defaultPort, 28070);
        assertEquals(callOfDuty.defaultPort, q3.defaultPort);

        // check case
        assertNotNull(cod);
        assertEquals(cod, CoD);
        assertEquals(cod, callOfDuty);
    }

    @Test
    public void loadConfig()
    {
        String fileName = "games.conf.json";
        factory.supportedGames = factory.loadConfig(Utilities.getResourceAsStream(fileName));

        assertEquals(factory.supportedGames.size(), 3);

        Game jk2 = factory.supportedGames.get("Star Wars Jedi Knight 2: Jedi Outcast");
        Game cod = factory.supportedGames.get("Call of Duty");

        assertEquals(jk2.abbreviatedName, "JK2");
        assertEquals(jk2.serverProtocolType, ServerProtocolType.Quake3);
        assertEquals(cod.defaultPort, 28960);
    }

    @Test
    public void getSupportedGames()
    {
        loadConfig();

        assertTrue(factory.getSupportedGames(ServerProtocolType.Ase).size() == 0);
        assertTrue(factory.getSupportedGames(ServerProtocolType.Quake3).size() == 3);
    }
} // GameFactoryTest