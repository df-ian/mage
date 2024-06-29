package mage.cards.v;

import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.costs.common.ExileFromHandCost;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.counter.DistributeCountersEffect;
import mage.abilities.effects.common.discard.DiscardControllerEffect;
import mage.abilities.keyword.EvokeAbility;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.counters.CounterType;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.target.common.TargetCardInHand;
import mage.target.common.TargetCreaturePermanentAmount;

import java.util.UUID;

/**
 * @author Ian
 */
public final class Verdancy extends CardImpl {

    private static final FilterCard filter2 = new FilterCard("a green card from your hand");

    static {
        filter2.add(new ColorPredicate(ObjectColor.GREEN));
    }

    public Verdancy(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{G}{G}");

        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.INCARNATION);
        this.power = new MageInt(3);
        this.toughness = new MageInt(4);

        this.addAbility(TrampleAbility.getInstance());


        // When Solitude enters the battlefield, exile up to one other target creature. That creature's controller gains life equal to its power.
        Ability ability = new EntersBattlefieldTriggeredAbility(new DistributeCountersEffect(CounterType.P1P1, 4, false, "any number of target creatures you control"), false);
        ability.addTarget(new TargetCreaturePermanentAmount(4, StaticFilters.FILTER_CONTROLLED_CREATURES));
        this.addAbility(ability);

        // Evoke—Exile a white card from your hand.
        this.addAbility(new EvokeAbility(new ExileFromHandCost(new TargetCardInHand(filter2))));
    }

    private Verdancy(final Verdancy card) {
        super(card);
    }

    @Override
    public Verdancy copy() {
        return new Verdancy(this);
    }
}
