package net.myacxy.jgsq.model;

import net.myacxy.jgsq.protocol.BaseProtocol;

import java.util.ArrayList;

public class GameServer
{
    protected BaseProtocol protocol;
    protected GameType type;

    public boolean isOnline;
    public boolean isPassworded;
    public String name;
    public int currentClients;
    public int maxClients;
    public String map;

    public ArrayList<Player> players;

    public GameServer(String ip, int port, BaseProtocol protocol, GameType type)
    {
        this.protocol = protocol;
        this.type = type;

        players = new ArrayList<>();

        protocol.connect(ip, port);
    }

    public void update()
    {
        protocol.updateServerInfo(this);
    }
}
