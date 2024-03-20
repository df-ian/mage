package mage.cards.s;

import mage.abilities.Ability;
import mage.abilities.common.delayed.AtTheBeginOfNextEndStepDelayedTriggeredAbility;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.SacrificeTargetEffect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.keyword.HasteAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.Filter;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCardInOpponentsGraveyard;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SecondEndeavour extends CardImpl {

    public SecondEndeavour(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{B}{R}");

        // Put target creature card from an opponent's graveyard onto the battlefield under your control. It gets +2/+0 and gains haste until end of turn. Sacrifice it at the beginning of the next end step.
        this.getSpellAbility().addEffect(new SecondEndeavourEffect());
        Filter filter = new FilterCard("artifact or creature card with mana value 4 or less");
        filter.add(Predicates.or(CardType.ARTIFACT.getPredicate(), CardType.CREATURE.getPredicate()));
        filter.add(new ManaValuePredicate(ComparisonType.OR_LESS, 4));
        this.getSpellAbility().addTarget(new TargetCardInGraveyard());
    }

    private SecondEndeavour(final SecondEndeavour card) {
        super(card);
    }

    @Override
    public SecondEndeavour copy() {
        return new SecondEndeavour(this);
    }
}

class SecondEndeavourEffect extends OneShotEffect {

    SecondEndeavourEffect() {
        super(Outcome.PutCreatureInPlay);
        staticText = "Return target artifact or creature card with mana value 4 or less from your graveyard to the battlefield. It gains haste. Sacrifice it at the beginning of the next end step.";
    }

    private SecondEndeavourEffect(final SecondEndeavourEffect effect) {
        super(effect);
    }

    @Override
    public SecondEndeavourEffect copy() {
        return new SecondEndeavourEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Card card = game.getCard(getTargetPointer().getFirst(game, source));
        if (card == null) {
            return false;
        }
        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null || !controller.moveCards(card, Zone.BATTLEFIELD, source, game)) {
            return false;
        }
        Permanent permanent = game.getPermanent(card.getId());
        if (permanent == null) {
            return false;
        }
        ContinuousEffect effect = new GainAbilityTargetEffect(HasteAbility.getInstance(), Duration.Custom);
        effect.setTargetPointer(new FixedTarget(permanent, game));
        game.addEffect(effect, source);
        Effect sacrificeEffect = new SacrificeTargetEffect("sacrifice " + permanent.getLogName(), controller.getId());
        sacrificeEffect.setTargetPointer(new FixedTarget(permanent, game));
        game.addDelayedTriggeredAbility(new AtTheBeginOfNextEndStepDelayedTriggeredAbility(sacrificeEffect), source);
        return true;
    }
}
