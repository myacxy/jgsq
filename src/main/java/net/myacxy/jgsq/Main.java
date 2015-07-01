package net.myacxy.jgsq;

import net.myacxy.jgsq.model.GameServer;
import net.myacxy.jgsq.model.GameType;
import net.myacxy.jgsq.protocol.Quake3;

public class Main
{
    public static void main(String[] args)
    {
        GameServer server = new GameServer("127.0.0.1",
                28070,
                new Quake3(),
                GameType.JediKnightJediOutcast);

        server.update();

        System.out.printf(server.name);
    }
}
