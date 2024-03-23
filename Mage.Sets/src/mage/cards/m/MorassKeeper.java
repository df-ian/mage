package mage.cards.m;

import mage.MageInt;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.delayed.ReflexiveTriggeredAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.DoWhenCostPaid;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.FightTargetSourceEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.DoctorsCompanionAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.counters.CounterType;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.DefendingPlayerControlsSourceAttackingPredicate;
import mage.target.TargetPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class MorassKeeper extends CardImpl {

    public MorassKeeper(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{B/G}");

        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.SHAMAN);
        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        // Nitro-9 -- Whenever Ace, Fearless Rebel attacks, you may sacrifice an artifact. When you do, put a +1/+1 counter on Ace, Fearless Rebel, then it fights up to one target creature defending player controls.
        ReflexiveTriggeredAbility ability = new ReflexiveTriggeredAbility(
                new AddCountersSourceEffect(CounterType.P1P1.createInstance(), false), false
        );
        ability.addEffect(new DrawCardSourceControllerEffect(1)
                .setText("and draw a card"));
        this.addAbility(new AttacksTriggeredAbility(new DoWhenCostPaid(
                ability,
                new SacrificeTargetCost(StaticFilters.FILTER_CONTROLLED_PERMANENT_A_LAND),
                "Sacrifice a land?"
        )));
    }

    private MorassKeeper(final MorassKeeper card) {
        super(card);
    }

    @Override
    public MorassKeeper copy() {
        return new MorassKeeper(this);
    }
}
