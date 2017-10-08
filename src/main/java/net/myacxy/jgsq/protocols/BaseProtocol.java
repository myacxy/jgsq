package net.myacxy.jgsq.protocols;

import net.myacxy.jgsq.helpers.ServerResponseStatus;
import net.myacxy.jgsq.models.Game;
import net.myacxy.jgsq.models.GameServer;
import net.myacxy.jgsq.utils.StringUtils;

import java.io.IOException;
import java.net.*;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public abstract class BaseProtocol {

    private static final int MAX_PACKET_SIZE = 65507;
    private static final Pattern PATTERN_IP_ADDRESS;

    static {
        PATTERN_IP_ADDRESS = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    }

    private DatagramSocket socket = null;
    private DatagramPacket packet = null;

    protected String response;
    protected String ipHostName;
    public InetAddress ipAddress;
    protected int port;
    protected Game game;
    protected TreeMap<String, String> parameters = new TreeMap<>();
    protected int timeout = (int) TimeUnit.SECONDS.toMillis(10);
    protected ServerResponseStatus responseStatus;

    protected long time;
    protected long deltaTime;

    public BaseProtocol(Game game) {
        this.game = game;
    }

    public ServerResponseStatus connect(String ip, Integer port) {
        try {
            ipAddress = InetAddress.getByName(ip);
            ipHostName = ipAddress.getHostName();
            if (port != null) this.port = port;
            if (port < 0 || port > 65536) return responseStatus = ServerResponseStatus.ILLEGAL_ARGUMENT_EXCEPTION;
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

    public void disconnect() {
        if (socket != null) {
            socket.disconnect();
        }
    }

    protected ServerResponseStatus query(String request) {
        byte[] buffer = StringUtils.prependOutOfBand(request).getBytes();
        packet = new DatagramPacket(buffer, buffer.length, ipAddress, port);
        try {
            time = System.currentTimeMillis();
            socket.send(packet);
        } catch (IOException e) {
            return responseStatus = ServerResponseStatus.IO_EXCEPTION;
        }

        response = getResponse();
        return getResponseStatus();
    } // query

    protected String getResponse() {
        byte[] response = new byte[MAX_PACKET_SIZE];
        packet = new DatagramPacket(response, response.length);
        try {
            socket.receive(packet);
            deltaTime = System.currentTimeMillis() - time;
            responseStatus = ServerResponseStatus.OK;
        } catch (SocketTimeoutException e) {
            responseStatus = ServerResponseStatus.SOCKET_TIMEOUT_EXCEPTION;
            return null;
        } catch (IOException e) {
            responseStatus = ServerResponseStatus.IO_EXCEPTION;
            return null;
        }

        return new String(response);
    } // getResponse

    public ServerResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public abstract ServerResponseStatus update();

    public abstract void updateServerInfo(GameServer server);
} // BaseProtocol
