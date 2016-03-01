package net.myacxy.jgsq.protocols;

import net.myacxy.jgsq.helpers.ServerResponseStatus;
import net.myacxy.jgsq.models.Game;
import net.myacxy.jgsq.models.GameServer;

import java.io.IOException;
import java.net.*;
import java.util.TreeMap;
import java.util.regex.Pattern;

public abstract class BaseProtocol
{
    private DatagramSocket socket = null;
    private DatagramPacket packet = null;
    private Pattern pattern;
    private static final String IP_ADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    protected String response;
    protected String ipHostName;
    public InetAddress ipAddress;
    protected int port;
    protected Game game;
    protected TreeMap<String, String> parameters;
    protected int timeout = 1500;
    protected ServerResponseStatus responseStatus;

    protected long time;
    protected long deltaTime;

    public BaseProtocol(Game game)
    {
        this.game = game;
        pattern = Pattern.compile(IP_ADDRESS_PATTERN);
        parameters = new TreeMap<>();
    }

    public ServerResponseStatus connect(String ip, Integer port)
    {
        try {
            ipAddress = InetAddress.getByName(ip);
            ipHostName = ipAddress.getHostName();
            if(port != null) this.port = port;
            if(port < 0 || port > 65536) return responseStatus = ServerResponseStatus.ILLEGAL_ARGUMENT_EXCEPTION;
        } catch (UnknownHostException e) {
            return responseStatus = ServerResponseStatus.UNKNOWN_HOST_EXCEPTION;
        }

        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(timeout);
        } catch (SocketException e) {
            return responseStatus = ServerResponseStatus.SOCKET_EXCEPTION;
        }
        return responseStatus = ServerResponseStatus.CONNECTED;
    } // connect

    public void disconnect()
    {
        if(socket != null)
        {
            socket.disconnect();
        }
    }

    protected ServerResponseStatus query(String request)
    {
        byte[] tmp = request.getBytes();
        int offset = 4;
        byte[] buffer = new byte[tmp.length + offset];

        for (int i = 0; i < tmp.length + offset; i++)
        {
            if(i < offset) buffer[i] = (byte)0xff; // oob
            else buffer[i] = tmp[i - offset];
        }

        packet = new DatagramPacket(buffer, buffer.length, ipAddress, port);
        try
        {
            time = System.currentTimeMillis();
            socket.send(packet);
        }
        catch (IOException e)
        {
            return responseStatus = ServerResponseStatus.IO_EXCEPTION;
        }

        response = getResponse();
        return getResponseStatus();
    } // query

    protected String getResponse()
    {
        byte[] response = new byte[65507];
        packet = new DatagramPacket(response, response.length);
        try
        {
            socket.receive(packet);
            deltaTime = System.currentTimeMillis() - time;
            responseStatus = ServerResponseStatus.OK;
        }
        catch (SocketTimeoutException e)
        {
            responseStatus = ServerResponseStatus.SOCKET_TIMEOUT_EXCEPTION;
            return null;
        }
        catch (IOException e)
        {
            responseStatus = ServerResponseStatus.IO_EXCEPTION;
            return null;
        }

        return new String(response);
    } // getResponse

    public ServerResponseStatus getResponseStatus()
    {
        return responseStatus;
    }

    public abstract ServerResponseStatus update();

    public abstract void updateServerInfo(GameServer server);
} // BaseProtocol
