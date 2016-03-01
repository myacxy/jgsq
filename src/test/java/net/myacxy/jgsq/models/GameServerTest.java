package net.myacxy.jgsq.models;

import net.myacxy.jgsq.factories.GameFactory;
import net.myacxy.jgsq.protocols.Quake3;
import net.myacxy.jgsq.utils.Utilities;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class GameServerTest {

    private GameServer q3;

    @Before
    public void setUp()
    {
        GameFactory gf = new GameFactory();
        Game jk2 = gf.getGame("JK2");
        gf.loadConfig(Utilities.getResourceAsStream("games.conf.json"));
        String fakeResponse = "";

        try {
            URL resource = Utilities.class.getClassLoader().getResource("jk2.response");
            Path path = Paths.get(resource.getPath().substring(1));
            fakeResponse = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Quake3 protocol = new FakeProtocol(jk2, fakeResponse);
        q3 = new GameServer(jk2, protocol);
        q3.connect("217.160.126.151", 28070);
        protocol.updateServerInfo(q3);
    } // setUp

    @Test
    public void toJson()
    {
        String json = "{\n" +
                "  \"current_clients\": 5,\n" +
                "  \"ip_address\": \"217.160.126.151\",\n" +
                "  \"online\": true,\n" +
                "  \"password_protected\": false,\n" +
                "  \"map_name\": \"ffa_bespin\",\n" +
                "  \"max_clients\": 24,\n" +
                "  \"host_name\": \"\\u0001\\u0001-\\u003c(TFJ)\\u003e-FFA1.03\",\n" +
                "  \"colored_host_name\": \"\\u0001\\u0001^5-\\u003c(TFJ)\\u003e-^4FFA^71.03^7\",\n" +
                "  \"players\": [\n" +
                "    {\n" +
                "      \"name\": \"zedi.bot\",\n" +
                "      \"colored_name\": \"^0zedi^1.^5bot\",\n" +
                "      \"score\": 10,\n" +
                "      \"deaths\": 0,\n" +
                "      \"ping\": 0,\n" +
                "      \"time\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"acxy.bot\",\n" +
                "      \"colored_name\": \"^1acxy^1.^6bot\",\n" +
                "      \"score\": 11,\n" +
                "      \"deaths\": 0,\n" +
                "      \"ping\": 0,\n" +
                "      \"time\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"nbv.bot\",\n" +
                "      \"colored_name\": \"^2nbv^1.^7bot\",\n" +
                "      \"score\": 12,\n" +
                "      \"deaths\": 0,\n" +
                "      \"ping\": 0,\n" +
                "      \"time\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"majin.bot\",\n" +
                "      \"colored_name\": \"^3majin^1.^8bot\",\n" +
                "      \"score\": 13,\n" +
                "      \"deaths\": 0,\n" +
                "      \"ping\": 0,\n" +
                "      \"time\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"murx.bot\",\n" +
                "      \"colored_name\": \"^4murx^1.^9bot\",\n" +
                "      \"score\": 13,\n" +
                "      \"deaths\": 0,\n" +
                "      \"ping\": 0,\n" +
                "      \"time\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"port\": 28070,\n" +
                "  \"parameters\": {\n" +
                "    \"bot_minplayers\": \"0\",\n" +
                "    \"capturelimit\": \"4\",\n" +
                "    \"dmflags\": \"0\",\n" +
                "    \"duel_fraglimit\": \"1\",\n" +
                "    \"fraglimit\": \"0\",\n" +
                "    \"g_blueteam\": \"Empire\",\n" +
                "    \"g_duelWeaponDisable\": \"65531\",\n" +
                "    \"g_forceBasedTeams\": \"1\",\n" +
                "    \"g_forceRegenTime\": \"0\",\n" +
                "    \"g_forcepowerdisable\": \"0\",\n" +
                "    \"g_gametype\": \"0\",\n" +
                "    \"g_jediVmerc\": \"0\\r\",\n" +
                "    \"g_maxForceRank\": \"200\",\n" +
                "    \"g_maxGameClients\": \"0\",\n" +
                "    \"g_maxHolocronCarry\": \"5\",\n" +
                "    \"g_needpass\": \"0\",\n" +
                "    \"g_privateDuel\": \"1\",\n" +
                "    \"g_redteam\": \"Rebellion\",\n" +
                "    \"g_saberLocking\": \"1\",\n" +
                "    \"g_weapondisable\": \"65531\",\n" +
                "    \"gamename\": \"basejk\",\n" +
                "    \"mapname\": \"ffa_bespin\",\n" +
                "    \"protocol\": \"15\",\n" +
                "    \"sv_allowAnonymous\": \"1\",\n" +
                "    \"sv_allowDownload\": \"0\",\n" +
                "    \"sv_floodProtect\": \"1\",\n" +
                "    \"sv_hostname\": \"\\u0001\\u0001^5-\\u003c(TFJ)\\u003e-^4FFA^71.03^7\",\n" +
                "    \"sv_maxPing\": \"500\",\n" +
                "    \"sv_maxRate\": \"20000\",\n" +
                "    \"sv_maxclients\": \"24\",\n" +
                "    \"sv_minPing\": \"0\",\n" +
                "    \"sv_privateClients\": \"0\",\n" +
                "    \"timelimit\": \"0\",\n" +
                "    \"version\": \"JK2MP: v1.03b linux-i386 May 15 2002\"\n" +
                "  },\n" +
                "  \"ping\": 0\n" +
                "}";

        assertEquals(json, q3.toJson());
    } // toJson

    private class FakeProtocol extends Quake3
    {
        public FakeProtocol(Game game, String response) {
            super(game);
            this.response = response;
        }
    }
} // GameServerTest
