package mage.game.permanent.token;

import mage.MageInt;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 * @author LoneFox
 */
public final class PartyToken extends TokenImpl {

    public PartyToken() {
        super("Party Token", "1/1 white Human creature token with \"This creature is also a Cleric, Rogue, Wizard, and Warrior\"");
        cardType.add(CardType.CREATURE);
        color.setWhite(true);
        subtype.add(SubType.HUMAN);
        subtype.add(SubType.WARRIOR);
        subtype.add(SubType.CLERIC);
        subtype.add(SubType.ROGUE);
        subtype.add(SubType.WIZARD);
        power = new MageInt(1);
        toughness = new MageInt(1);
    }

    private PartyToken(final PartyToken token) {
        super(token);
    }

    @Override
    public PartyToken copy() {
        return new PartyToken(this);
    }
}
