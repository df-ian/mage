package mage.cards.f;

import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.MageSingleton;
import mage.abilities.Mode;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CreateDelayedTriggeredAbilityEffect;
import mage.abilities.effects.common.CreateTokenCopyTargetEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.effects.common.discard.DiscardEachPlayerEffect;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.CardsImpl;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.filter.predicate.mageobject.PowerPredicate;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.game.stack.Spell;
import mage.players.Player;
import mage.target.Target;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCardInHand;
import mage.target.targetpointer.FixedTarget;
import mage.target.targetpointer.FixedTargets;
import mage.util.CardUtil;
import mage.watchers.Watcher;

import java.io.ObjectStreamException;
import java.util.UUID;

/**
 * @author Ian
 */
public final class FirebirdsInvocation extends CardImpl {


    public FirebirdsInvocation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{0}");

        // Spree
        this.addAbility(new SpreeAbility(this));


        // + {G} - Whenever a creature with power 4 or greater enters under your control this turn, draw a card
        this.getSpellAbility().addEffect(new CreateDelayedTriggeredAbilityEffect(new FirebirdsInvocationTriggeredAbility()));
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{G}"));

        // + {2}{U} - You may reveal a card from your hand. If you reveal a creature card with mana value 4 or less this way, create a token that's a copy of it
        this.getSpellAbility().addMode(new Mode(new FirebirdsInvocationCopyEffect()).withCost(new ManaCostsImpl<>("{2}{U}")));

        // + {1}{R} - Creatures you control gain haste until end of turn
        this.getSpellAbility().addMode(new Mode(new GainAbilityControlledEffect(HasteAbility.getInstance(), Duration.EndOfTurn, StaticFilters.FILTER_CONTROLLED_CREATURES))
                .withCost(new ManaCostsImpl<>("{1}{R}")));
    }

    private FirebirdsInvocation(final FirebirdsInvocation card) {
        super(card);
    }

    @Override
    public FirebirdsInvocation copy() {
        return new FirebirdsInvocation(this);
    }
}



class FirebirdsInvocationTriggeredAbility extends DelayedTriggeredAbility {

    private static final FilterPermanent filter = new FilterCreaturePermanent("a creature with power 4 or greater");

    static {
        filter.add(new PowerPredicate(ComparisonType.MORE_THAN, 3));
    }
    public FirebirdsInvocationTriggeredAbility() {
        super(new DrawCardSourceControllerEffect(1), Duration.EndOfTurn, false);
    }

    private FirebirdsInvocationTriggeredAbility(final FirebirdsInvocationTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ENTERS_THE_BATTLEFIELD;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getPlayerId().equals(this.getControllerId())) {
            Permanent spell = game.getPermanent(event.getTargetId());
            return spell != null && filter.match(spell, event.getPlayerId(), this, game);
        }
        return false;
    }

    @Override
    public FirebirdsInvocationTriggeredAbility copy() {
        return new FirebirdsInvocationTriggeredAbility(this);
    }

    @Override
    public String getRule() {
        return "Whenever a creature with power 4 or greater enters under your control this turn, draw a card.";
    }
}

class FirebirdsInvocationCopyEffect extends OneShotEffect {

    FirebirdsInvocationCopyEffect() {
        super(Outcome.PutCreatureInPlay);
        staticText = "Reveal a card from your hand. "
                + "If you reveal a creature card with mana value 4 or less this way, create a token that's a copy of it.";
    }

    private FirebirdsInvocationCopyEffect(final FirebirdsInvocationCopyEffect effect) {
        super(effect);
    }

    @Override
    public FirebirdsInvocationCopyEffect copy() {
        return new FirebirdsInvocationCopyEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        if (player == null) {
            return false;
        }
        // This was implemented before ruling was clear.
        // It is assumed for now that:
        // - if you have no card in hand you must reveal top of library.
        // - if you have no library, you must reveal a card from your hand.
        boolean canRevealFromHand = !player.getHand().isEmpty();

        if (canRevealFromHand) {
            return revealFromHand(player, game, source);
        } else {
            return false;
        }
    }
    
    private boolean revealFromHand(Player player, Game game, Ability source) {
        CardsImpl cards = new CardsImpl();
        Card card = null;
        boolean madeAChoice = false;
        if (player.getHand().size() > 0) {
            Target target = new TargetCardInHand(StaticFilters.FILTER_CARD);
            target.withNotTarget(true);
            if (player.chooseTarget(outcome, target, source, game)) {
                card = game.getCard(target.getFirstTarget());
                madeAChoice = true;
            }
        }
        MageObject mageObject = source.getSourceObject(game);
        if (card == null || mageObject == null) {
            return madeAChoice;
        }
        cards.add(card);
        player.revealCards(mageObject.getName() + " (from hand)", cards, game, false);
        game.informPlayers(player.getLogName() + " reveals " + card.getLogName() + " from hand" + CardUtil.getSourceLogName(game, source));
        if (card.isCreature(game) && card.getManaValue() <= 4) {
            new CreateTokenCopyTargetEffect().setTargetPointer(new FixedTarget(card.getId())).apply(game, source);
        }
        return true;
    }
}