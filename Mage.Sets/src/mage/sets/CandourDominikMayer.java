package mage.sets;

import mage.cards.ExpansionSet;
import mage.constants.Rarity;
import mage.constants.SetType;

/**
 * https://candour.glitch.me/cards.html
 */
public class CandourDominikMayer extends ExpansionSet {

    private static final CandourDominikMayer instance = new CandourDominikMayer();

    public static CandourDominikMayer getInstance() {
        return instance;
    }

    private CandourDominikMayer() {
        super("Candour - Dominik Mayer Anthology", "CA1", ExpansionSet.buildDate(2024, 6, 20), SetType.CANDOUR);
        this.hasBoosters = false;
        this.hasBasicLands = false;


        cards.add(new SetCardInfo("Bloodknight's Invocation", "1", Rarity.MYTHIC, mage.cards.b.BloodknightsInvocation.class));
        cards.add(new SetCardInfo("Firebird's Invocation", "2", Rarity.MYTHIC, mage.cards.f.FirebirdsInvocation.class));
        cards.add(new SetCardInfo("Hallowhunt Invocation", "3", Rarity.MYTHIC, mage.cards.h.HallowhuntInvocation.class));
        cards.add(new SetCardInfo("Oracle's Invocation", "4", Rarity.MYTHIC, mage.cards.o.OraclesInvocation.class));
        cards.add(new SetCardInfo("Phantomrider's Invocation", "5", Rarity.MYTHIC, mage.cards.p.PhantomridersInvocation.class));
        cards.add(new SetCardInfo("Pyrrhic Invocation", "6", Rarity.MYTHIC, mage.cards.p.PyrrhicInvocation.class));
        cards.add(new SetCardInfo("Radiant Invocation", "7", Rarity.MYTHIC, mage.cards.r.RadiantInvocation.class));
        cards.add(new SetCardInfo("Rotwalker's Invocation", "8", Rarity.MYTHIC, mage.cards.r.RotwalkersInvocation.class));
        cards.add(new SetCardInfo("Sunguard's Invocation", "9", Rarity.MYTHIC, mage.cards.s.SunguardsInvocation.class));
        cards.add(new SetCardInfo("Voidcaller's Invocation", "10", Rarity.MYTHIC, mage.cards.v.VoidcallersInvocation.class));

    }
}
