package net.myacxy.jgsq.protocol;

import net.myacxy.jgsq.model.Game;
import net.myacxy.jgsq.model.GameServer;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
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

    protected byte[] response;
    protected String hostName;
    protected InetAddress ipAddress;
    protected int port;
    protected Game game;
    protected Map<String, String> parameters;
    protected DateTime timeOfLastQuery;

    protected static int UPDATE_INTERVAL_MINUTES = 5;

    public BaseProtocol(Game game)
    {
        this.game = game;
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
            return;
        }

        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(10000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    } // connect

    public void disconnect()
    {
        if(socket != null)
        {
            socket.disconnect();
        }
    }

    protected byte[] query(String request, boolean forceUpdate)
    {
        if(!forceUpdate && timeOfLastQuery != null)
        {
            Interval interval = new Interval(timeOfLastQuery, DateTime.now());
            if(interval.toPeriod().getMinutes() < UPDATE_INTERVAL_MINUTES)
            {
                System.out.println(String.format(
                        "Last update was %d minutes ago. Minimum update interval is %d",
                        interval.toPeriod().getMinutes(),
                        UPDATE_INTERVAL_MINUTES));
                return response;
            }
        }
        byte[] tmp = request.getBytes();
        int offset = 4;
        byte[] buffer = new byte[tmp.length + offset];

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
        timeOfLastQuery = DateTime.now();

        return getResponse();
    } // query

    protected byte[] getResponse()
    {
        response = new byte[65507];
        packet = new DatagramPacket(response, response.length);
        try {
            socket.receive(packet);
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        return response;
    } // getResponse

    public abstract void updateServerInfo(GameServer server);
} // BaseProtocol
