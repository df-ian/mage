package mage.cards.b;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.common.DealsDamageToAPlayerAllTriggeredAbility;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SetTargetPointer;
import mage.constants.SubType;
import mage.counters.CounterType;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.game.permanent.token.BloodToken;

import java.util.UUID;

/**
 * @author Ian
 */
public final class BloodcasterProdigy extends CardImpl {


    public BloodcasterProdigy(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{B}");

        this.subtype.add(SubType.VAMPIRE);
        this.subtype.add(SubType.WIZARD);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        this.addAbility(FlyingAbility.getInstance());
        // Menace
        this.addAbility(new MenaceAbility(false));

        // Whenever a Knight you control deals combat damage to a player, put a +1/+1 counter on that creature and create a Blood token.
        DoIfCostPaid e = new DoIfCostPaid(new DrawCardSourceControllerEffect(1), new PayLifeCost(3));
        e.addEffect(new CreateTokenEffect(new BloodToken()).concatBy("and"));
        Ability ability = new DealsCombatDamageToAPlayerTriggeredAbility(e, false);
        this.addAbility(ability);
    }

    private BloodcasterProdigy(final BloodcasterProdigy card) {
        super(card);
    }

    @Override
    public BloodcasterProdigy copy() {
        return new BloodcasterProdigy(this);
    }
}
