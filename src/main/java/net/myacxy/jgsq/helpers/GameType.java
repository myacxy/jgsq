package net.myacxy.jgsq.helpers;

import net.myacxy.jgsq.helpers.ServerProtocolType;

public enum GameType
{
    //region Ase
    /// <summary>
    /// Multi-Theft Auto
    /// </summary>
    MultiTheftAuto(ServerProtocolType.Ase),
    /// <summary>
    /// Multi-Theft Auto: Vice City
    /// </summary>
    MultiTheftAutoViceCity(ServerProtocolType.Ase),
    /// <summary>
    /// Age of Empires 2
    /// </summary>
    AgeOfEmpires2(ServerProtocolType.Ase),
    /// <summary>
    /// Age of Empires 2: The Conquerors
    /// </summary>
    AgeOfEmpires2TheConquerors(ServerProtocolType.Ase),
    /// <summary>
    /// Soldat
    /// </summary>
    Soldat(ServerProtocolType.Ase),
    /// <summary>
    /// Devastation
    /// </summary>
    Devastation(ServerProtocolType.Ase),
    /// <summary>
    /// Purge Jihad
    /// </summary>
    PurgeJihad(ServerProtocolType.Ase),
    /// <summary>
    /// Universal Combat
    /// </summary>
    UniversalCombat(ServerProtocolType.Ase),
    /// <summary>
    /// Xpand Rally
    /// </summary>
    XpandRally(ServerProtocolType.Ase),
    /// <summary>
    /// Far Cry
    /// </summary>
    FarCry(ServerProtocolType.Ase),
    /// <summary>
    /// Medal of Honor: Pacific Assault
    /// </summary>
    MedalOfHonorPacificAssault(ServerProtocolType.Ase),
    /// <summary>
    /// Chaser
    /// </summary>
    Chaser(ServerProtocolType.Ase),
    /// <summary>
    /// Chrome
    /// </summary>
    Chrome(ServerProtocolType.Ase),
    //endregion
    //region Quake3
    /// <summary>
    /// Quake 3
    /// </summary>
    Quake3(ServerProtocolType.Quake3),
    /// <summary>
    /// Call of Duty
    /// </summary>
    CallOfDuty(ServerProtocolType.Quake3),
    /// <summary>
    /// Star Wars Jedi Knight: Jedi Academy
    /// </summary>
    JediKnightJediAcademy(ServerProtocolType.Quake3),
    /// <summary>
    /// Call of Duty: United Offensive
    /// </summary>
    CallOfDutyUnitedOffensive(ServerProtocolType.Quake3),
    /// <summary>
    /// Star Trek: Voyager - Elite Force
    /// </summary>
    StarTrekVoyagerEliteForce(ServerProtocolType.Quake3),
    /// <summary>
    /// Star Trek: Voyager - Elite Force 2
    /// </summary>
    StarTrekVoyagerEliteForce2(ServerProtocolType.Quake3),
    /// <summary>
    /// Soldier of Fortune 2
    /// </summary>
    SoldierOfFortune2(ServerProtocolType.Quake3),
    /// <summary>
    /// Return to Castle Wolfenstein
    /// </summary>
    ReturnToCastleWolfenstein(ServerProtocolType.Quake3),
    /// <summary>
    /// Wolfenstein: Enemy Territory
    /// </summary>
    WolfensteinEnemyTerritory(ServerProtocolType.Quake3),
    /// <summary>
    /// Star Wars Jedi Knight 2: Jedi Outcast
    /// </summary>
    JediKnightJediOutcast(ServerProtocolType.Quake3),
    /// <summary>
    /// Soldier of Fortune
    /// </summary>
    SoldierOfFortune(ServerProtocolType.Quake3),
    /// <summary>
    /// Daikatana
    /// </summary>
    Daikatana(ServerProtocolType.Quake3),
    //endregion
    //region Half-Life
    /// <summary>
    /// Half-Life
    /// </summary>
    HalfLife(ServerProtocolType.HalfLife),
    /// <summary>
    /// Counter-Strike
    /// </summary>
    CounterStrike(ServerProtocolType.HalfLife),
    /// <summary>
    /// Counter-Strike: Condition Zero
    /// </summary>
    CounterStrikeConditionZero(ServerProtocolType.HalfLife),
    /// <summary>
    /// Day of Defeat
    /// </summary>
    DayOfDefeat(ServerProtocolType.HalfLife),
    /// <summary>
    /// Gunman Chronicles
    /// </summary>
    GunmanChronicles(ServerProtocolType.HalfLife),
    //endregion
    //region GameSpy
    /// <summary>
    /// Doom 3
    /// </summary>
    Doom3 (ServerProtocolType.Doom3),
    /// <summary>
    /// Unreal Tournament 2003
    /// </summary>
    UnrealTournament2003(ServerProtocolType.GameSpy),
    /// <summary>
    /// Unreal Tounrmanet 2004
    /// </summary>
    UnrealTournament2004(ServerProtocolType.GameSpy),
    /// <summary>
    /// Unreal 2 XMP
    /// </summary>
    Unreal2XMP(ServerProtocolType.GameSpy),
    /// <summary>
    /// Medal of Honor: Allied Assault
    /// </summary>
    MedalOfHonorAlliedAssault(ServerProtocolType.GameSpy),
    /// <summary>
    /// Medal of Honor: Breakthrough
    /// </summary>
    MedalOfHonorBreakthrough(ServerProtocolType.GameSpy),
    /// <summary>
    /// Medal of Honor: Spearhead
    /// </summary>
    MedalOfHonorSpearhead(ServerProtocolType.GameSpy),
    /// <summary>
    /// GameSpy (Generic Protocol)
    /// </summary>
    GameSpy(ServerProtocolType.GameSpy),
    /// <summary>
    /// Unreal Tournament
    /// </summary>
    UnrealTournament(ServerProtocolType.GameSpy),
    /// <summary>
    /// Descent 3
    /// </summary>
    Descent3(ServerProtocolType.GameSpy),
    /// <summary>
    /// Battlefield 1942
    /// </summary>
    Battlefield1942(ServerProtocolType.GameSpy),
    /// <summary>
    /// Postal 2
    /// </summary>
    Postal2(ServerProtocolType.GameSpy),
    /// <summary>
    /// Deus Ex
    /// </summary>
    DeusEx(ServerProtocolType.GameSpy),
    /// <summary>
    /// IL-2 Sturmovik
    /// </summary>
    IL2Sturmovik(ServerProtocolType.GameSpy),
    /// <summary>
    /// IL-2 Sturmovik: Forgotten Battles
    /// </summary>
    IL2SturmovikForgottenBattles(ServerProtocolType.GameSpy),
    /// <summary>
    /// Heretic 2
    /// </summary>
    Heretic2(ServerProtocolType.GameSpy),
    /// <summary>
    /// IGI-2: Covert Strike
    /// </summary>
    IGI2CovertStrike(ServerProtocolType.GameSpy),
    /// <summary>
    /// Gore
    /// </summary>
    Gore(ServerProtocolType.GameSpy),
    /// <summary>
    /// Vietcong
    /// </summary>
    Vietcong(ServerProtocolType.GameSpy),
    /// <summary>
    /// Serious Sam
    /// </summary>
    SeriousSam(ServerProtocolType.GameSpy),
    /// <summary>
    /// Serious Sam: The Second Encounter
    /// </summary>
    SeriousSamTheSecondEncounter(ServerProtocolType.GameSpy),
    /// <summary>
    /// Aliens vs. Predators 2
    /// </summary>
    AliensVsPredators2(ServerProtocolType.GameSpy),
    /// <summary>
    /// No One Lives Forever
    /// </summary>
    NoOneLivesForever(ServerProtocolType.GameSpy),
    /// <summary>
    /// No One Lives Forever 2
    /// </summary>
    NoOneLivesForever2(ServerProtocolType.GameSpy),
    /// <summary>
    /// Shogo
    /// </summary>
    Shogo(ServerProtocolType.GameSpy),
    /// <summary>
    /// Codename: Eagle
    /// </summary>
    CodenameEagle(ServerProtocolType.GameSpy),
    /// <summary>
    /// Giants: Citizen Kabuto
    /// </summary>
    GiantsCitizenKabuto(ServerProtocolType.GameSpy),
    /// <summary>
    /// Global Operations
    /// </summary>
    GlobalOperations(ServerProtocolType.GameSpy),
    /// <summary>
    /// Nerf ArenaBlast
    /// </summary>
    NerfArenaBlast(ServerProtocolType.GameSpy),
    /// <summary>
    /// RalliSport Challenge
    /// </summary>
    RalliSportChallenge(ServerProtocolType.GameSpy),
    /// <summary>
    /// Rally Masters
    /// </summary>
    RallyMasters(ServerProtocolType.GameSpy),
    /// <summary>
    /// Command and Conquer: Renegade
    /// </summary>
    CommandAndConquerRenegade(ServerProtocolType.GameSpy),
    /// <summary>
    /// Rune
    /// </summary>
    Rune(ServerProtocolType.GameSpy),
    /// <summary>
    /// Sin
    /// </summary>
    Sin(ServerProtocolType.GameSpy),
    /// <summary>
    /// Tactical Ops
    /// </summary>
    TacticalOps(ServerProtocolType.GameSpy),
    /// <summary>
    /// Unreal
    /// </summary>
    Unreal(ServerProtocolType.GameSpy),
    /// <summary>
    /// Wheel of Time
    /// </summary>
    WheelOfTime(ServerProtocolType.GameSpy),
    /// <summary>
    /// Deadly Dozen: Pacific Theater
    /// </summary>
    DeadlyDozenPacificTheater(ServerProtocolType.GameSpy),
    /// <summary>
    /// Dirt Track Racing 2
    /// </summary>
    DirtTrackRacing2(ServerProtocolType.GameSpy),
    /// <summary>
    /// Drakan: Order of the Flame
    /// </summary>
    DrakanOrderOfTheFlame(ServerProtocolType.GameSpy),
    /// <summary>
    /// F1 2002
    /// </summary>
    F12002(ServerProtocolType.GameSpy),
    /// <summary>
    /// Iron Storm
    /// </summary>
    IronStorm(ServerProtocolType.GameSpy),
    /// <summary>
    /// James Bond: Nightfire
    /// </summary>
    JamesBondNightfire(ServerProtocolType.GameSpy),
    /// <summary>
    /// Kingpin
    /// </summary>
    Kingpin(ServerProtocolType.GameSpy),
    /// <summary>
    /// Need for Speed: Hot Pursuit 2
    /// </summary>
    NeedForSpeedHotPursuit2(ServerProtocolType.GameSpy),
    /// <summary>
    /// Redline
    /// </summary>
    Redline(ServerProtocolType.GameSpy),
    /// <summary>
    /// Turok 2
    /// </summary>
    Turok2(ServerProtocolType.GameSpy),
    /// <summary>
    /// Tron 2.0
    /// </summary>
    Tron2(ServerProtocolType.GameSpy),
    /// <summary>
    /// Tony Hawk's Pro Skater 3
    /// </summary>
    TonyHawksProSkater3(ServerProtocolType.GameSpy),
    /// <summary>
    /// Tony Hawk's Pro Skater 4
    /// </summary>
    TonyHawksProSkater4(ServerProtocolType.GameSpy),
    /// <summary>
    /// V8 Supercar Challenge
    /// </summary>
    V8SupercarChallenge(ServerProtocolType.GameSpy),
    /// <summary>
    /// Team Factor
    /// </summary>
    TeamFactor(ServerProtocolType.GameSpy),
    /// <summary>
    /// Rainbow Six
    /// </summary>
    RainbowSix(ServerProtocolType.GameSpy),
    /// <summary>
    /// Rainbow Six: Rogue Spear
    /// </summary>
    RainbowSixRogueSpear(ServerProtocolType.GameSpy),
    /// <summary>
    /// Nitro Family
    /// </summary>
    NitroFamily(ServerProtocolType.GameSpy),
    /// <summary>
    /// Rise of Nations
    /// </summary>
    RiseOfNations(ServerProtocolType.GameSpy),
    /// <summary>
    /// Contract J.A.C.K.
    /// </summary>
    ContractJACK(ServerProtocolType.GameSpy),
    /// <summary>
    /// Homeworld 2
    /// </summary>
    Homeworld2(ServerProtocolType.GameSpy),
    /// <summary>
    /// Breed
    /// </summary>
    Breed(ServerProtocolType.GameSpy2),
    /// <summary>
    /// Operation Flashpoint: Resistance
    /// </summary>
    OperationFlashPointResistance(ServerProtocolType.GameSpy),
    /// <summary>
    /// Star Trek: Bridge Commander
    /// </summary>
    StarTrekBridgeCommander(ServerProtocolType.GameSpy),
    /// <summary>
    /// Tribes: Vengeance
    /// </summary>
    TribesVengeance(ServerProtocolType.GameSpy),
    /// <summary>
    /// Tony Hawk's Underground 2
    /// </summary>
    TonyHawksUnderground2(ServerProtocolType.GameSpy),
    //endregion
    //region Gamespy v2
    /// <summary>
    /// Star Wars: Battlefront
    /// </summary>
    StarWarsBattlefront(ServerProtocolType.GameSpy2),
    /// <summary>
    /// GameSpy 2 (Generic Protocol)
    /// </summary>
    GameSpy2(ServerProtocolType.GameSpy2),
    /// <summary>
    /// Battlefield Vietnam
    /// </summary>
    BattlefieldVietnam(ServerProtocolType.GameSpy2),
    /// <summary>
    /// Painkiller
    /// </summary>
    Painkiller(ServerProtocolType.GameSpy2),
    /// <summary>
    /// Halo: Combat Evolved
    /// </summary>
    HaloCombatEvolved(ServerProtocolType.GameSpy2),
    /// <summary>
    /// America's Army
    /// </summary>
    AmericasArmy(ServerProtocolType.GameSpy2),
    /// <summary>
    /// Neverwinter Nights
    /// </summary>
    NeverwinterNights(ServerProtocolType.GameSpy2),
    /// <summary>
    /// Operation Flashpoint
    /// </summary>
    OperationFlashpoint(ServerProtocolType.GameSpy2),
    //endregion
    //region Hl-Source
    /// <summary>
    /// Source Engine (Generic Protocol)
    /// </summary>
    Source(ServerProtocolType.Source),
    /// <summary>
    /// Half-Life 2
    /// </summary>
    HalfLife2(ServerProtocolType.Source),
    /// <summary>
    /// Counter-Strike: Source
    /// </summary>
    CounterStrikeSource(ServerProtocolType.Source),
    //endregion
    //region Other
    /// <summary>
    /// Not listed game
    /// </summary>
    Unknown(ServerProtocolType.None);
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
