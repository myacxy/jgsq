package net.myacxy.jgsq.protocol;

import com.sun.jndi.toolkit.url.Uri;
import com.sun.media.jfxmedia.track.Track;
import net.myacxy.jgsq.misc.Utilities;
import net.myacxy.jgsq.model.GameServer;
import net.myacxy.jgsq.model.Player;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Quake3 extends BaseProtocol
{

    private Pattern pattern;
    private final String PLAYER_REGEX = "(?<score>.+) (?<ping>.+) \"(?<name>.+)\"";

    public Quake3()
    {
        pattern = Pattern.compile(PLAYER_REGEX);
    }

    @Override
    public void updateServerInfo(GameServer server) {
        //query("getstatus");
        //getResponse();
        //String tmp = new String(response);
        byte[] fileBytes = new byte[0];
        try {
            Path path = Paths.get(getClass().getClassLoader().getResource("response.example").getPath().substring(1));
            fileBytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String tmp = new String(fileBytes);

        if(tmp.contains("disconnect"))
        {
            server.isOnline = false;
            return;
        }

        String[] lines = tmp.split("\\n");

        StringTokenizer tokens = new StringTokenizer(lines[1],"\\");
        while(tokens.hasMoreTokens())
        {
            String key = tokens.nextToken();
            String value = tokens.nextToken();
            parameters.put(key, value);
        }

        for(int i = 2; i < lines.length - 1; i++)
        {
            if(lines[i].length() == 0) continue;
            System.out.println(parsePlayer(lines[i]));
        }

        updateParameters(server);
    }

    private void updateParameters(GameServer server)
    {
        server.name = parameters.get("sv_hostname");
        server.map = parameters.get("map");
        if(parameters.containsKey("passworded"))
        {
            server.isPassworded = Boolean.parseBoolean(parameters.get("passworded"));
        }
    }

    protected Player parsePlayer(String line)
    {
        Matcher matcher = pattern.matcher(line);

        if(matcher.find())
        {
            String coloredName = matcher.group("name");
            String name = Utilities.RemoveColorCode(coloredName);
            int score = Integer.parseInt(matcher.group("score"));
            int ping =Integer.parseInt(matcher.group("ping"));
            return new Player(name, coloredName, score, ping);
        }
        return null;
    }
}
