package mage.cards.p;

import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.Mode;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.effects.common.*;
import mage.abilities.effects.common.discard.DiscardTargetEffect;
import mage.abilities.effects.keyword.InvestigateTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.predicate.Predicates;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.events.NumberOfTriggersEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.permanent.Permanent;
import mage.game.permanent.token.Token;
import mage.players.Player;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCreaturePermanent;
import mage.target.targetpointer.FixedTarget;
import mage.util.functions.CopyTokenFunction;

import java.util.UUID;

/**
 * @author Ian
 */
public final class PyrrhicInvocation extends CardImpl {


    public PyrrhicInvocation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{0}");

        // Spree
        this.addAbility(new SpreeAbility(this));


        // + {2}{B} - Whenever a permanent is dealt damage this turn, its controller loses 3 life unless they discard a card or sacrifice it.
        this.getSpellAbility().addEffect(new CreateDelayedTriggeredAbilityEffect(new PyrrhicInvocationDelayedTriggeredAbility()));
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{2}{B}"));

        Mode mode = new Mode(new DamagePlayersEffect(1, TargetController.OPPONENT));
        mode.addEffect(new DamageAllEffect(1, StaticFilters.FILTER_PERMANENT_CREATURE_OR_PLANESWALKER)
                .setText("and each creature and planeswalker"));
        // + {R} - End the festivities
        this.getSpellAbility().addMode(mode.withCost(new ManaCostsImpl<>("{R}")));


        // + {2}{G} - acid slime, babee
        mode = new Mode(new CreateDelayedTriggeredAbilityEffect(new PyrrhicInvocationDelayedTriggeredAbility2()));
        this.getSpellAbility().addMode(mode
                .withCost(new ManaCostsImpl<>("{1}{G}")));
    }

    private PyrrhicInvocation(final PyrrhicInvocation card) {
        super(card);
    }

    @Override
    public PyrrhicInvocation copy() {
        return new PyrrhicInvocation(this);
    }
}

class PyrrhicInvocationDelayedTriggeredAbility extends DelayedTriggeredAbility {

    public PyrrhicInvocationDelayedTriggeredAbility() {
        super(new PyrrhicSacEffect());
        setTriggerPhrase("Whenever a permanent is dealt damage this turn, ");
    }

    private PyrrhicInvocationDelayedTriggeredAbility(final PyrrhicInvocationDelayedTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public PyrrhicInvocationDelayedTriggeredAbility copy() {
        return new PyrrhicInvocationDelayedTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DAMAGE_PERMANENT;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        this.getAllEffects().setTargetPointer(new FixedTarget(event.getTargetId(), game));
        return true;
    }
}

class PyrrhicSacEffect extends OneShotEffect {

    PyrrhicSacEffect() {
        super(Outcome.PutCardInPlay);
        staticText = "its controller loses 3 life unless they discard a card or sacrifice it";
    }

    private PyrrhicSacEffect(final PyrrhicSacEffect effect) {
        super(effect);
    }

    @Override
    public PyrrhicSacEffect copy() {
        return new PyrrhicSacEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(source.getFirstTarget());
        if (permanent == null) {
            return false;
        }
        Player controller = game.getPlayer(permanent.getControllerId());
        if (controller == null) {
            return false;
        }
        if (controller.chooseUse(Outcome.LoseLife, "Lose 3 life?", "Otherwise, discard a card or sacrifice that permanent", "Lose life", "Discard / Sacrifice", source, game)) {
            controller.loseLife(3, game, source, false);
            return true;
        } else {
            if (!controller.getHand().isEmpty() && controller.chooseUse(Outcome.Discard, "Discard a card?", "Otherwise, sacrifice that permanent", "Discard", "Sacrifice", source, game)) {
                new DiscardTargetEffect(1).setTargetPointer(new FixedTarget(controller.getId())).apply(game, source);
                return true;
            }
            return permanent.sacrifice(source, game);
        }
    }
}


class PyrrhicInvocationDelayedTriggeredAbility2 extends DelayedTriggeredAbility {
    static FilterPermanent filter = new FilterPermanent("artifact, enchantment, or nonbasic land");
    static {
        filter.add(Predicates.or(CardType.ARTIFACT.getPredicate(), CardType.ENCHANTMENT.getPredicate(), Predicates.and(
                CardType.LAND.getPredicate(),
                Predicates.not(SuperType.BASIC.getPredicate())
        )));
    }
    public PyrrhicInvocationDelayedTriggeredAbility2() {
        super(new SacrificeOpponentsEffect(filter));
        setTriggerPhrase("Whenever a creature dies this turn, ");
    }

    private PyrrhicInvocationDelayedTriggeredAbility2(final PyrrhicInvocationDelayedTriggeredAbility2 ability) {
        super(ability);
    }

    @Override
    public PyrrhicInvocationDelayedTriggeredAbility2 copy() {
        return new PyrrhicInvocationDelayedTriggeredAbility2(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ZONE_CHANGE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        ZoneChangeEvent zEvent = (ZoneChangeEvent) event;
        return zEvent.isDiesEvent() && game.getPermanent(event.getTargetId()).isCreature() && event.getTargetId().equals(getSourceId());
    }
}