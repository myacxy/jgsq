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
            case ASE:
                throw new NotImplementedException();
            case DOOM3:
                throw new NotImplementedException();
            case GAME_SPY:
                throw new NotImplementedException();
            case GAME_SPY2:
                throw new NotImplementedException();
            case HALF_LIFE:
                throw new NotImplementedException();
            case SOURCE:
                throw new NotImplementedException();
            case QUAKE3:
                return new GameServer(game, new Quake3(game));
            default:
                return null;
        }
    } // getGameServer
} // GameServerFactory
