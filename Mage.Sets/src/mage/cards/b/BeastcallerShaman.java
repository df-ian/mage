package mage.cards.b;

import mage.MageInt;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.effects.common.DrawDiscardControllerEffect;
import mage.abilities.effects.common.ExileTopXMayPlayUntilEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.AdventureCard;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ComparisonType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.counters.CounterType;
import mage.filter.FilterSpell;
import mage.filter.common.FilterCreatureSpell;
import mage.filter.predicate.mageobject.ManaValuePredicate;

import java.util.UUID;

/**
 * @author Ian
 */
public final class BeastcallerShaman extends AdventureCard {

    private static FilterSpell filter = new FilterCreatureSpell();



    public BeastcallerShaman(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, new CardType[]{CardType.INSTANT}, "{1}{G}", "Tame the Beast", "{R}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SHAMAN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        this.addAbility(new SpellCastControllerTriggeredAbility(new AddCountersSourceEffect(CounterType.P1P1.createInstance(1)), filter, false));

        this.getSpellCard().getSpellAbility().addEffect(new ExileTopXMayPlayUntilEffect(1, Duration.UntilYourNextEndStep));

        this.finalizeAdventure();
    }

    private BeastcallerShaman(final BeastcallerShaman card) {
        super(card);
    }

    @Override
    public BeastcallerShaman copy() {
        return new BeastcallerShaman(this);
    }
}
