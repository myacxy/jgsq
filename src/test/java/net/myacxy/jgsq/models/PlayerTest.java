package net.myacxy.jgsq.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player zediBot;

    @Before
    public void setUp()
    {
        zediBot = new Player("zedi.bot", "^0zedi^1.^5bot", 10, 0);
    }
    @Test
    public void toJson()
    {
        String json = "{\n" +
                "  \"name\": \"zedi.bot\",\n" +
                "  \"colored_name\": \"^0zedi^1.^5bot\",\n" +
                "  \"score\": 10,\n" +
                "  \"deaths\": 0,\n" +
                "  \"ping\": 0,\n" +
                "  \"time\": 0\n" +
                "}";

        assertEquals(json, zediBot.toJson());
    }
}