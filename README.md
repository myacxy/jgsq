Java Game Server Query
======================

Java library in early development used to query status information of game servers.

Features
========

* Easily define supported games via JSON
* Quake3 server protocol
* Received information 
  * Server parameters (e.g. hostname, map, time limit)
  * Current players (e.g. name, score, ping)

Usage
=====

Define supported games via JSON, e.g. `games.conf.json`

```JSON
[
  {
    "game"              : "Quake III Arena",
    "game_alternative"  : "Quake 3",
    "game_abbreviation" : "Q3",
    "server_protocol"   : "QUAKE3",
    "default_port"      : 28960
  },
  ...
]
```

Receive server status information

```Java
GameFactory gf = new GameFactory();
gf.loadConfig(Utilities.getResourceAsStream("games.conf.json")); // load resource file
Game q3 = gf.getGame("Quake 3"); // or "Q3" or "Quake III Arena"

GameServerFactory gsf = new GameServerFactory();
GameServer server = gsf.getGameServer(q3);

if(server.connect("127.0.0.1", 28960) == ServerResponseStatus.CONNECTED) // or domain.tld
{
  if(server.update() == ServerResponseStatus.OK)
  {
    // do something
  }
}
```

TODO
====

* RCON support
* Server protocols
  * All Seeing Eye
  * Doom3
  * GameSpy
  * GameSpy v2
  * Half-Life
  * Source

