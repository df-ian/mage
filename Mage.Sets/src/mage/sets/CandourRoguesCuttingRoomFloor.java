package mage.sets;

import mage.cards.ExpansionSet;
import mage.constants.Rarity;
import mage.constants.SetType;

/**
 * https://candour.glitch.me/cards.html
 */
public class CandourRoguesCuttingRoomFloor extends ExpansionSet {

    private static final CandourRoguesCuttingRoomFloor instance = new CandourRoguesCuttingRoomFloor();

    public static CandourRoguesCuttingRoomFloor getInstance() {
        return instance;
    }

    private CandourRoguesCuttingRoomFloor() {
        super("Candour - Rogue's Cutting Room Floor", "CA2", ExpansionSet.buildDate(2024, 6, 20), SetType.CANDOUR);
        this.hasBoosters = false;
        this.hasBasicLands = false;


        cards.add(new SetCardInfo("Fervent Trailblazers", "1", Rarity.RARE, mage.cards.f.FerventTrailblazers.class));

        //TO IMPLEMENT

    }
}
