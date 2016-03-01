package net.myacxy.jgsq.protocols;

import net.myacxy.jgsq.helpers.ServerResponseStatus;
import net.myacxy.jgsq.utils.Utilities;
import net.myacxy.jgsq.models.Game;
import net.myacxy.jgsq.models.GameServer;
import net.myacxy.jgsq.models.Player;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Quake3 extends BaseProtocol
{
    private Pattern pattern;
    private final String PLAYER_REGEX = "(\\d+) (\\d+) \"(.*)\"";

    public Quake3(Game game)
    {
        super(game);
        pattern = Pattern.compile(PLAYER_REGEX);
    }

    @Override
    public ServerResponseStatus update() {
        return query("getstatus");
    }

    @Override
    public void updateServerInfo(GameServer server) {
        if(response == null || response.contains("disconnect"))
        {
            server.isOnline = false;
            return;
        }

        server.players = new ArrayList<>();
        String[] lines = response.split("\\n");

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

        server.isOnline = true;
        server.ipAddress = ipAddress.getHostAddress();
        server.port = port;
        server.ping = (int) deltaTime;

        server.coloredHostName = parameters.get("sv_hostname");
        server.hostName = Utilities.removeColorCode(server.coloredHostName);
        server.mapName = parameters.get("mapname");
        server.isPasswordProtected = Integer.parseInt(parameters.get("g_needpass")) == 1;
        server.maxClients = Integer.parseInt(parameters.get("sv_maxclients"));
        server.currentClients = server.players.size();
    } // updateParameters

    protected Player parsePlayer(String line)
    {
        Matcher matcher = pattern.matcher(line);

        if(matcher.find())
        {
            int score = Integer.parseInt(matcher.group(1));
            int ping = Integer.parseInt(matcher.group(2));
            String coloredName = matcher.group(3);
            String name = Utilities.removeColorCode(coloredName);
            return new Player(name, coloredName, score, ping);
        }
        return null;
    } // parsePlayer
} // Quake3
