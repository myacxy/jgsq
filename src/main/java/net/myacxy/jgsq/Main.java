package net.myacxy.jgsq;

import java.io.IOException;
import java.net.*;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args)
    {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress ia = null;
        try {
            ia = InetAddress.getByName("myacxy.net");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String out = "ÿÿÿÿgetstatus";
        byte [] buff = out.getBytes();
        buff[0] = (byte)0xff;		// oob
        buff[1] = (byte)0xff;
        buff[2] = (byte)0xff;
        buff[3] = (byte)0xff;
        DatagramPacket dp = new DatagramPacket(buff, buff.length, ia, 28070);
        try {
            ds.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buff = new byte[65507];
        dp = new DatagramPacket(buff, buff.length);
        try {
            ds.receive(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String temp = new String(buff);
        System.out.println(temp);

        StringTokenizer tokens = new StringTokenizer(temp,"\\");
        tokens.nextToken();
        while(tokens.hasMoreTokens())
        {
            String cmd = tokens.nextToken();
            if (!tokens.hasMoreTokens()) return;
            else System.out.println(cmd);
        }
    }
}
