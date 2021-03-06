package net.myacxy.jgsq.factories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.myacxy.jgsq.models.Game;
import net.myacxy.jgsq.helpers.ServerProtocolType;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class GameFactory
{
    protected Map<String, Game> supportedGames;

    public GameFactory()
    {
        supportedGames = new HashMap<>();
    }

    /**
     *
     * @param name The name of the game (full, shorter alternative or abbreviated)
     * @return corresponding game if query is a supported game
     */
    public Game getGame(String name)
    {
        if(supportedGames.size() < 1) return null;
        // query matches full name
        if(supportedGames.containsKey(name))
        {
            return supportedGames.get(name);
        }
        for (Game game : supportedGames.values())
        {
            // query matches alternative name
            if (game.alternativeName.equalsIgnoreCase(name))
            {
                return game;
            }
            // query matches abbreviated name
            else if(game.abbreviatedName.equalsIgnoreCase(name))
            {
                return game;
            }
        }
        return null;
    } // getGameServer

    /**
     * Checks a JSON config file for all known game type configurations
     *
     * @param fileStream inputStream of the resource file
     * @return supported games
     */
    public Map<String, Game> loadConfig(InputStream fileStream)
    {
        Gson gson = new GsonBuilder().create();
        Map<String, Game> games = new HashMap<>();

        try {
            Reader reader = new InputStreamReader(fileStream);
            Game[] gamesArray = gson.fromJson(reader, Game[].class);

            for (Game game : gamesArray) {
                games.put(game.name, game);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return supportedGames = games;
    } // loadConfig

    /**
     *
     * @param type server protocol type to be queried
     * @return supported games filtered by server protocol type
     */
    public Map<String, Game> getSupportedGames(ServerProtocolType type)
    {
        if(supportedGames == null) return null;
        Map<String, Game> filteredGames = new HashMap<>();

        // JDK8
//        supportedGames.values().parallelStream().forEach(game -> {
//            if(game.serverProtocolType.equals(type)) filteredGames.put(game.name, game);
//        });

        // JDK7
        for(Game game : supportedGames.values())
        {
            if(game.serverProtocolType.equals(type)) filteredGames.put(game.name, game);
        }

        return filteredGames;
    }

    public Map<String, Game> getSupportedGames()
    {
        return supportedGames;
    }
} // GameServerFactory
