package net.myacxy.jgsq.protocol;

import net.myacxy.jgsq.model.GameServer;

import java.io.IOException;
import java.net.*;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class BaseProtocol
{
    private DatagramSocket socket = null;
    private DatagramPacket packet = null;
    private byte[] buffer;
    protected byte[] response;
    protected String hostName;
    protected InetAddress ipAddress;
    protected int port;

    public Map<String, String> parameters;

    private Pattern pattern;

    private static final String IP_ADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public BaseProtocol()
    {
        pattern = Pattern.compile(IP_ADDRESS_PATTERN);
        parameters = new HashMap<>();
    }

    public void connect(String ip, Integer port)
    {
        if(ip.contains(":"))
        {
            String[] tmp = ip.split(":", 2);
            if(pattern.matcher(tmp[0]).matches()) ip = tmp[0];
            this.port = Integer.parseInt(tmp[1]);
        }

        try {
            ipAddress = InetAddress.getByName(ip);
            hostName = ipAddress.getHostName();
            if(port != null) this.port = port;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(1500);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void disconnect()
    {

    }

    protected void query(String request)
    {
        byte[] tmp = request.getBytes();
        int offset = 4;
        buffer = new byte[tmp.length + offset];

        for (int i = 0; i < tmp.length + offset; i++)
        {
            if(i < offset) buffer[i] = (byte)0xff; // oob
            else buffer[i] = tmp[i - offset];
        }

        packet = new DatagramPacket(buffer, buffer.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void getResponse()
    {
        response = new byte[65507];
        packet = new DatagramPacket(response, response.length);
        try {
            socket.receive(packet);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public abstract void updateServerInfo(GameServer server);
} // BaseProtocol
