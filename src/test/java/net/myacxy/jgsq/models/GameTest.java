package net.myacxy.jgsq.models;

import net.myacxy.jgsq.factories.GameFactory;
import net.myacxy.jgsq.utils.ResourceUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game q3;

    @Before
    public void setUp() {
        GameFactory gf = new GameFactory();
        gf.loadConfig(ResourceUtil.getResourceAsStream("games.conf.json"));
        q3 = gf.getGame("Q3");
    }

    @Test
    public void toJson() {
        String json = "{\n" +
                "  \"game\": \"Quake III Arena\",\n" +
                "  \"game_alternative\": \"Quake 3\",\n" +
                "  \"game_abbreviation\": \"Q3\",\n" +
                "  \"server_protocol\": \"QUAKE3\",\n" +
                "  \"default_port\": 28960\n" +
                "}";

        assertEquals(json, q3.toJson());
    }
}
