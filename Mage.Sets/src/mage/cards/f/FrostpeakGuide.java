package mage.cards.f;

import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.costs.common.RemoveCountersSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.counters.CounterType;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class FrostpeakGuide extends CardImpl {

    public FrostpeakGuide(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{G}{U}");

        this.supertype.add(SuperType.SNOW);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SCOUT);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        this.addAbility(TrampleAbility.getInstance());
        this.addAbility(new WardAbility(new ManaCostsImpl<>("{2}")));

        // Whenever you cast a creature spell, draw a card.
        this.addAbility(new SpellCastControllerTriggeredAbility(
                new AddCountersSourceEffect(CounterType.P1P1.createInstance(1)),
                StaticFilters.FILTER_SPELL_A_CREATURE, false
        ));

        this.addAbility(new SimpleActivatedAbility(new DrawCardSourceControllerEffect(1), new RemoveCountersSourceCost(CounterType.P1P1.createInstance(2))));
    }

    private FrostpeakGuide(final FrostpeakGuide card) {
        super(card);
    }

    @Override
    public FrostpeakGuide copy() {
        return new FrostpeakGuide(this);
    }
}
