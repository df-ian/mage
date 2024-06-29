package mage.cards.f;

import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.AlternativeCostSourceAbility;
import mage.abilities.costs.common.ExileFromHandCost;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.cost.SpellCostReductionSourceEffect;
import mage.abilities.hint.ConditionHint;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.common.FilterOwnedCard;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCardInHand;

import java.util.UUID;

/**
 * @author Ian
 */
public final class Fireblitz extends CardImpl {

    private static final FilterControlledPermanent filter = new FilterControlledPermanent("you control a Wizard");
    private static final FilterOwnedCard filter2
            = new FilterOwnedCard("a red card from your hand");

    static {
        filter.add(SubType.WIZARD.getPredicate());
        filter2.add(new ColorPredicate(ObjectColor.RED));
    }

    public Fireblitz(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{1}{R}{R}");

        // Wizard's Lightning costs {2} less to cast if you control a Wizard.
        Condition condition = new PermanentsOnTheBattlefieldCondition(filter);
        AlternativeCostSourceAbility altCost = new AlternativeCostSourceAbility(new PayLifeCost(1), condition);
        altCost.addCost(new ExileFromHandCost(new TargetCardInHand(filter2)));
        altCost.setRuleAtTheTop(true);
        altCost.addHint(new ConditionHint(condition, "You control a Wizard"));
        this.addAbility(altCost);

        // Wizard's Lightning deals 3 damage to any target.
        this.getSpellAbility().addTarget(new TargetAnyTarget());
        this.getSpellAbility().addEffect(new DamageTargetEffect(3));
    }

    private Fireblitz(final Fireblitz card) {
        super(card);
    }

    @Override
    public Fireblitz copy() {
        return new Fireblitz(this);
    }
}
