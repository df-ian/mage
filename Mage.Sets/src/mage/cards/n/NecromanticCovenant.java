
package mage.cards.n;

import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.delayed.AtTheBeginOfNextEndStepDelayedTriggeredAbility;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.continuous.AddCreatureTypeAdditionEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCreaturePermanent;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class NecromanticCovenant extends CardImpl {

    public NecromanticCovenant(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{3}{B}{B}");
        Ability a = new EntersBattlefieldTriggeredAbility(new DestroyTargetEffect());
        a.addTarget(new TargetCreaturePermanent());
        this.addAbility(a);

        // Whenever a creature you don't control dies, return it to the battlefield under
        // your control with an additional +1/+1 counter on it at the beginning of the
        // next end step. That creature is a black Zombie in addition to its other colors and types.
        this.addAbility(new NecromanticCovenantTriggeredAbility());
    }

    private NecromanticCovenant(final NecromanticCovenant card) {
        super(card);
    }

    @Override
    public NecromanticCovenant copy() {
        return new NecromanticCovenant(this);
    }
}

class NecromanticCovenantTriggeredAbility extends TriggeredAbilityImpl {

    public NecromanticCovenantTriggeredAbility() {
        super(Zone.BATTLEFIELD, null);
    }

    private NecromanticCovenantTriggeredAbility(final NecromanticCovenantTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public NecromanticCovenantTriggeredAbility copy() {
        return new NecromanticCovenantTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ZONE_CHANGE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        ZoneChangeEvent zoneChangeEvent = (ZoneChangeEvent) event;
        if (zoneChangeEvent.isDiesEvent()) {
            Permanent permanent = (Permanent) game.getLastKnownInformation(event.getTargetId(), Zone.BATTLEFIELD);
            if (permanent != null && !permanent.isControlledBy(this.getControllerId()) && permanent.isCreature(game)) {
                Card card = (Card) game.getObject(permanent.getId());
                if (card != null) {
                    Effect effect = new NecromanticCovenantEffect();
                    effect.setTargetPointer(new FixedTarget(card, game));
                    DelayedTriggeredAbility delayedAbility = new AtTheBeginOfNextEndStepDelayedTriggeredAbility(effect);
                    game.addDelayedTriggeredAbility(delayedAbility, this);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever a creature you don't control dies, return it to the battlefield under your control at the beginning of the next end step.";
    }
}

class NecromanticCovenantEffect extends OneShotEffect {

    NecromanticCovenantEffect() {
        super(Outcome.PutCreatureInPlay);
        staticText = " return the creature to the battlefield under your control.";
    }

    private NecromanticCovenantEffect(final NecromanticCovenantEffect effect) {
        super(effect);
    }

    @Override
    public NecromanticCovenantEffect copy() {
        return new NecromanticCovenantEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            Card card = game.getCard(getTargetPointer().getFirst(game, source));
            if (card != null) {
                controller.moveCards(card, Zone.BATTLEFIELD, source, game);
            }
            return true;
        }
        return false;
    }

}