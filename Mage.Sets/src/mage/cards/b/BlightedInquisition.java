

package mage.cards.b;

import mage.abilities.condition.common.KickedCondition;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.common.discard.DiscardCardYouChooseTargetEffect;
import mage.abilities.keyword.KickerAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ComparisonType;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.target.TargetPlayer;

import java.util.UUID;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public final class BlightedInquisition extends CardImpl {

    private static final FilterCard filter = new FilterCard("nonland card with mana value 3 or less");

    static {
        filter.add(Predicates.not(CardType.LAND.getPredicate()));
        filter.add(new ManaValuePredicate(ComparisonType.FEWER_THAN, 4));
    }

    public BlightedInquisition(UUID ownerId, CardSetInfo setInfo){
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{B}");

        this.addAbility(new KickerAbility(new PayLifeCost(3)));

        this.getSpellAbility().addTarget(new TargetPlayer());
        ConditionalOneShotEffect effect = new ConditionalOneShotEffect(new DiscardCardYouChooseTargetEffect(filter), new DiscardCardYouChooseTargetEffect(StaticFilters.FILTER_CARD_NON_LAND), KickedCondition.ONCE, "Target player reveals their hand. You choose a nonland card from it. They discard it if it has mana value 3 or less or if this spell was kicked.");
        this.getSpellAbility().addEffect(effect);
    }

    private BlightedInquisition(final BlightedInquisition card) {
        super(card);
    }

    @Override
    public BlightedInquisition copy() {
        return new BlightedInquisition(this);
    }
}
