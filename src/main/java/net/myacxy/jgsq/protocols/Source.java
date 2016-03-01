package net.myacxy.jgsq.protocols;

import net.myacxy.jgsq.helpers.ServerResponseStatus;
import net.myacxy.jgsq.models.Game;
import net.myacxy.jgsq.models.GameServer;
import net.myacxy.jgsq.models.Player;

public class Source extends BaseProtocol
{
    public Source(Game game)
    {
        super(game);
    }

    @Override
    public ServerResponseStatus update()
    {
        return query(Request.A2S_INFO.get());
    }

    @Override
    public void updateServerInfo(GameServer server)
    {

    } // updateServerInfo

    private void updateParameters(GameServer server)
    {

    } // updateParameters

    protected Player parsePlayer(String line)
    {
        return null;
    } // parsePlayer

    public enum Request
    {
        A2S_INFO((byte) 0x54, "Source Engine Query"),
        /**
         * Ping the server to see if it exists, this can be used to calculate the latency to the server.
         * Warning:	According to Valve (see Talk Page), A2A_PING is no longer supported on Counter Strike: Source and
         * Team Fortress 2 servers, and is considered a deprecated feature.
         *
         * Header	byte	'i' (0x69)
         * FF FF FF FF 69   ÿÿÿÿi
         */
        A2S_PING((byte) 0x69);

        public byte header;
        public String payload;

        Request(byte header)
        {
            this.header = header;
        }

        Request(byte header, String payload)
        {
            this.header = header;
            this.payload = payload;
        }

        public String get()
        {
            return String.valueOf(header) + " " + payload;
        }
    } // Request

    public enum Challenge
    {
        A2S_PLAYER((byte) 0x55, 0xFFFFFFFF),
        A2S_RULES((byte) 0x56, 0xFFFFFFFF),
        A2S_PLAYER_CHALLENGE((byte) 0x55, 0xFFFFFFFF),
        /**
         * A2S_PLAYER and A2S_RULES queries both require a challenge number. Formerly, this number could be obtained
         * via an A2S_SERVERQUERY_GETCHALLENGE request. In newer games it no longer works. Instead, issue A2S_PLAYER
         * or A2S_RULES queries with an initial challenge of -1 (0xFFFFFFFF) to receive a usable challenge number.
         *
         * On some engines (confirmed AppIDs: 17510, 17530, 17740, 17550, 17700) it can be used.
         *
         * Header   byte	'W' (0x57)
         *
         * Example:
         * FF FF FF FF 57   ÿÿÿÿW
         */
        A2S_SERVERQUERY_GETCHALLENGE((byte) 0x57);

        public byte header;
        public int challenge;

        Challenge(byte header)
        {
            this.header = header;
        }

        Challenge(byte header, int challenge)
        {
            this.header = header;
            this.challenge = challenge;
        }

        public String get()
        {
            return String.valueOf(header) + " " + String.valueOf(challenge);
        }
    }

    public class Response
    {
        public byte header;
        public String payload;

        Response(byte header, String payload)
        {
            this.header = header;
            this.payload = payload;
        }

        public String get()
        {
            return String.valueOf(header) + " " + payload;
        }

        @Override
        public String toString() {
            return get();
        }

        public class A2S_PLAYER extends Response
        {
            A2S_PLAYER(String payload)
            {
                super((byte) 0x41, payload);
            }
        }

        /**
         * Source Server
         *
         * Heading	byte	'j' (0x6A)
         * Payload	string	'00000000000000.'
         *
         * Example:
         * FF FF FF FF 6A 30 30 30 30 30 30 30 30 30 30 30    ÿÿÿÿj00000000000
         * 30 30 30 00                                        000.
         */
        public class A2S_PING extends Response
        {
            A2S_PING(String payload)
            {
                super(Request.A2S_PING.header, payload);
            }
        }

        /**
         * Header	    byte	Should be equal to 'A' (0x41.)
         * Challenge	long	The challenge number to use.
         *
         * Example:
         * FF FF FF FF 41 4B A1 D5 22   ÿÿÿÿAK¡Õ"
         */
        public class A2S_SERVERQUERY_GETCHALLENGE extends Response
        {
            A2S_SERVERQUERY_GETCHALLENGE(String payload)
            {
                super((byte) 0x41, payload);
            }
        }
    } // Response
} // Source
