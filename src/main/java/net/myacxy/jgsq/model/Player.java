package net.myacxy.jgsq.model;

public class Player
{
    public String name;
    public String coloredName;
    public int score;
    public int deaths;
    public int ping;
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
}