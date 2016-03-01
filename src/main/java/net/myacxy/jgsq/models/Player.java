package net.myacxy.jgsq.models;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class Player
{
    @SerializedName("name")
    public String name;
    @SerializedName("colored_name")
    public String coloredName;
    @SerializedName("score")
    public int score;
    @SerializedName("deaths")
    public int deaths;
    @SerializedName("ping")
    public int ping;
    @SerializedName("time")
    public int time;

    public Player(String name, int score, int ping)
    {
        this.name = name;
        this.score = score;
        this.ping = ping;
    }

    public Player(String name, String coloredName, int score, int ping)
    {
        this.name = name;
        this.coloredName = coloredName;
        this.score = score;
        this.ping = ping;
    }

    @Override
    public String toString()
    {
        return String.format("(%s, %s, %s)", score, ping, coloredName);
    }

    public String toJson()
    {
        return new GsonBuilder().setPrettyPrinting()
                .create()
                .toJson(this);
    }
} // Player
