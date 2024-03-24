package mage.game.permanent.token;

import mage.MageInt;
import mage.constants.CardType;
import mage.constants.SubType;

public class WhisperingHengeToken extends TokenImpl {
    public WhisperingHengeToken() {
        super("Wraith Token", "1/1 black Wraith creature token");
        cardType.add(CardType.CREATURE);
        color.setBlack(true);
        subtype.add(SubType.WRAITH);
        power = new MageInt(1);
        toughness = new MageInt(1);
    }

    private WhisperingHengeToken(final WhisperingHengeToken token) {
        super(token);
    }

    @Override
    public WhisperingHengeToken copy() {
        return new WhisperingHengeToken(this);
    }
}