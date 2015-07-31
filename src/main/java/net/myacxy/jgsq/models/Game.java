package net.myacxy.jgsq.models;

import com.google.gson.annotations.SerializedName;
import net.myacxy.jgsq.helpers.ServerProtocolType;

public class Game
{
    /**
     * Full game name
     */
    @SerializedName("game")
    public String name;

    /**
     * Shorter variation of the game name
     */
    @SerializedName("game_alternative")
    public String alternativeName;

    /**
     * Common abbreviation of the game name
     */
    @SerializedName("game_abbreviation")
    public String abbreviatedName;

    @SerializedName("server_protocol")
    public ServerProtocolType serverProtocolType;

    @SerializedName("default_port")
    public int defaultPort;

    public Game(String name,
                ServerProtocolType serverProtocolType,
                int defaultPort)
    {
        this.name = name;
        this.serverProtocolType = serverProtocolType;
        this.defaultPort = defaultPort;
    }


    public Game(String name,
                String alternativeName,
                String abbreviatedName,
                ServerProtocolType serverProtocolType,
                int defaultPort)
    {
        this.name = name;
        this.alternativeName = alternativeName;
        this.abbreviatedName = abbreviatedName;
        this.serverProtocolType = serverProtocolType;
        this.defaultPort = defaultPort;
    }

    @Override
    public String toString()
    {
        return name;
    }
} // Game
