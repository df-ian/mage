package mage.cards.p;

import mage.abilities.Ability;
import mage.abilities.condition.InvertCondition;
import mage.abilities.condition.common.CardsInControllerGraveyardCondition;
import mage.abilities.decorator.ConditionalOneShotEffect;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.target.common.TargetCreaturePermanent;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 *
 * @author I
 */
public final class PitilessExecution extends CardImpl {

    public PitilessExecution(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{B}");

        // Target nonblack creature gets -1/-1 until end of turn.
        this.getSpellAbility().addEffect(new ConditionalOneShotEffect(
                new PitilessExecutionEffect(),
                new InvertCondition(new CardsInControllerGraveyardCondition(7)),
                "Destroy target creature if it has mana value 2 or less."));

        // Threshold - If seven or more cards are in your graveyard, instead destroy that creature. It can't be regenerated.
        this.getSpellAbility().addEffect(new ConditionalOneShotEffect(
                new DestroyTargetEffect(false),
                new CardsInControllerGraveyardCondition(7),
                "<br/><br/><i>Threshold</i> &mdash; If seven or more cards are in your graveyard, instead destroy that creature."));

        this.getSpellAbility().addTarget(new TargetCreaturePermanent(StaticFilters.FILTER_PERMANENT_CREATURE));
    }

    private PitilessExecution(final PitilessExecution card) {
        super(card);
    }

    @Override
    public PitilessExecution copy() {
        return new PitilessExecution(this);
    }
}

class PitilessExecutionEffect extends OneShotEffect {

    PitilessExecutionEffect() {
        super(Outcome.UnboostCreature);
    }

    private PitilessExecutionEffect(final PitilessExecutionEffect effect) {
        super(effect);
    }

    @Override
    public PitilessExecutionEffect copy() {
        return new PitilessExecutionEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        DestroyTargetEffect effect = new DestroyTargetEffect(false);
        Permanent permanent = game.getPermanent(source.getFirstTarget());
        if (permanent != null && permanent.getManaValue() <= 2) {
            effect.setTargetPointer(new FixedTarget(permanent, game));
            effect.apply(game, source);
            return true;
        }
        return false;
    }
}
