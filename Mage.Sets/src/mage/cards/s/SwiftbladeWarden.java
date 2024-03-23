
package mage.cards.s;

import mage.MageInt;
import mage.abilities.common.SpellCastAllTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.counters.CounterType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SwiftbladeWarden extends CardImpl {

    public SwiftbladeWarden(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{G}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.KNIGHT);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // Trample
        this.addAbility(TrampleAbility.getInstance());
        this.addAbility(new WardAbility(new ManaCostsImpl<>("{2}")));

        // Whenever a player casts a spell, put a +1/+1 counter on Managorger Hydra.
        this.addAbility(new SpellCastAllTriggeredAbility(new AddCountersSourceEffect(CounterType.P1P1.createInstance()), false));
    }

    private SwiftbladeWarden(final SwiftbladeWarden card) {
        super(card);
    }

    @Override
    public SwiftbladeWarden copy() {
        return new SwiftbladeWarden(this);
    }
}
