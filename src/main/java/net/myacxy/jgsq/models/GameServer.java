package net.myacxy.jgsq.models;

import com.google.gson.annotations.SerializedName;
import net.myacxy.jgsq.helpers.ServerResponseStatus;
import net.myacxy.jgsq.protocols.BaseProtocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameServer
{
    protected transient BaseProtocol protocol;

    @SerializedName("current_clients")
    public int currentClients;
    @SerializedName("game")
    public Game game;
    @SerializedName("ip_address")
    public String ipAddress;
    @SerializedName("online")
    public boolean isOnline;
    @SerializedName("password_protected")
    public boolean isPasswordProtected;
    @SerializedName("map_name")
    public String mapName;
    @SerializedName("max_clients")
    public int maxClients;
    @SerializedName("host_name")
    public String hostName;
    @SerializedName("colored_host_name")
    public String coloredHostName;
    @SerializedName("players")
    public ArrayList<Player> players;
    @SerializedName("port")
    public int port;
    @SerializedName("parameters")
    public Map<String, String> parameters;
    @SerializedName("rcon")
    public String rcon;
    @SerializedName("query_port")
    public String queryPort;

    public GameServer(Game game, String ipAddress, int port, BaseProtocol protocol)
    {
        this.game = game;
        this.ipAddress = ipAddress;
        this.port = port;
        this.protocol = protocol;

        players = new ArrayList<>();
        parameters = new HashMap<>();
    }

    public GameServer(Game game, BaseProtocol protocol)
    {
        this.game = game;
        this.protocol = protocol;

        players = new ArrayList<>();
        parameters = new HashMap<>();
    }

    public ServerResponseStatus connect()
    {
        return connect(ipAddress, port);
    }

    public ServerResponseStatus connect(String ipAddress, int port)
    {
        this.ipAddress = ipAddress;
        this.port = port;

        return protocol.connect(ipAddress, port);
    }

    public void disconnect()
    {
        isOnline = false;
        protocol.disconnect();
    }

    public ServerResponseStatus update()
    {
        if(protocol.getResponseStatus() == ServerResponseStatus.Connected)
        {
            if(protocol.query("getstatus") == ServerResponseStatus.OK)
            {
                protocol.updateServerInfo(this);
            }
        }
        return protocol.getResponseStatus();
    }

    public BaseProtocol getProtocol()
    {
        return protocol;
    }
} // GameServer
