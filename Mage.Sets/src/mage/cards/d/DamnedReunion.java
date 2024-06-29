package mage.cards.d;

import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.common.delayed.AtTheBeginOfNextEndStepDelayedTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.keyword.FlashbackAbility;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.SpliceAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreatureCard;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCardInYourGraveyard;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 * @author Ian
 */
public final class DamnedReunion extends CardImpl {

    public DamnedReunion(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{1}{B}");

        // Return target legendary creature card from your graveyard to the battlefield. That creature gains haste. Exile it at the beginning of the next end step.
        this.getSpellAbility().addEffect(new DamnedReunionEffect());
        this.getSpellAbility().addTarget(new TargetCardInYourGraveyard(StaticFilters.FILTER_CARD_CREATURE).withChooseHint("return to battlefield"));

        // Splice onto Arcane {2}{B}
        this.addAbility(new FlashbackAbility(this, new ManaCostsImpl<>("{4}{B}")));
    }

    private DamnedReunion(final DamnedReunion card) {
        super(card);
    }

    @Override
    public DamnedReunion copy() {
        return new DamnedReunion(this);
    }
}

class DamnedReunionEffect extends OneShotEffect {

    DamnedReunionEffect() {
        super(Outcome.PutCardInPlay);
        this.staticText = "Return target creature card from your graveyard to the battlefield. That creature gains haste. Exile it at the beginning of the next end step";
    }

    private DamnedReunionEffect(final DamnedReunionEffect effect) {
        super(effect);
    }

    @Override
    public DamnedReunionEffect copy() {
        return new DamnedReunionEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null) {
            return false;
        }
        Card card = game.getCard(getTargetPointer().getFirst(game, source));
        if (card == null || !controller.moveCards(card, Zone.BATTLEFIELD, source, game)) {
            return false;
        }
        Permanent permanent = game.getPermanent(card.getId());
        if (permanent == null) {
            return false;
        }
        // Haste
        ContinuousEffect effect = new GainAbilityTargetEffect(HasteAbility.getInstance(), Duration.Custom);
        effect.setTargetPointer(new FixedTarget(permanent, game));
        game.addEffect(effect, source);
        // Exile it at end of turn
        Effect exileEffect = new ExileTargetEffect("Exile " + permanent.getName() + " at the beginning of the next end step");
        exileEffect.setTargetPointer(new FixedTarget(permanent, game));
        DelayedTriggeredAbility delayedAbility = new AtTheBeginOfNextEndStepDelayedTriggeredAbility(exileEffect);
        game.addDelayedTriggeredAbility(delayedAbility, source);
        return true;

    }
}
