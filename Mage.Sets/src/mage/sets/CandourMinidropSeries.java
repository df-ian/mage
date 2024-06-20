package mage.sets;

import mage.cards.ExpansionSet;
import mage.constants.Rarity;
import mage.constants.SetType;

/**
 * https://candour.glitch.me/cards.html
 */
public class CandourMinidropSeries extends ExpansionSet {

    private static final CandourMinidropSeries instance = new CandourMinidropSeries();

    public static CandourMinidropSeries getInstance() {
        return instance;
    }

    private CandourMinidropSeries() {
        super("Candour Minidrop Series", "CMS", ExpansionSet.buildDate(2024, 6, 20), SetType.CANDOUR);
        this.hasBoosters = false;
        this.hasBasicLands = false;


        cards.add(new SetCardInfo("Transformation", "1", Rarity.MYTHIC, mage.cards.t.Transformation.class));
        cards.add(new SetCardInfo("Acuity", "2", Rarity.MYTHIC, mage.cards.a.Acuity.class));
        cards.add(new SetCardInfo("Cruelty", "3", Rarity.MYTHIC, mage.cards.c.Cruelty.class));
        cards.add(new SetCardInfo("Ire", "4", Rarity.MYTHIC, mage.cards.i.Ire.class));
        cards.add(new SetCardInfo("Verdancy", "5", Rarity.MYTHIC, mage.cards.v.Verdancy.class));


        //TO IMPLEMENT

    }
}
