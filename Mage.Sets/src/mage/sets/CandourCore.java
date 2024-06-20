package mage.sets;

import mage.cards.ExpansionSet;
import mage.constants.Rarity;
import mage.constants.SetType;

/**
 * https://candour.glitch.me/cards.html
 */
public class CandourCore extends ExpansionSet {

    private static final CandourCore instance = new CandourCore();

    public static CandourCore getInstance() {
        return instance;
    }

    private CandourCore() {
        super("Candour Core", "CA0", ExpansionSet.buildDate(2024, 3, 19), SetType.CANDOUR);
        this.hasBoosters = false;
        this.hasBasicLands = false;


        cards.add(new SetCardInfo("Fervent Trailblazers", "1", Rarity.RARE, mage.cards.f.FerventTrailblazers.class));
        cards.add(new SetCardInfo("Guilded Hierarch", "2", Rarity.RARE, mage.cards.g.GildedHierarch.class));
        cards.add(new SetCardInfo("Blazing Communion", "3", Rarity.UNCOMMON, mage.cards.b.BlazingCommunion.class));
        cards.add(new SetCardInfo("Icy Rebuke", "4", Rarity.UNCOMMON, mage.cards.i.IcyRebuke.class));
        cards.add(new SetCardInfo("Reap the Catacombs", "5", Rarity.UNCOMMON, mage.cards.r.ReapTheCatacombs.class));
        cards.add(new SetCardInfo("Pitiless Execution", "6", Rarity.UNCOMMON, mage.cards.p.PitilessExecution.class));
        cards.add(new SetCardInfo("Gravetide Regent", "7", Rarity.MYTHIC, mage.cards.g.GravetideRegent.class));
        cards.add(new SetCardInfo("Inorganic Portent", "8", Rarity.UNCOMMON, mage.cards.i.InorganicPortent.class));
        cards.add(new SetCardInfo("Abiding Souls", "9", Rarity.UNCOMMON, mage.cards.a.AbidingSouls.class));
        cards.add(new SetCardInfo("Simian Pit-Rager", "10", Rarity.UNCOMMON, mage.cards.s.SimianPitRager.class));
        cards.add(new SetCardInfo("World to Ashes", "11", Rarity.RARE, mage.cards.w.WorldToAshes.class));
        cards.add(new SetCardInfo("Knight of the Tides", "12", Rarity.RARE, mage.cards.k.KnightOfTheTides.class));
        cards.add(new SetCardInfo("Exuberant Imp", "13", Rarity.UNCOMMON, mage.cards.e.ExuberantImp.class));
        cards.add(new SetCardInfo("Fiery Grief", "14", Rarity.UNCOMMON, mage.cards.f.FieryGrief.class));
        cards.add(new SetCardInfo("Cloudcloak Thief", "15", Rarity.RARE, mage.cards.c.CloudcloakThief.class));
        cards.add(new SetCardInfo("Lamplet Companion", "16", Rarity.UNCOMMON, mage.cards.l.LampletCompanion.class));
        cards.add(new SetCardInfo("Sun-blessed Artisan", "17", Rarity.UNCOMMON, mage.cards.s.SunBlessedArtisan.class));
        cards.add(new SetCardInfo("Clockwork Assassin", "18", Rarity.RARE, mage.cards.c.ClockworkAssassin.class));
        cards.add(new SetCardInfo("Genesis Pod", "19", Rarity.MYTHIC, mage.cards.g.GenesisPod.class));
        cards.add(new SetCardInfo("Dawnthread Elk", "20", Rarity.UNCOMMON, mage.cards.d.DawnthreadElk.class));
        cards.add(new SetCardInfo("Predestine", "21", Rarity.UNCOMMON, mage.cards.p.Predestine.class));
        cards.add(new SetCardInfo("Mu Yanling, Seeing Wind", "22", Rarity.MYTHIC, mage.cards.m.MuYanlingSeeingWind.class));
        cards.add(new SetCardInfo("Conduit Mage", "23", Rarity.RARE, mage.cards.c.ConduitMage.class));
        cards.add(new SetCardInfo("Unsettled Compass", "24", Rarity.RARE, mage.cards.u.UnsettledCompass.class));
        cards.add(new SetCardInfo("Animir, Nature's Aegis", "25", Rarity.RARE, mage.cards.a.AnimirNaturesAegis.class));
        cards.add(new SetCardInfo("Solar Sanction", "26", Rarity.UNCOMMON, mage.cards.s.SolarSanction.class));
        cards.add(new SetCardInfo("Lotus-Eyed Selkie", "27", Rarity.RARE, mage.cards.l.LotusEyedSelkie.class));
        cards.add(new SetCardInfo("Senate Loremage", "28", Rarity.RARE, mage.cards.s.SenateLoremage.class));
        cards.add(new SetCardInfo("Najeela, Blossoming Fury", "29", Rarity.MYTHIC, mage.cards.n.NajeelaBlossomingFury.class));
        cards.add(new SetCardInfo("Slaughter Wurm", "30", Rarity.MYTHIC, mage.cards.s.SlaughterWurm.class));
        cards.add(new SetCardInfo("Teferi Akosa", "31", Rarity.MYTHIC, mage.cards.t.TeferiAkosa.class));
        cards.add(new SetCardInfo("Pestermage", "32", Rarity.UNCOMMON, mage.cards.p.Pestermage.class));
        cards.add(new SetCardInfo("Hutali, Poetic Beastcaller", "33", Rarity.MYTHIC, mage.cards.h.HuatliPoeticBeastcaller.class));;
        cards.add(new SetCardInfo("Seasoned Pyromancy", "34", Rarity.UNCOMMON, mage.cards.s.SeasonedPyromancy.class));
        cards.add(new SetCardInfo("Contracted Aetherborn", "35", Rarity.RARE, mage.cards.c.ContractedAetherborn.class));
        cards.add(new SetCardInfo("Stensia Puppeteer", "36", Rarity.RARE, mage.cards.s.StensiaPuppeteer.class));
        cards.add(new SetCardInfo("Twisted Bog", "37", Rarity.RARE, mage.cards.t.TwistedBog.class));
        cards.add(new SetCardInfo("Rending Blaze", "38", Rarity.UNCOMMON, mage.cards.r.RendingBlaze.class));
        cards.add(new SetCardInfo("Otherworldly Affinity", "39", Rarity.UNCOMMON, mage.cards.o.OtherworldlyAffinity.class));
        cards.add(new SetCardInfo("Sarkhan, Triumphant Dragonspeaker", "40", Rarity.RARE, mage.cards.s.SarkhanTriumphantDragonspeaker.class));
        cards.add(new SetCardInfo("Flamespan Regent", "41", Rarity.MYTHIC, mage.cards.f.FlamespanRegent.class));
        cards.add(new SetCardInfo("Ajani, Avenger of the Pride", "42", Rarity.MYTHIC, mage.cards.a.AjaniAvengerOfThePride.class));
        cards.add(new SetCardInfo("Scorchfield Scavenger", "43", Rarity.RARE, mage.cards.s.ScorchfieldScavenger.class));
        cards.add(new SetCardInfo("Sarkhan's Abreaction", "44", Rarity.UNCOMMON, mage.cards.s.SarkhansAbreaction.class));
        cards.add(new SetCardInfo("Composite Myr", "45", Rarity.RARE, mage.cards.c.CompositeMyr.class));
        cards.add(new SetCardInfo("Sanguinary Temptation", "46", Rarity.UNCOMMON, mage.cards.s.SanguinaryTemptation.class));
        cards.add(new SetCardInfo("Abundant Connection", "47", Rarity.RARE, mage.cards.a.AbundantConnection.class));
        cards.add(new SetCardInfo("Presence Unwanted", "48", Rarity.MYTHIC, mage.cards.p.PresenceUnwanted.class));
        cards.add(new SetCardInfo("The Pillars of Elysium", "49", Rarity.MYTHIC, mage.cards.p.PillarsOfElysium.class));
        cards.add(new SetCardInfo("Rotten Rootrager", "50", Rarity.UNCOMMON, mage.cards.r.RottenRootrager.class));
        cards.add(new SetCardInfo("Pernicious Revenant", "51", Rarity.UNCOMMON, mage.cards.p.PerniciousRevenant.class));
        cards.add(new SetCardInfo("Spiteful Journeymage", "52", Rarity.RARE, mage.cards.s.SpitefulJourneymage.class));
        cards.add(new SetCardInfo("Isadora of the Eternal Watch", "53", Rarity.RARE, mage.cards.i.IsadoraOfTheEternalWatch.class));
        cards.add(new SetCardInfo("Bronzeforge Fanatic", "54", Rarity.RARE, mage.cards.b.BronzeforgeFanatic.class));
        cards.add(new SetCardInfo("Squandered Hopes", "55", Rarity.UNCOMMON, mage.cards.s.SquanderedHopes.class));
        cards.add(new SetCardInfo("Coiling Wrath Engine", "56", Rarity.MYTHIC, mage.cards.c.CoilingWrathEngine.class));
        cards.add(new SetCardInfo("Seething Return", "57", Rarity.UNCOMMON, mage.cards.s.SeethingReturn.class));
        cards.add(new SetCardInfo("Corn Nibbler", "58", Rarity.UNCOMMON, mage.cards.c.CornNibbler.class));
        cards.add(new SetCardInfo("Tempest Basilisk", "59", Rarity.UNCOMMON, mage.cards.t.TempestBasilisk.class));
        cards.add(new SetCardInfo("Unravel", "60", Rarity.UNCOMMON, mage.cards.u.Unravel.class));
        cards.add(new SetCardInfo("Sylvan Connection", "61", Rarity.MYTHIC, mage.cards.s.SylvanConnection.class));
        cards.add(new SetCardInfo("Devout Seersmith", "62", Rarity.UNCOMMON, mage.cards.d.DevoutSeersmith.class));
        cards.add(new SetCardInfo("Momentary Evanescence", "63", Rarity.RARE, mage.cards.m.MomentaryEvanescence.class));
        cards.add(new SetCardInfo("Necromantic Study", "64", Rarity.MYTHIC, mage.cards.n.NecromanticStudy.class));
        cards.add(new SetCardInfo("Chromestroke Savant", "65", Rarity.MYTHIC, mage.cards.c.ChromestrokeSavant.class));
        cards.add(new SetCardInfo("Jade Obelisk", "66", Rarity.RARE, mage.cards.j.JadeObelisk.class));
        cards.add(new SetCardInfo("Emphatic Recruiter", "67", Rarity.RARE, mage.cards.e.EmphaticRecruiter.class));
        cards.add(new SetCardInfo("Swiftblade Warden", "68", Rarity.RARE, mage.cards.s.SwiftbladeWarden.class));
        cards.add(new SetCardInfo("Altar of the Dross", "69", Rarity.RARE, mage.cards.a.AltarOfTheDross.class));
        cards.add(new SetCardInfo("Odyssey", "70", Rarity.MYTHIC, mage.cards.o.Odyssey.class));
        cards.add(new SetCardInfo("Rishkar, Lifecraft Revolutionary", "71", Rarity.RARE, mage.cards.r.RishkarLifecraftRevolutionary.class));
        cards.add(new SetCardInfo("Crucible of Loams", "72", Rarity.MYTHIC, mage.cards.c.CrucibleOfLoams.class));
        cards.add(new SetCardInfo("Elven Cradle", "73", Rarity.MYTHIC, mage.cards.e.ElvenCradle.class));
        cards.add(new SetCardInfo("Rotten Find", "74", Rarity.RARE, mage.cards.r.RottenFind.class));
        cards.add(new SetCardInfo("Hedron Mystic", "75", Rarity.RARE, mage.cards.h.HedronMystic.class));
        cards.add(new SetCardInfo("Tamiyo, Truthseeker", "76", Rarity.MYTHIC, mage.cards.t.TamiyoTruthSeeker.class));
        cards.add(new SetCardInfo("Valakut Convergence", "77", Rarity.MYTHIC, mage.cards.v.ValakutConvergence.class));
        cards.add(new SetCardInfo("Chittering Siege", "78", Rarity.UNCOMMON, mage.cards.c.ChitteringSiege.class));
        cards.add(new SetCardInfo("Rapacious Rodent", "79", Rarity.UNCOMMON, mage.cards.r.RapaciousRodent.class));
        cards.add(new SetCardInfo("Ulamog, the Vacuous Famine", "80", Rarity.MYTHIC, mage.cards.u.UlamogTheVaucousFamine.class));
        cards.add(new SetCardInfo("Hordemaster Wight", "81", Rarity.RARE, mage.cards.h.HordemasterWight.class));
        cards.add(new SetCardInfo("Spontaneous Succession", "82", Rarity.MYTHIC, mage.cards.s.SpontaneousSuccession.class));
        cards.add(new SetCardInfo("Seasoned Ascension", "83", Rarity.MYTHIC, mage.cards.s.SeasonedAscension.class));
        cards.add(new SetCardInfo("Titan of Primacy", "84", Rarity.MYTHIC, mage.cards.t.TitanOfPrimacy.class));
        cards.add(new SetCardInfo("Sword of Might and Muster", "85", Rarity.MYTHIC, mage.cards.s.SwordOfMightAndMuster.class));
        cards.add(new SetCardInfo("Fblthp, Lost Leader", "86", Rarity.RARE, mage.cards.f.FblthpLostLeader.class));
        cards.add(new SetCardInfo("Aminatou, Fateshifter Prodigy", "87", Rarity.MYTHIC, mage.cards.a.AminatouFateshifterProdigy.class));
        cards.add(new SetCardInfo("Apex of the Peaks", "88", Rarity.MYTHIC, mage.cards.a.ApexOfThePeaks.class));
        cards.add(new SetCardInfo("Swiftslash Thief", "89", Rarity.RARE, mage.cards.s.SwiftslashThief.class));
        cards.add(new SetCardInfo("Consign to Nothingness", "90", Rarity.RARE, mage.cards.c.ConsignToNothingness.class));
        cards.add(new SetCardInfo("Ironbane Prowler", "91", Rarity.RARE, mage.cards.i.IronbaneProwler.class));
        cards.add(new SetCardInfo("Dina, Vitality Alchemist", "92", Rarity.MYTHIC, mage.cards.d.DinaVitalityAlchemist.class));
        cards.add(new SetCardInfo("Malevolent Artifice", "93", Rarity.RARE, mage.cards.m.MalevolentArtifice.class));
        cards.add(new SetCardInfo("Liliana's Invocation", "94", Rarity.RARE, mage.cards.l.LilianasInvocation.class));
        cards.add(new SetCardInfo("Crimson Fabrications", "95", Rarity.UNCOMMON, mage.cards.c.CrimsonFabrications.class));
        cards.add(new SetCardInfo("Vrista, Titan of Void's Mystery", "96", Rarity.MYTHIC, mage.cards.v.VristaTitanOfVoidsMystery.class));
        cards.add(new SetCardInfo("Undercity Adventurers", "97", Rarity.RARE, mage.cards.u.UndercityAdventurers.class));
        cards.add(new SetCardInfo("Shifting Bonsai", "98", Rarity.MYTHIC, mage.cards.p.PrismaticVista.class));
        cards.add(new SetCardInfo("Undermountain Paragon", "99", Rarity.UNCOMMON, mage.cards.u.UndermountainParagon.class));
        cards.add(new SetCardInfo("Harbinger of Tithes", "100", Rarity.RARE, mage.cards.h.HarbingerOfTithes.class));
        cards.add(new SetCardInfo("Ethereal Dissension", "101", Rarity.UNCOMMON, mage.cards.e.EtherealDissension.class));
        cards.add(new SetCardInfo("Spector Flask", "102", Rarity.UNCOMMON, mage.cards.s.SpectorFlask.class));
        cards.add(new SetCardInfo("Aramis Sandscourge", "103", Rarity.MYTHIC, mage.cards.a.AramisSandscourge.class));
        cards.add(new SetCardInfo("Exalted Solitude", "104", Rarity.RARE, mage.cards.e.ExaltedSolitude.class));
        cards.add(new SetCardInfo("Metamorphic Lantern", "105", Rarity.RARE, mage.cards.m.MetamorphicLantern.class));
        cards.add(new SetCardInfo("Talion's Wispweaver", "106", Rarity.RARE, mage.cards.t.TalionsWispweaver.class));
        cards.add(new SetCardInfo("Azril, Threefold Storm", "107", Rarity.RARE, mage.cards.a.AzrilThreefoldStorm.class));
        cards.add(new SetCardInfo("Lotus Carpet", "108", Rarity.MYTHIC, mage.cards.l.LotusCarpet.class));
        cards.add(new SetCardInfo("Resplendent Acolyte", "109", Rarity.RARE, mage.cards.r.ResplendentAcolyte.class));
        cards.add(new SetCardInfo("Consuming Conviction", "110", Rarity.UNCOMMON, mage.cards.c.ConsumingConviction.class));
        cards.add(new SetCardInfo("Soulscape Scourge", "111", Rarity.RARE, mage.cards.s.SoulscapeScourge.class));
        cards.add(new SetCardInfo("Pride Runemage", "112", Rarity.RARE, mage.cards.p.PrideRunemage.class));
        cards.add(new SetCardInfo("Gitaxian Scrap-Searcher", "115", Rarity.RARE, mage.cards.g.GitaxianScrapSearcher.class));
        cards.add(new SetCardInfo("Archmage's Astrolabe", "116", Rarity.UNCOMMON, mage.cards.a.ArchmagesAstrolabe.class));
        cards.add(new SetCardInfo("Scion of Skithiryx", "117", Rarity.UNCOMMON, mage.cards.s.ScionOfSkithryx.class));
        cards.add(new SetCardInfo("Etherium Vial", "118", Rarity.RARE, mage.cards.e.EtheriumVial.class));
        cards.add(new SetCardInfo("Verdant Slingshot", "119", Rarity.UNCOMMON, mage.cards.v.VerdantSlingshot.class));
        cards.add(new SetCardInfo("Dreamcleaver Hierarch", "120", Rarity.MYTHIC, mage.cards.d.DreamcleaverHierarch.class));
        cards.add(new SetCardInfo("Slinking Scorcher", "121", Rarity.UNCOMMON, mage.cards.s.SlinkingScorcher.class));
        cards.add(new SetCardInfo("Atraxa's Assimilator", "122", Rarity.MYTHIC, mage.cards.a.AtraxasAssimilator.class));
        cards.add(new SetCardInfo("Enigmatic Serpent", "123", Rarity.UNCOMMON, mage.cards.e.EnigmaticSerpent.class));
        cards.add(new SetCardInfo("Rampaging Lhurgoyf", "124", Rarity.MYTHIC, mage.cards.r.RampagingLhurgoyf.class));
        cards.add(new SetCardInfo("Surging Rampage", "125", Rarity.MYTHIC, mage.cards.s.SurgingRampage.class));
        cards.add(new SetCardInfo("Seeker of Secrets", "126", Rarity.UNCOMMON, mage.cards.s.SeekerOfSecrets.class));
        cards.add(new SetCardInfo("Pollenburst Dryad", "127", Rarity.UNCOMMON, mage.cards.p.PollenburstDryad.class));
        cards.add(new SetCardInfo("Tinkerleaf Sprites", "128", Rarity.UNCOMMON, mage.cards.t.TinkerleafSprites.class));
        cards.add(new SetCardInfo("Aeon Ritual", "129", Rarity.UNCOMMON, mage.cards.a.AeonRitual.class));
        cards.add(new SetCardInfo("Call of the Multiverse", "130", Rarity.MYTHIC, mage.cards.c.CallOfTheMultiverse.class));
        cards.add(new SetCardInfo("Glen Elendra Scion", "131", Rarity.RARE, mage.cards.g.GlenElendraScion.class));
        cards.add(new SetCardInfo("Seasoned Fireweaver", "132", Rarity.MYTHIC, mage.cards.s.SeasonedFireweaver.class));
        cards.add(new SetCardInfo("Lochthwain Compleat", "133", Rarity.MYTHIC, mage.cards.l.LochthwainCompleat.class));
        cards.add(new SetCardInfo("Skywhale Umbra", "134", Rarity.RARE, mage.cards.s.SkywhaleUmbra.class));
        cards.add(new SetCardInfo("Hurried Forging", "135", Rarity.RARE, mage.cards.h.HurriedForging.class));
        cards.add(new SetCardInfo("Aetherwing, Ghirapur Flagship", "136", Rarity.MYTHIC, mage.cards.a.AetherwingGiraphurFlagship.class));
        cards.add(new SetCardInfo("Elesh Norn, Annex Regent", "137", Rarity.MYTHIC, mage.cards.e.EleshNornAnnexRegent.class));
        cards.add(new SetCardInfo("Thousand-Legged Deathcoil", "138", Rarity.RARE, mage.cards.t.ThousandLeggedDeathcoil.class));
        cards.add(new SetCardInfo("Road to Exile", "139", Rarity.RARE, mage.cards.r.RoadToExile.class));
        cards.add(new SetCardInfo("Strixhaven Falls", "140", Rarity.RARE, mage.cards.s.StrixhavenFalls.class));
        cards.add(new SetCardInfo("Sunrise Champion", "141", Rarity.RARE, mage.cards.s.SunriseChampion.class));
        cards.add(new SetCardInfo("Adventurous Instinct", "142", Rarity.UNCOMMON, mage.cards.a.AdventurousInstinct.class));
        cards.add(new SetCardInfo("Harrowing Emergence", "143", Rarity.MYTHIC, mage.cards.h.HarrowingEmergence.class));
        cards.add(new SetCardInfo("Throne of Argoth", "144", Rarity.RARE, mage.cards.t.ThroneOfArgoth.class));
        cards.add(new SetCardInfo("Tomakul, Domed Settlement", "145", Rarity.RARE, mage.cards.t.TomakulDomedSettlement.class));
        cards.add(new SetCardInfo("Universal Expertise", "146", Rarity.RARE, mage.cards.u.UniversalExpertise.class));
        cards.add(new SetCardInfo("Suspension Canopy", "147", Rarity.RARE, mage.cards.s.SuspensionCanopy.class));
        cards.add(new SetCardInfo("Leonin Stoneblade", "148", Rarity.RARE, mage.cards.l.LeoninStoneblade.class));
        cards.add(new SetCardInfo("Feywild Witness", "149", Rarity.UNCOMMON, mage.cards.f.FeywildWitness.class));
        cards.add(new SetCardInfo("Necromantic Covenant", "150", Rarity.MYTHIC, mage.cards.n.NecromanticCovenant.class));
        cards.add(new SetCardInfo("Fateweaver", "151", Rarity.RARE, mage.cards.f.Fateweaver.class));
        cards.add(new SetCardInfo("Otherworldly Contrition", "152", Rarity.UNCOMMON, mage.cards.o.OtherworldlyContrition.class));
        cards.add(new SetCardInfo("Portal to Adarkar", "153", Rarity.RARE, mage.cards.p.PortalToAdarkar.class));
        cards.add(new SetCardInfo("Harbinger of Equilibrium", "154", Rarity.RARE, mage.cards.h.HarbingerOfEquilibrium.class));;
        cards.add(new SetCardInfo("Force of Tenacity", "155", Rarity.MYTHIC, mage.cards.f.ForceOfTenacity.class));
        cards.add(new SetCardInfo("Fireblitz", "156", Rarity.MYTHIC, mage.cards.f.Fireblitz.class));
        cards.add(new SetCardInfo("Essence Retrieval", "157", Rarity.RARE, mage.cards.e.EssenceRetrieval.class));
        cards.add(new SetCardInfo("Beastial Majesty", "158", Rarity.RARE, mage.cards.b.BeastialMajesty.class));
        cards.add(new SetCardInfo("Argivian Exemplar", "159", Rarity.RARE, mage.cards.a.ArgivianExemplar.class));
        cards.add(new SetCardInfo("Conjured Corruption", "160", Rarity.MYTHIC, mage.cards.c.ConjuredCorruption.class));
        cards.add(new SetCardInfo("Narcoreaver", "161", Rarity.UNCOMMON, mage.cards.n.Narcoreaver.class));
        cards.add(new SetCardInfo("Villaintown, Nefarious Confluence", "162", Rarity.MYTHIC, mage.cards.v.VillaintownNefariousConfluence.class));
        cards.add(new SetCardInfo("Prosperity, City of Opportunity", "163", Rarity.MYTHIC, mage.cards.p.ProsperityCityOfOpportunity.class));
        cards.add(new SetCardInfo("Cyntha, Lineweaver Adept", "164", Rarity.MYTHIC, mage.cards.c.CynthaLineweaverAdept.class));
        cards.add(new SetCardInfo("Heliod, Resplendent Omen", "165", Rarity.MYTHIC, mage.cards.h.HeliodResplendentOmen.class));
        cards.add(new SetCardInfo("Hedron Valkyrie", "166", Rarity.UNCOMMON, mage.cards.h.HedronValkyrie.class));
        cards.add(new SetCardInfo("Elspeth Fells the Compleated", "167", Rarity.MYTHIC, mage.cards.e.ElspethFellsTheCompleated.class));
        cards.add(new SetCardInfo("Magister of Vindication", "168", Rarity.RARE, mage.cards.m.MagisterOfVindication.class));
        cards.add(new SetCardInfo("Elegant Appropriation", "169", Rarity.MYTHIC, mage.cards.e.ElegantAppropriation.class));
        cards.add(new SetCardInfo("River Coatl", "170", Rarity.RARE, mage.cards.r.RiverCoatl.class));
        cards.add(new SetCardInfo("Vitality Cleave", "171", Rarity.UNCOMMON, mage.cards.v.VitalityCleave.class));
        cards.add(new SetCardInfo("Resolute Fealty", "172", Rarity.RARE, mage.cards.r.ResoluteFealty.class));
        cards.add(new SetCardInfo("Rhox Warchief", "173", Rarity.RARE, mage.cards.r.RhoxWarchief.class));
        cards.add(new SetCardInfo("Bittergale Sliver", "174", Rarity.RARE, mage.cards.b.BittergaleSliver.class));
        cards.add(new SetCardInfo("Mana Reshaper", "177", Rarity.MYTHIC, mage.cards.m.ManaReshaper.class));
        cards.add(new SetCardInfo("Splitcaster Mage", "178", Rarity.MYTHIC, mage.cards.s.SplitcasterMage.class));
        cards.add(new SetCardInfo("Agonizing Edict", "190", Rarity.UNCOMMON, mage.cards.a.AgonizingEdict.class));
        cards.add(new SetCardInfo("Tajuru Relic Seeker", "193", Rarity.RARE, mage.cards.t.TajuruRelicSeeker.class));
        cards.add(new SetCardInfo("Clockwork Curio", "201", Rarity.RARE, mage.cards.c.ClockworkCurio.class));
        cards.add(new SetCardInfo("Flickerfowl", "206", Rarity.UNCOMMON, mage.cards.f.Flickerfowl.class));
        cards.add(new SetCardInfo("Stalking Vipercat", "207", Rarity.UNCOMMON, mage.cards.s.StalkingVipercat.class));
        cards.add(new SetCardInfo("Saheeli's Splendor", "208", Rarity.RARE, mage.cards.s.SaheelisSplendor.class));
        cards.add(new SetCardInfo("Second Endeavour", "209", Rarity.UNCOMMON, mage.cards.s.SecondEndeavour.class));
        cards.add(new SetCardInfo("Aurelia's Renown", "210", Rarity.RARE, mage.cards.a.AureliasRenown.class));
        cards.add(new SetCardInfo("Midnight Beastcaller", "216", Rarity.RARE, mage.cards.m.MidnightBeastcaller.class));
        cards.add(new SetCardInfo("Tasigur, Eternal Tyrant", "224", Rarity.RARE, mage.cards.t.TasigurEternalTyrant.class));
        cards.add(new SetCardInfo("Kolaghan, Thunderous Regent", "227", Rarity.MYTHIC, mage.cards.k.KolaghanThunderousRegent.class));
        cards.add(new SetCardInfo("Fae Court of Tuinvale", "228", Rarity.MYTHIC, mage.cards.f.FaeCourtOfTuinvale.class));
        cards.add(new SetCardInfo("Collected Construction", "229", Rarity.RARE, mage.cards.c.CollectedConstruction.class));
        cards.add(new SetCardInfo("Deathless Arbiter", "230", Rarity.RARE, mage.cards.d.DeathlessArbiter.class));
        cards.add(new SetCardInfo("Galepack Alpha", "231", Rarity.RARE, mage.cards.g.GalepackAlpha.class));
        cards.add(new SetCardInfo("Remorseful Savior", "232", Rarity.UNCOMMON, mage.cards.r.RemorsefulSavior.class));
        cards.add(new SetCardInfo("Tibalt's Wager", "233", Rarity.UNCOMMON, mage.cards.t.TibaltsWager.class));
        cards.add(new SetCardInfo("Maddening Revelation", "234", Rarity.UNCOMMON, mage.cards.m.MaddeningRevelation.class));
        cards.add(new SetCardInfo("Tibalt, Chaotic Abolisher", "235", Rarity.RARE, mage.cards.t.TibaltChaoticAbolisher.class));
        cards.add(new SetCardInfo("Pilgrim of the Wind", "236", Rarity.RARE, mage.cards.p.PilgrimOfTheWind.class));
        cards.add(new SetCardInfo("Morbid Seamstress", "237", Rarity.UNCOMMON, mage.cards.m.MorbidSeamstress.class));
        cards.add(new SetCardInfo("Summoner of the Swarm", "238", Rarity.RARE, mage.cards.s.SummonerOfTheSwarm.class));
        cards.add(new SetCardInfo("Loxodon Naturalist", "239", Rarity.RARE, mage.cards.l.LoxodonNaturalist.class));
        cards.add(new SetCardInfo("Volatile Blade", "240", Rarity.UNCOMMON, mage.cards.v.VolatileBlade.class));
        cards.add(new SetCardInfo("Will and Rowan, Desparked", "241", Rarity.RARE, mage.cards.w.WillAndRowanDesparked.class));
        cards.add(new SetCardInfo("Magus of the Chalice", "242", Rarity.MYTHIC, mage.cards.m.MagusOfTheChalice.class));
        cards.add(new SetCardInfo("Disruptive Schemes", "243", Rarity.RARE, mage.cards.d.DisruptiveSchemes.class));
        cards.add(new SetCardInfo("Elspeth, Godsend", "244", Rarity.MYTHIC, mage.cards.e.ElspethGodsend.class));
        cards.add(new SetCardInfo("Rift Colossus", "245", Rarity.MYTHIC, mage.cards.r.RiftColossus.class));
        cards.add(new SetCardInfo("Sigarda, Nyxbloom Herald", "246", Rarity.MYTHIC, mage.cards.s.SigardaNyxbloomHerald.class));
        cards.add(new SetCardInfo("Thunderous Winder", "247", Rarity.UNCOMMON, mage.cards.t.ThunderousWinder.class));
        cards.add(new SetCardInfo("Devilshroud Veil", "248", Rarity.RARE, mage.cards.d.DevilshroudVeil.class));
        cards.add(new SetCardInfo("Impermanate", "249", Rarity.UNCOMMON, mage.cards.i.Impermanate.class));
        cards.add(new SetCardInfo("Volatile Dunes", "250", Rarity.RARE, mage.cards.v.VolatileDunes.class));
        cards.add(new SetCardInfo("Torpor Summit", "251", Rarity.RARE, mage.cards.t.TorporSummit.class));
        cards.add(new SetCardInfo("Starlit Cairn", "252", Rarity.RARE, mage.cards.s.StarlitCairn.class));
        cards.add(new SetCardInfo("Cranial Clamp", "253", Rarity.RARE, mage.cards.c.CranialClamp.class));
        cards.add(new SetCardInfo("Seal of Phimyrium", "254", Rarity.MYTHIC, mage.cards.s.SealOfPhimyrium.class));
        cards.add(new SetCardInfo("Seal of Primorthus", "255", Rarity.MYTHIC, mage.cards.s.SealOfPrimorthus.class));
        cards.add(new SetCardInfo("Rotting Witness", "256", Rarity.UNCOMMON, mage.cards.r.RottingWitness.class));
        cards.add(new SetCardInfo("Beastcaller Shaman", "257", Rarity.RARE, mage.cards.b.BeastcallerShaman.class));
        cards.add(new SetCardInfo("Nimble Opportunist", "258", Rarity.UNCOMMON, mage.cards.n.NimbleOpportunist.class));
        cards.add(new SetCardInfo("Universal Predation", "259", Rarity.MYTHIC, mage.cards.u.UniversalPredation.class));
        cards.add(new SetCardInfo("Splintering Agony", "260", Rarity.MYTHIC, mage.cards.s.SplinteringAgony.class));
        cards.add(new SetCardInfo("Verdant Affinity", "261", Rarity.MYTHIC, mage.cards.v.VerdantAffinity.class));
        cards.add(new SetCardInfo("Necrotic Spellbomb", "262", Rarity.UNCOMMON, mage.cards.n.NecroticSpellbomb.class));
        cards.add(new SetCardInfo("Nahiri, Stoneforge Artisan", "263", Rarity.MYTHIC, mage.cards.n.NahiriStoneforgeArtisan.class));
        cards.add(new SetCardInfo("Lotus of Turmoil", "264", Rarity.MYTHIC, mage.cards.l.LotusOfTurmoil.class));
        cards.add(new SetCardInfo("Demented Recollections", "265", Rarity.UNCOMMON, mage.cards.d.DementedRecollections.class));
        cards.add(new SetCardInfo("Void Shard", "266", Rarity.UNCOMMON, mage.cards.v.VoidShard.class));
        cards.add(new SetCardInfo("Angelic Wrath", "268", Rarity.RARE, mage.cards.a.AngelicWrath.class));
        cards.add(new SetCardInfo("Whispering Henge", "269", Rarity.RARE, mage.cards.w.WhisperingHenge.class));
        cards.add(new SetCardInfo("Ruinous Sands", "270", Rarity.RARE, mage.cards.r.RuinousSands.class));
        cards.add(new SetCardInfo("Ordruun Reckoner", "271", Rarity.MYTHIC, mage.cards.o.OrdruunReckoner.class));
        cards.add(new SetCardInfo("Blighted Inquisition", "272", Rarity.RARE, mage.cards.b.BlightedInquisition.class));
        cards.add(new SetCardInfo("Descent of the Wicked Slumber", "273", Rarity.UNCOMMON, mage.cards.d.DescentOfTheWickedSlumber.class));
        cards.add(new SetCardInfo("Tomakul, Domed Settlement", "274", Rarity.MYTHIC, mage.cards.t.TomakulDomedSettlement.class));
        cards.add(new SetCardInfo("Servoswipe Adept", "275", Rarity.RARE, mage.cards.s.ServoswipeAdept.class));
        cards.add(new SetCardInfo("Slumbering Greatwurm", "276", Rarity.MYTHIC, mage.cards.s.SlumberingGreatwurm.class));
        cards.add(new SetCardInfo("Diviner's Terrarium", "277", Rarity.UNCOMMON, mage.cards.d.DivinersTerrarium.class));
        cards.add(new SetCardInfo("Chromatic Lotus", "278", Rarity.UNCOMMON, mage.cards.c.ChromaticLotus.class));
        cards.add(new SetCardInfo("Aethercatch Relic", "279", Rarity.UNCOMMON, mage.cards.a.AethercatchRelic.class));
        cards.add(new SetCardInfo("Cloudpierce Monument", "280", Rarity.RARE, mage.cards.c.CloudpierceMonument.class));
        cards.add(new SetCardInfo("Volatile Crypt", "281", Rarity.UNCOMMON, mage.cards.v.VolatileCrypt.class));
        cards.add(new SetCardInfo("Sylvan Charm", "282", Rarity.RARE, mage.cards.s.SylvanCharm.class));
        cards.add(new SetCardInfo("Aetherpulse Dryad", "283", Rarity.UNCOMMON, mage.cards.a.AetherpulseDryad.class));
        cards.add(new SetCardInfo("Mismatch", "284", Rarity.UNCOMMON, mage.cards.m.Mismatch.class));
        cards.add(new SetCardInfo("Devastating Zenith", "285", Rarity.MYTHIC, mage.cards.d.DevastatingZenith.class));
        cards.add(new SetCardInfo("Titan of Phyresis", "286", Rarity.MYTHIC, mage.cards.t.TitanOfPhyresis.class));
        cards.add(new SetCardInfo("Hexclasp", "287", Rarity.UNCOMMON, mage.cards.h.Hexclasp.class));
        cards.add(new SetCardInfo("Jade Sphinx", "288", Rarity.RARE, mage.cards.j.JadeSphinx.class));
        cards.add(new SetCardInfo("Worldsoul Preserver", "289", Rarity.RARE, mage.cards.w.WorldsoulPreserver.class));
        cards.add(new SetCardInfo("Bloodcaster Prodigy", "290", Rarity.RARE, mage.cards.b.BloodcasterProdigy.class));
        cards.add(new SetCardInfo("Amphibious Rootwalla", "291", Rarity.UNCOMMON, mage.cards.a.AmphibiousRootwalla.class));
        cards.add(new SetCardInfo("Bitter Ophiomancer", "292", Rarity.MYTHIC, mage.cards.b.BitterOphiomancer.class));
        cards.add(new SetCardInfo("Nissa, Animist Supreme", "293", Rarity.MYTHIC, mage.cards.n.NissaAnimistSupreme.class));
        cards.add(new SetCardInfo("Enhanced Renegade", "294", Rarity.RARE, mage.cards.e.EnhancedRenegade.class));
        cards.add(new SetCardInfo("Persistent Combatant", "295", Rarity.RARE, mage.cards.p.PersistentCombatant.class));
        cards.add(new SetCardInfo("Aurelia, Relentless Vindicator", "296", Rarity.MYTHIC, mage.cards.a.AureliaRelentlessVindicator.class));
        cards.add(new SetCardInfo("Nyxbloom Flourishing", "297", Rarity.RARE, mage.cards.n.NyxbloomFlourishing.class));
        cards.add(new SetCardInfo("Resurgent Phoenix", "298", Rarity.RARE, mage.cards.a.AshenPhoenix.class));
        cards.add(new SetCardInfo("Bloodline Skystalker", "299", Rarity.RARE, mage.cards.b.BloodlineSkystalker.class));
        cards.add(new SetCardInfo("Morass Keeper", "300", Rarity.RARE, mage.cards.m.MorassKeeper.class));


        cards.add(new SetCardInfo("Godless Shrine", "301", Rarity.RARE, mage.cards.g.GodlessShrine.class));
        cards.add(new SetCardInfo("Watery Grave", "302", Rarity.RARE, mage.cards.w.WateryGrave.class));
        cards.add(new SetCardInfo("Steam Vents", "303", Rarity.RARE, mage.cards.s.SteamVents.class));
        cards.add(new SetCardInfo("Blood Crypt", "304", Rarity.RARE, mage.cards.b.BloodCrypt.class));
        cards.add(new SetCardInfo("Overgrown Tomb", "305", Rarity.RARE, mage.cards.o.OvergrownTomb.class));
        cards.add(new SetCardInfo("Stomping Ground", "306", Rarity.RARE, mage.cards.s.StompingGround.class));
        cards.add(new SetCardInfo("Sacred Foundry", "307", Rarity.RARE, mage.cards.s.SacredFoundry.class));
        cards.add(new SetCardInfo("Wandervine Heights", "308", Rarity.RARE, mage.cards.t.TempleGarden.class));
        cards.add(new SetCardInfo("Breeding Pool", "309", Rarity.RARE, mage.cards.b.BreedingPool.class));
        cards.add(new SetCardInfo("Blackcleave Cliffs", "310", Rarity.RARE, mage.cards.b.BlackcleaveCliffs.class));
        cards.add(new SetCardInfo("Blooming Marsh", "311", Rarity.RARE, mage.cards.b.BloomingMarsh.class));
        cards.add(new SetCardInfo("Botanical Sanctum", "312", Rarity.RARE, mage.cards.b.BotanicalSanctum.class));
        cards.add(new SetCardInfo("Concealed Courtyard", "313", Rarity.RARE, mage.cards.c.ConcealedCourtyard.class));
        cards.add(new SetCardInfo("Copperline Gorge", "314", Rarity.RARE, mage.cards.c.CopperlineGorge.class));
        cards.add(new SetCardInfo("Darkslick Shores", "315", Rarity.RARE, mage.cards.d.DarkslickShores.class));
        cards.add(new SetCardInfo("Inspiring Vantage", "316", Rarity.RARE, mage.cards.i.InspiringVantage.class));
        cards.add(new SetCardInfo("Razorverge Thicket", "317", Rarity.RARE, mage.cards.r.RazorvergeThicket.class));
        cards.add(new SetCardInfo("Seachrome Coast", "318", Rarity.RARE, mage.cards.s.SeachromeCoast.class));
        cards.add(new SetCardInfo("Spirebluff Canal", "319", Rarity.RARE, mage.cards.s.SpirebluffCanal.class));
        cards.add(new SetCardInfo("Adarkar Wastes", "320", Rarity.RARE, mage.cards.a.AdarkarWastes.class));
        cards.add(new SetCardInfo("Battlefield Forge", "321", Rarity.RARE, mage.cards.b.BattlefieldForge.class));
        cards.add(new SetCardInfo("Brushland", "322", Rarity.RARE, mage.cards.b.Brushland.class));
        cards.add(new SetCardInfo("Caves of Koilos", "323", Rarity.RARE, mage.cards.c.CavesOfKoilos.class));
        cards.add(new SetCardInfo("Karplusan Forest", "324", Rarity.RARE, mage.cards.k.KarplusanForest.class));
        cards.add(new SetCardInfo("Llanowar Wastes", "325", Rarity.RARE, mage.cards.l.LlanowarWastes.class));
        cards.add(new SetCardInfo("Shivan Reef", "326", Rarity.RARE, mage.cards.s.ShivanReef.class));
        cards.add(new SetCardInfo("Sulfurous Springs", "327", Rarity.RARE, mage.cards.s.SulfurousSprings.class));
        cards.add(new SetCardInfo("Underground River", "328", Rarity.RARE, mage.cards.u.UndergroundRiver.class));
        cards.add(new SetCardInfo("Yavimaya Coast", "329", Rarity.RARE, mage.cards.y.YavimayaCoast.class));
        cards.add(new SetCardInfo("Rimeshard Basin", "330", Rarity.RARE, mage.cards.h.HallowedFountain.class));

        //TO IMPLEMENT
        //Kaya Cassir
        //Liliana, Ambitious Healer
        //Imperial Bloodscribe
        //Nightmarish Ravager
        //Astral Projection
        //Seething Summoner
        //Schismatizing Iteration
        //Filken, Midnight Commander
        //Arcavios Dueling Peaks
        //Bloodthirsty Coercion
        //Basri, Steadfast Paladin

    }
}
