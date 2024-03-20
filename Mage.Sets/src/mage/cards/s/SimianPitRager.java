package mage.cards.s;

import mage.MageInt;
import mage.abilities.keyword.RiotAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SimianPitRager extends CardImpl {

    public SimianPitRager(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{R}{G}");

        this.subtype.add(SubType.MONKEY);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        // Riot
        this.addAbility(new RiotAbility());

        this.addAbility(TrampleAbility.getInstance());
    }

    private SimianPitRager(final SimianPitRager card) {
        super(card);
    }

    @Override
    public SimianPitRager copy() {
        return new SimianPitRager(this);
    }
}
