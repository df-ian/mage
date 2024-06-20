package mage.cards.o;

import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.Mode;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.delayed.WhenTargetDiesDelayedTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.effects.common.*;
import mage.abilities.effects.common.counter.DistributeCountersEffect;
import mage.abilities.effects.common.replacement.AdditionalTriggerControlledETBReplacementEffect;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.constants.SetTargetPointer;
import mage.counters.CounterType;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.predicate.Predicates;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.events.NumberOfTriggersEvent;
import mage.target.TargetPermanent;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCreaturePermanent;
import mage.target.common.TargetPermanentAmount;

import java.util.UUID;

/**
 * @author Ian
 */
public final class OraclesInvocation extends CardImpl {


    public OraclesInvocation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{0}");

        // Spree
        this.addAbility(new SpreeAbility(this));


        // + {U} - single turn yarock
        this.getSpellAbility().addEffect(new OraclesInvocationPanharmoniconEffect());
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{U}"));

        Mode mode = new Mode(new CreateDelayedTriggeredAbilityEffect(new OraclesInvocationDelayedTriggeredAbility()));
        // + {1}{R} - whenver a creature enters under your control, ~ pings
        this.getSpellAbility().addMode(mode.withCost(new ManaCostsImpl<>("{1}{R}")));


        // + {2}{W} - mass flicker
        mode = new Mode(new ExileReturnBattlefieldNextEndStepTargetEffect());
        mode.addTarget(new TargetCreaturePermanent(0, Integer.MAX_VALUE));
        this.getSpellAbility().addMode(mode
                .withCost(new ManaCostsImpl<>("{2}{W}")));
    }

    private OraclesInvocation(final OraclesInvocation card) {
        super(card);
    }

    @Override
    public OraclesInvocation copy() {
        return new OraclesInvocation(this);
    }
}

class OraclesInvocationPanharmoniconEffect extends ReplacementEffectImpl {
    public OraclesInvocationPanharmoniconEffect() {
        super(Duration.EndOfTurn, Outcome.Benefit);
        staticText = "If a permanent entering the battlefield this turn causes an ability to trigger, that ability triggers an additional time";
    }

    private OraclesInvocationPanharmoniconEffect(final OraclesInvocationPanharmoniconEffect effect) {
        super(effect);
    }

    public OraclesInvocationPanharmoniconEffect copy() {
        return new OraclesInvocationPanharmoniconEffect(this);
    }

    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.NUMBER_OF_TRIGGERS;
    }

    public boolean applies(GameEvent event, Ability source, Game game) {
        if (!(event instanceof NumberOfTriggersEvent)) {
            return false;
        }
        NumberOfTriggersEvent numberOfTriggersEvent = (NumberOfTriggersEvent) event;
        // Only triggers for the source controller
        if (!source.isControlledBy(event.getPlayerId())) {
            return false;
        }
        GameEvent sourceEvent = numberOfTriggersEvent.getSourceEvent();
        // Only EtB triggers
        if (sourceEvent == null
                || sourceEvent.getType() != GameEvent.EventType.ENTERS_THE_BATTLEFIELD
                || !(sourceEvent instanceof EntersTheBattlefieldEvent)) {
            return false;
        }
        // Only for triggers of permanents
        return true;
    }

    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        event.setAmount(event.getAmount() + 1);
        return false;
    }
}



class OraclesInvocationDelayedTriggeredAbility extends DelayedTriggeredAbility {

    public OraclesInvocationDelayedTriggeredAbility() {
        super(new DamageTargetEffect(1));
        this.addTarget(new TargetAnyTarget());
        setTriggerPhrase("Whenever a creature enters under your control this turn, ");
    }

    private OraclesInvocationDelayedTriggeredAbility(final OraclesInvocationDelayedTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public OraclesInvocationDelayedTriggeredAbility copy() {
        return new OraclesInvocationDelayedTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ENTERS_THE_BATTLEFIELD_CONTROL;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        return this.isControlledBy(game.getOwnerId(event.getTargetId()));
    }
}