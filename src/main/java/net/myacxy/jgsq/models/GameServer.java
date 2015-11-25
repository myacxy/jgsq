package net.myacxy.jgsq.models;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import net.myacxy.jgsq.helpers.ServerResponseStatus;
import net.myacxy.jgsq.protocols.BaseProtocol;

import java.util.ArrayList;
import java.util.TreeMap;

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
    public TreeMap<String, String> parameters;
    @SerializedName("rcon")
    public String rcon;
    @SerializedName("query_port")
    public String queryPort;
    @SerializedName("ping")
    public int ping;

    public GameServer(Game game, String ipAddress, int port, BaseProtocol protocol)
    {
        this.game = game;
        this.ipAddress = ipAddress;
        this.port = port;
        this.protocol = protocol;

        players = new ArrayList<>();
        parameters = new TreeMap<>();
    }

    public GameServer(Game game, BaseProtocol protocol)
    {
        this.game = game;
        this.protocol = protocol;

        players = new ArrayList<>();
        parameters = new TreeMap<>();
    }

    public ServerResponseStatus connect()
    {
        return connect(ipAddress, port);
    }

    public ServerResponseStatus connect(String ipAddress, int port)
    {
        this.ipAddress = ipAddress;
        this.port = port;

        if(protocol.connect(ipAddress, port) == ServerResponseStatus.CONNECTED)
        {
            this.ipAddress = protocol.ipAddress.getHostAddress();
        }

        return protocol.getResponseStatus();
    }

    public void disconnect()
    {
        isOnline = false;
        protocol.disconnect();
    }

    public ServerResponseStatus update()
    {
        if(protocol.getResponseStatus() == ServerResponseStatus.CONNECTED)
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

    @Override
    public String toString()
    {
        return String.format("(%s:%s, %s, %s)", ipAddress, String.valueOf(port), hostName, game);
    }

    public String toJson()
    {
        return new GsonBuilder().setPrettyPrinting()
                .create()
                .toJson(this);
    }
} // GameServer
