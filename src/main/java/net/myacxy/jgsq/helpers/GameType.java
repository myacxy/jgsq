package net.myacxy.jgsq.helpers;

public enum GameType
{
    //region Ase
    /// <summary>
    /// Multi-Theft Auto
    /// </summary>
    MultiTheftAuto(ServerProtocolType.ASE),
    /// <summary>
    /// Multi-Theft Auto: Vice City
    /// </summary>
    MultiTheftAutoViceCity(ServerProtocolType.ASE),
    /// <summary>
    /// Age of Empires 2
    /// </summary>
    AgeOfEmpires2(ServerProtocolType.ASE),
    /// <summary>
    /// Age of Empires 2: The Conquerors
    /// </summary>
    AgeOfEmpires2TheConquerors(ServerProtocolType.ASE),
    /// <summary>
    /// Soldat
    /// </summary>
    Soldat(ServerProtocolType.ASE),
    /// <summary>
    /// Devastation
    /// </summary>
    Devastation(ServerProtocolType.ASE),
    /// <summary>
    /// Purge Jihad
    /// </summary>
    PurgeJihad(ServerProtocolType.ASE),
    /// <summary>
    /// Universal Combat
    /// </summary>
    UniversalCombat(ServerProtocolType.ASE),
    /// <summary>
    /// Xpand Rally
    /// </summary>
    XpandRally(ServerProtocolType.ASE),
    /// <summary>
    /// Far Cry
    /// </summary>
    FarCry(ServerProtocolType.ASE),
    /// <summary>
    /// Medal of Honor: Pacific Assault
    /// </summary>
    MedalOfHonorPacificAssault(ServerProtocolType.ASE),
    /// <summary>
    /// Chaser
    /// </summary>
    Chaser(ServerProtocolType.ASE),
    /// <summary>
    /// Chrome
    /// </summary>
    Chrome(ServerProtocolType.ASE),
    //endregion
    //region Quake3
    /// <summary>
    /// Quake 3
    /// </summary>
    Quake3(ServerProtocolType.QUAKE3),
    /// <summary>
    /// Call of Duty
    /// </summary>
    CallOfDuty(ServerProtocolType.QUAKE3),
    /// <summary>
    /// Star Wars Jedi Knight: Jedi Academy
    /// </summary>
    JediKnightJediAcademy(ServerProtocolType.QUAKE3),
    /// <summary>
    /// Call of Duty: United Offensive
    /// </summary>
    CallOfDutyUnitedOffensive(ServerProtocolType.QUAKE3),
    /// <summary>
    /// Star Trek: Voyager - Elite Force
    /// </summary>
    StarTrekVoyagerEliteForce(ServerProtocolType.QUAKE3),
    /// <summary>
    /// Star Trek: Voyager - Elite Force 2
    /// </summary>
    StarTrekVoyagerEliteForce2(ServerProtocolType.QUAKE3),
    /// <summary>
    /// Soldier of Fortune 2
    /// </summary>
    SoldierOfFortune2(ServerProtocolType.QUAKE3),
    /// <summary>
    /// Return to Castle Wolfenstein
    /// </summary>
    ReturnToCastleWolfenstein(ServerProtocolType.QUAKE3),
    /// <summary>
    /// Wolfenstein: Enemy Territory
    /// </summary>
    WolfensteinEnemyTerritory(ServerProtocolType.QUAKE3),
    /// <summary>
    /// Star Wars Jedi Knight 2: Jedi Outcast
    /// </summary>
    JediKnightJediOutcast(ServerProtocolType.QUAKE3),
    /// <summary>
    /// Soldier of Fortune
    /// </summary>
    SoldierOfFortune(ServerProtocolType.QUAKE3),
    /// <summary>
    /// Daikatana
    /// </summary>
    Daikatana(ServerProtocolType.QUAKE3),
    //endregion
    //region Half-Life
    /// <summary>
    /// Half-Life
    /// </summary>
    HalfLife(ServerProtocolType.HALF_LIFE),
    /// <summary>
    /// Counter-Strike
    /// </summary>
    CounterStrike(ServerProtocolType.HALF_LIFE),
    /// <summary>
    /// Counter-Strike: Condition Zero
    /// </summary>
    CounterStrikeConditionZero(ServerProtocolType.HALF_LIFE),
    /// <summary>
    /// Day of Defeat
    /// </summary>
    DayOfDefeat(ServerProtocolType.HALF_LIFE),
    /// <summary>
    /// Gunman Chronicles
    /// </summary>
    GunmanChronicles(ServerProtocolType.HALF_LIFE),
    //endregion
    //region GameSpy
    /// <summary>
    /// Doom 3
    /// </summary>
    Doom3 (ServerProtocolType.DOOM3),
    /// <summary>
    /// Unreal Tournament 2003
    /// </summary>
    UnrealTournament2003(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Unreal Tounrmanet 2004
    /// </summary>
    UnrealTournament2004(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Unreal 2 XMP
    /// </summary>
    Unreal2XMP(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Medal of Honor: Allied Assault
    /// </summary>
    MedalOfHonorAlliedAssault(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Medal of Honor: Breakthrough
    /// </summary>
    MedalOfHonorBreakthrough(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Medal of Honor: Spearhead
    /// </summary>
    MedalOfHonorSpearhead(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// GameSpy (Generic Protocol)
    /// </summary>
    GameSpy(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Unreal Tournament
    /// </summary>
    UnrealTournament(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Descent 3
    /// </summary>
    Descent3(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Battlefield 1942
    /// </summary>
    Battlefield1942(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Postal 2
    /// </summary>
    Postal2(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Deus Ex
    /// </summary>
    DeusEx(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// IL-2 Sturmovik
    /// </summary>
    IL2Sturmovik(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// IL-2 Sturmovik: Forgotten Battles
    /// </summary>
    IL2SturmovikForgottenBattles(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Heretic 2
    /// </summary>
    Heretic2(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// IGI-2: Covert Strike
    /// </summary>
    IGI2CovertStrike(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Gore
    /// </summary>
    Gore(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Vietcong
    /// </summary>
    Vietcong(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Serious Sam
    /// </summary>
    SeriousSam(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Serious Sam: The Second Encounter
    /// </summary>
    SeriousSamTheSecondEncounter(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Aliens vs. Predators 2
    /// </summary>
    AliensVsPredators2(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// No One Lives Forever
    /// </summary>
    NoOneLivesForever(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// No One Lives Forever 2
    /// </summary>
    NoOneLivesForever2(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Shogo
    /// </summary>
    Shogo(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Codename: Eagle
    /// </summary>
    CodenameEagle(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Giants: Citizen Kabuto
    /// </summary>
    GiantsCitizenKabuto(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Global Operations
    /// </summary>
    GlobalOperations(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Nerf ArenaBlast
    /// </summary>
    NerfArenaBlast(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// RalliSport Challenge
    /// </summary>
    RalliSportChallenge(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Rally Masters
    /// </summary>
    RallyMasters(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Command and Conquer: Renegade
    /// </summary>
    CommandAndConquerRenegade(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Rune
    /// </summary>
    Rune(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Sin
    /// </summary>
    Sin(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Tactical Ops
    /// </summary>
    TacticalOps(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Unreal
    /// </summary>
    Unreal(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Wheel of Time
    /// </summary>
    WheelOfTime(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Deadly Dozen: Pacific Theater
    /// </summary>
    DeadlyDozenPacificTheater(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Dirt Track Racing 2
    /// </summary>
    DirtTrackRacing2(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Drakan: Order of the Flame
    /// </summary>
    DrakanOrderOfTheFlame(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// F1 2002
    /// </summary>
    F12002(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Iron Storm
    /// </summary>
    IronStorm(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// James Bond: Nightfire
    /// </summary>
    JamesBondNightfire(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Kingpin
    /// </summary>
    Kingpin(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Need for Speed: Hot Pursuit 2
    /// </summary>
    NeedForSpeedHotPursuit2(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Redline
    /// </summary>
    Redline(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Turok 2
    /// </summary>
    Turok2(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Tron 2.0
    /// </summary>
    Tron2(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Tony Hawk's Pro Skater 3
    /// </summary>
    TonyHawksProSkater3(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Tony Hawk's Pro Skater 4
    /// </summary>
    TonyHawksProSkater4(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// V8 Supercar Challenge
    /// </summary>
    V8SupercarChallenge(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Team Factor
    /// </summary>
    TeamFactor(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Rainbow Six
    /// </summary>
    RainbowSix(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Rainbow Six: Rogue Spear
    /// </summary>
    RainbowSixRogueSpear(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Nitro Family
    /// </summary>
    NitroFamily(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Rise of Nations
    /// </summary>
    RiseOfNations(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Contract J.A.C.K.
    /// </summary>
    ContractJACK(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Homeworld 2
    /// </summary>
    Homeworld2(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Breed
    /// </summary>
    Breed(ServerProtocolType.GAME_SPY2),
    /// <summary>
    /// Operation Flashpoint: Resistance
    /// </summary>
    OperationFlashPointResistance(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Star Trek: Bridge Commander
    /// </summary>
    StarTrekBridgeCommander(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Tribes: Vengeance
    /// </summary>
    TribesVengeance(ServerProtocolType.GAME_SPY),
    /// <summary>
    /// Tony Hawk's Underground 2
    /// </summary>
    TonyHawksUnderground2(ServerProtocolType.GAME_SPY),
    //endregion
    //region Gamespy v2
    /// <summary>
    /// Star Wars: Battlefront
    /// </summary>
    StarWarsBattlefront(ServerProtocolType.GAME_SPY2),
    /// <summary>
    /// GameSpy 2 (Generic Protocol)
    /// </summary>
    GameSpy2(ServerProtocolType.GAME_SPY2),
    /// <summary>
    /// Battlefield Vietnam
    /// </summary>
    BattlefieldVietnam(ServerProtocolType.GAME_SPY2),
    /// <summary>
    /// Painkiller
    /// </summary>
    Painkiller(ServerProtocolType.GAME_SPY2),
    /// <summary>
    /// Halo: Combat Evolved
    /// </summary>
    HaloCombatEvolved(ServerProtocolType.GAME_SPY2),
    /// <summary>
    /// America's Army
    /// </summary>
    AmericasArmy(ServerProtocolType.GAME_SPY2),
    /// <summary>
    /// Neverwinter Nights
    /// </summary>
    NeverwinterNights(ServerProtocolType.GAME_SPY2),
    /// <summary>
    /// Operation Flashpoint
    /// </summary>
    OperationFlashpoint(ServerProtocolType.GAME_SPY2),
    //endregion
    //region Hl-Source
    /// <summary>
    /// Source Engine (Generic Protocol)
    /// </summary>
    Source(ServerProtocolType.SOURCE),
    /// <summary>
    /// Half-Life 2
    /// </summary>
    HalfLife2(ServerProtocolType.SOURCE),
    /// <summary>
    /// Counter-Strike: Source
    /// </summary>
    CounterStrikeSource(ServerProtocolType.SOURCE),
    //endregion
    //region Other
    /// <summary>
    /// Not listed game
    /// </summary>
    Unknown(ServerProtocolType.UNKNOWN);
    //endregion

    private final ServerProtocolType protocol;

    GameType(ServerProtocolType protocol)
    {
        this.protocol = protocol;
    }

    public ServerProtocolType getProtocol()
    {
        return protocol;
    }
}
