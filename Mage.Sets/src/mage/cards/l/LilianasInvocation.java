package mage.cards.l;

import mage.abilities.condition.Condition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.common.DiscardCardCost;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.common.LoseLifeSourceControllerEffect;
import mage.abilities.effects.common.SacrificeEffect;
import mage.abilities.effects.common.SacrificeOpponentsEffect;
import mage.abilities.effects.common.discard.DiscardEachPlayerEffect;
import mage.abilities.effects.common.discard.DiscardTargetEffect;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.TargetController;
import mage.filter.StaticFilters;
import mage.filter.common.FilterControlledPlaneswalkerPermanent;
import mage.target.TargetPlayer;

import java.util.UUID;

/**
 * @author Ian
 */
public final class LilianasInvocation extends CardImpl {

    public LilianasInvocation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{B}{B}");

        // Each opponent sacrifices a creature. If you control a Liliana planeswalker, each opponent also discards a card.

        this.getSpellAbility().addEffect(new SacrificeEffect(StaticFilters.FILTER_PERMANENT_CREATURE, 1, "Target player"));
        this.getSpellAbility().addEffect(new DiscardTargetEffect(1).setText("discards a card").concatBy("and"));
        this.getSpellAbility().addEffect(new LoseLifeSourceControllerEffect(2));
        this.getSpellAbility().addTarget(new TargetPlayer());

        this.addAbility(new FlashbackAbility(this, new DiscardCardCost()));
    }

    private LilianasInvocation(final LilianasInvocation card) {
        super(card);
    }

    @Override
    public LilianasInvocation copy() {
        return new LilianasInvocation(this);
    }
}
