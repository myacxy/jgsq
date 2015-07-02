package net.myacxy.jgsq.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.myacxy.jgsq.model.Game;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
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
            if (game.alternativeName.equals(name))
            {
                return game;
            }
            // query matches abbreviated name
            else if(game.abbreviatedName.equals(name))
            {
                return game;
            }
        }
        return null;
    } // getGameServer

    /**
     * Checks a JSON config file for all known game type configurations
     *
     * @param path absolute Path of the resource file
     * @return supported games
     */
    public Map<String, Game> loadConfig(Path path)
    {
        Gson gson = new GsonBuilder().create();
        Map<String, Game> games = new HashMap<>();

        try(Reader reader = Files.newBufferedReader(path))
        {
            Game[] gamesArray = gson.fromJson(reader, Game[].class);

            for (Game game : gamesArray) {
                games.put(game.name, game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return supportedGames = games;
    } // loadConfig
} // GameServerFactory
