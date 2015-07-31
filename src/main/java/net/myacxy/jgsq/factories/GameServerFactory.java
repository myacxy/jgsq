package net.myacxy.jgsq.factories;

import net.myacxy.jgsq.models.Game;
import net.myacxy.jgsq.models.GameServer;
import net.myacxy.jgsq.protocols.Quake3;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class GameServerFactory
{
    public GameServer getGameServer(Game game)
    {
        switch (game.serverProtocolType)
        {
            case Ase:
                throw new NotImplementedException();
            case Doom3:
                throw new NotImplementedException();
            case GameSpy:
                throw new NotImplementedException();
            case GameSpy2:
                throw new NotImplementedException();
            case HalfLife:
                throw new NotImplementedException();
            case Source:
                throw new NotImplementedException();
            case Quake3:
                return new GameServer(game, new Quake3(game));
            default:
                return null;
        }
    } // getGameServer
} // GameServerFactory
