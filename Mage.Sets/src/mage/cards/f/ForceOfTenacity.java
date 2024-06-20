package mage.cards.f;

import mage.ObjectColor;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.AlternativeCostSourceAbility;
import mage.abilities.costs.common.ExileFromHandCost;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.hint.ConditionHint;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.counters.Counter;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.common.FilterOwnedCard;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.target.TargetSpell;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCardInHand;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ForceOfTenacity extends CardImpl {

    private static final FilterControlledPermanent filter = new FilterControlledPermanent("you control a Wizard");
    private static final FilterOwnedCard filter2
            = new FilterOwnedCard("a blue card from your hand");

    static {
        filter.add(SubType.WIZARD.getPredicate());
        filter2.add(new ColorPredicate(ObjectColor.BLUE));
    }

    public ForceOfTenacity(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{1}{U}{U}");

        // Wizard's Lightning costs {2} less to cast if you control a Wizard.
        Condition condition = new PermanentsOnTheBattlefieldCondition(filter);
        AlternativeCostSourceAbility altCost = new AlternativeCostSourceAbility(new PayLifeCost(1), condition);
        altCost.addCost(new ExileFromHandCost(new TargetCardInHand(filter2)));
        altCost.setRuleAtTheTop(true);
        altCost.addHint(new ConditionHint(condition, "You control a Wizard"));
        this.addAbility(altCost);

        // Wizard's Lightning deals 3 damage to any target.
        this.getSpellAbility().addTarget(new TargetSpell());
        this.getSpellAbility().addEffect(new CounterTargetEffect());
    }

    private ForceOfTenacity(final ForceOfTenacity card) {
        super(card);
    }

    @Override
    public ForceOfTenacity copy() {
        return new ForceOfTenacity(this);
    }
}
