package mage.cards.p;

import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.Mode;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.CreateTokenCopySourceEffect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.effects.common.*;
import mage.abilities.effects.keyword.InvestigateTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.events.NumberOfTriggersEvent;
import mage.game.permanent.Permanent;
import mage.game.permanent.token.Token;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCreatureOrPlaneswalker;
import mage.target.common.TargetCreaturePermanent;
import mage.target.targetpointer.FixedTarget;
import mage.target.targetpointer.TargetPointer;
import mage.util.functions.CopyTokenFunction;

import java.util.UUID;

/**
 * @author Ian
 */
public final class PhantomridersInvocation extends CardImpl {


    public PhantomridersInvocation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{0}");

        // Spree
        this.addAbility(new SpreeAbility(this));


        // + {1}{W} - fateful absence
        this.getSpellAbility().addEffect(new PhantomridersDestroyEffect());
        this.getSpellAbility().addTarget(new TargetCreatureOrPlaneswalker(0, 1));
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{1}{W}"));

        // + {U}{U} - Token creation effect
        Mode mode = new Mode(new CreateDelayedTriggeredAbilityEffect(new PhantomridersInvocationDelayedTriggeredAbility()));
        this.getSpellAbility().addMode(mode.withCost(new ManaCostsImpl<>("{U}{U}")));


        // + {B} - mass flicker
        mode = new Mode(new ReturnFromGraveyardToBattlefieldTargetEffect(true));
        FilterCard filterYard = new FilterCard("creature card in a graveyard with mana value 3 or less");
        filterYard.add(CardType.CREATURE.getPredicate());
        filterYard.add(new ManaValuePredicate(ComparisonType.OR_LESS, 3));
        mode.addTarget(new TargetCardInGraveyard(0, 1, filterYard, false));
        mode.addEffect(new LoseLifeSourceControllerEffect(2).concatBy(", then"));
        this.getSpellAbility().addMode(mode
                .withCost(new ManaCostsImpl<>("{B}")));
    }

    private PhantomridersInvocation(final PhantomridersInvocation card) {
        super(card);
    }

    @Override
    public PhantomridersInvocation copy() {
        return new PhantomridersInvocation(this);
    }
}

class PhantomridersInvocationPanharmoniconEffect extends ReplacementEffectImpl {
    public PhantomridersInvocationPanharmoniconEffect() {
        super(Duration.EndOfTurn, Outcome.Benefit);
        staticText = "If a permanent entering the battlefield causes an ability to trigger, that ability triggers an additional time";
    }

    private PhantomridersInvocationPanharmoniconEffect(final PhantomridersInvocationPanharmoniconEffect effect) {
        super(effect);
    }

    public PhantomridersInvocationPanharmoniconEffect copy() {
        return new PhantomridersInvocationPanharmoniconEffect(this);
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



class PhantomridersInvocationDelayedTriggeredAbility extends DelayedTriggeredAbility {

    public PhantomridersInvocationDelayedTriggeredAbility() {
        super(new PhantomridersCloneEffect());
        setTriggerPhrase("Whenever a creature enters under your control this turn, ");
    }

    private PhantomridersInvocationDelayedTriggeredAbility(final PhantomridersInvocationDelayedTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public PhantomridersInvocationDelayedTriggeredAbility copy() {
        return new PhantomridersInvocationDelayedTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ENTERS_THE_BATTLEFIELD_CONTROL;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        Permanent perm = game.getPermanentOrLKIBattlefield(event.getTargetId());
        if (perm == null) {
            return false;
        }
        boolean success = this.isControlledBy(game.getOwnerId(event.getTargetId()))
                && (perm.isCreature(game) || perm.isArtifact(game))
                && !perm.getSubtype(game).contains(SubType.ILLUSION);
        if (!success) { return false; }
        this.getAllEffects().setTargetPointer(new FixedTarget(event.getTargetId(), game));
        return true;
    }
}



class PhantomridersCloneEffect extends OneShotEffect {

    PhantomridersCloneEffect() {
        super(Outcome.PutCardInPlay);
        staticText = "create a token that's a copy of it, except it's a 1/1 blue Illusion creature with flying";
    }

    private PhantomridersCloneEffect(final PhantomridersCloneEffect effect) {
        super(effect);
    }

    @Override
    public PhantomridersCloneEffect copy() {
        return new PhantomridersCloneEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Card permanent = game.getCard(source.getFirstTarget());
        if (permanent == null) {
            return false;
        }
        Token token = CopyTokenFunction.createTokenCopy(permanent, game);
        token.removePTCDA();
        token.setPower(1);
        token.setToughness(1);
        token.setColor(ObjectColor.BLUE);
        token.removeAllCardTypes();
        token.addCardType(CardType.CREATURE);
        token.removeAllCreatureTypes();
        token.addSubType(SubType.ILLUSION);
        token.addAbility(FlyingAbility.getInstance());
        token.putOntoBattlefield(1, game, source, source.getControllerId());
        return true;
    }
}

class PhantomridersDestroyEffect extends OneShotEffect {

    PhantomridersDestroyEffect() {
        super(Outcome.DestroyPermanent);
        staticText = "Destroy up to one target creature or planeswalker. Its controller investigates";
    }

    private PhantomridersDestroyEffect(final PhantomridersDestroyEffect effect) {
        super(effect);
    }

    @Override
    public PhantomridersDestroyEffect copy() {
        return new PhantomridersDestroyEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(source.getFirstTarget());
        if (permanent == null) {
            return false;
        }
        UUID controllerId = permanent.getControllerId();
        permanent.destroy(source, game, false);
        new InvestigateTargetEffect().setTargetPointer(new FixedTarget(controllerId)).apply(game, source);
        return true;
    }
}