package net.myacxy.jgsq.model;

import net.myacxy.jgsq.protocol.BaseProtocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameServer
{
    protected BaseProtocol protocol;

    public int currentClients;
    public String ip;
    public boolean isOnline;
    public boolean isPasswordProtected;
    public String map;
    public int maxClients;
    public String hostName;
    public String coloredHostName;
    public ArrayList<Player> players;
    public int port;
    public Map<String, String> parameters;

    public GameServer(String ip, int port, BaseProtocol protocol)
    {
        this.ip = ip;
        this.port = port;
        this.protocol = protocol;

        players = new ArrayList<>();
        parameters = new HashMap<>();
    }

    public GameServer(BaseProtocol protocol)
    {
        this.protocol = protocol;
        players = new ArrayList<>();
        parameters = new HashMap<>();
    }

    public boolean connect()
    {
        return ip.length() > 0 && connect(ip, port);
    }

    public boolean connect(String ip, int port)
    {
        protocol.connect(ip, port);
        return true;
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

    public BaseProtocol getProtocol()
    {
        return protocol;
    }
} // GameServer
