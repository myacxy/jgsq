package net.myacxy.jgsq.protocol;

import net.myacxy.jgsq.misc.Utilities;
import net.myacxy.jgsq.model.Game;
import net.myacxy.jgsq.model.GameServer;
import net.myacxy.jgsq.model.Player;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Quake3 extends BaseProtocol
{
    private Pattern pattern;
    private final String PLAYER_REGEX = "(?<score>.+) (?<ping>.+) \"(?<name>.+)\"";

    public Quake3(Game game)
    {
        super(game);
        pattern = Pattern.compile(PLAYER_REGEX);
    }

    @Override
    public void updateServerInfo(GameServer server) {
        if(response == null) return;

        String tmp = new String(response);
        server.players = new ArrayList<>();

        if(tmp.contains("disconnect"))
        {
            server.isOnline = false;
            return;
        }

        String[] lines = tmp.split("\\n");

        System.out.println(lines[1]);

        // parameters
        StringTokenizer tokens = new StringTokenizer(lines[1],"\\");
        while(tokens.hasMoreTokens())
        {
            String key = tokens.nextToken();
            String value = tokens.nextToken();
            parameters.put(key, value);
        }

        // players
        for(int i = 2; i < lines.length - 1; i++)
        {
            if(lines[i].length() == 0) continue;
            server.players.add(parsePlayer(lines[i]));
            System.out.println(server.players.get(i - 2));
        }

        updateParameters(server);
    } // updateServerInfo

    private void updateParameters(GameServer server)
    {
        server.parameters = parameters;

        server.coloredHostName = parameters.get("sv_hostname");
        server.hostName = Utilities.removeColorCode(server.coloredHostName);
        server.map = parameters.get("mapname");
        server.isPasswordProtected = Boolean.parseBoolean(parameters.get("g_needpass"));
        server.maxClients = Integer.parseInt(parameters.get("sv_maxclients"));
        server.currentClients = server.players.size();
    } // updateParameters

    protected Player parsePlayer(String line)
    {
        Matcher matcher = pattern.matcher(line);

        if(matcher.find())
        {
            String coloredName = matcher.group("name");
            String name = Utilities.removeColorCode(coloredName);
            int score = Integer.parseInt(matcher.group("score"));
            int ping =Integer.parseInt(matcher.group("ping"));
            return new Player(name, coloredName, score, ping);
        }
        return null;
    } // parsePlayer
} // Quake3
