package net.myacxy.jgsq.model;

import net.myacxy.jgsq.protocol.BaseProtocol;

import java.util.ArrayList;
import java.util.Map;

public class GameServer
{
    protected BaseProtocol protocol;

    public String ip;
    public int port;
    public boolean isOnline;
    public boolean isPasswordProtected;
    public String name;
    public int currentClients;
    public int maxClients;
    public String map;

    public ArrayList<Player> players;

    public Map<String, String> parameters;

    public GameServer(String ip, int port, BaseProtocol protocol)
    {
        this.ip = ip;
        this.port = port;
        this.protocol = protocol;

        players = new ArrayList<>();
    }

    public GameServer(BaseProtocol protocol)
    {
        this.protocol = protocol;
        players = new ArrayList<>();
    }

    public boolean connect()
    {
        return ip.length() > 0 && connect(ip, port);
    }

    public boolean connect(String ip, int port)
    {
        return isOnline = protocol.connect(ip, port);
    }

    public void disconnect()
    {
        isOnline = false;
        protocol.disconnect();
    }

    public void update()
    {
        protocol.updateServerInfo(this);
    }
} // GameServer
