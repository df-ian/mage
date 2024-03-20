package mage.cards.s;

import mage.MageInt;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class StalkingVipercat extends CardImpl {

    public StalkingVipercat(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G}");

        this.subtype.add(SubType.SNAKE);
        this.subtype.add(SubType.CAT);
        this.power = new MageInt(4);
        this.toughness = new MageInt(3);


        this.addAbility(TrampleAbility.getInstance());
    }

    private StalkingVipercat(final StalkingVipercat card) {
        super(card);
    }

    @Override
    public StalkingVipercat copy() {
        return new StalkingVipercat(this);
    }
}
