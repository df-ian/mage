package mage.cards.t;

import mage.MageInt;
import mage.abilities.common.CanBlockOnlyFlyingAbility;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.effects.common.DrawDiscardControllerEffect;
import mage.abilities.effects.common.ReturnToHandTargetEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.AdventureCard;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ComparisonType;
import mage.constants.SubType;
import mage.constants.TargetController;
import mage.counters.CounterType;
import mage.filter.FilterPermanent;
import mage.filter.FilterSpell;
import mage.filter.common.FilterNonlandPermanent;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.target.TargetPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ThunderousWinder extends AdventureCard {

    private static FilterSpell filter = new FilterSpell("spell with mana value 4 or greater");

    static {
        filter.add(new ManaValuePredicate(ComparisonType.OR_GREATER, 4));
    }


    public ThunderousWinder(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, new CardType[]{CardType.SORCERY}, "{1}{G}", "Coiling Inquiry", "{U}");

        this.subtype.add(SubType.SERPENT);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        this.addAbility(TrampleAbility.getInstance());

        this.addAbility(new SpellCastControllerTriggeredAbility(new AddCountersSourceEffect(CounterType.P1P1.createInstance(2)), filter, false));

        // Petty Theft
        // Return target nonland permanent an opponent controls to its owner's hand.
        this.getSpellCard().getSpellAbility().addEffect(new DrawDiscardControllerEffect(2, 2));

        this.finalizeAdventure();
    }

    private ThunderousWinder(final ThunderousWinder card) {
        super(card);
    }

    @Override
    public ThunderousWinder copy() {
        return new ThunderousWinder(this);
    }
}
