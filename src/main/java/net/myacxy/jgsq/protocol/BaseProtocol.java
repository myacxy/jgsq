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

    protected byte[] response;
    protected String hostName;
    protected InetAddress ipAddress;
    protected int port;

    protected Game game;

    public Map<String, String> parameters;

    private Pattern pattern;

    private static final String IP_ADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    protected DateTime timeOfLastQuery;

    public static int UPDATE_INTERVAL_MINUTES = 5;

    public BaseProtocol(Game game)
    {
        this.game = game;
        pattern = Pattern.compile(IP_ADDRESS_PATTERN);
        parameters = new HashMap<>();
    }

    public boolean connect(String ip, Integer port)
    {
        // 255.255.255.255:65535 or domain.tld:65535
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
            return false;
        }

        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(10000);
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        }

        return socket.;
    }

    public void disconnect()
    {
        socket.disconnect();
    }

    protected void query(String request)
    {
        if(timeOfLastQuery != null )
        {
            Interval interval = new Interval(timeOfLastQuery, DateTime.now());
            if(interval.toPeriod().getMinutes() < UPDATE_INTERVAL_MINUTES)
            {
                System.out.println(String.format(
                        "Last update was %d minutes ago. Minimum update interval is %d",
                        interval.toPeriod().getMinutes(),
                        UPDATE_INTERVAL_MINUTES));
                return;
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
    }

    protected boolean getResponse()
    {
        response = new byte[65507];
        packet = new DatagramPacket(response, response.length);
        try {
            socket.receive(packet);
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public abstract void updateServerInfo(GameServer server);
} // BaseProtocol
