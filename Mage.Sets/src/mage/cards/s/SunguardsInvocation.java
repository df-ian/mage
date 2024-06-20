package mage.cards.s;

import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.effects.common.MillThenPutInHandEffect;
import mage.abilities.effects.common.SacrificeAllEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayEffect;
import mage.abilities.keyword.DecayedAbility;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.CardsImpl;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.permanent.token.Token;
import mage.players.Player;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCardInLibrary;
import mage.util.functions.CopyTokenFunction;
import mage.watchers.common.PermanentWasCastWatcher;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Ian
 */
public final class SunguardsInvocation extends CardImpl {


    public SunguardsInvocation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{0}");

        // Spree
        this.addAbility(new SpreeAbility(this));


        // + {U} - single turn yarock
        this.getSpellAbility().addEffect(new SunguardsInvocationContainmentEffect());
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{1}{W}"));

        Mode mode = new Mode(new SunguardsInvocationMindcensorEffect());
        this.getSpellAbility().addMode(mode.withCost(new ManaCostsImpl<>("{B}")));

        mode = new Mode(new SunguardsInvocationTutorEffect());
        this.getSpellAbility().addMode(mode
                .withCost(new ManaCostsImpl<>("{G}{G}")));
    }

    private SunguardsInvocation(final SunguardsInvocation card) {
        super(card);
    }

    @Override
    public SunguardsInvocation copy() {
        return new SunguardsInvocation(this);
    }
}

class SunguardsInvocationContainmentEffect extends ReplacementEffectImpl {

    SunguardsInvocationContainmentEffect() {
        super(Duration.EndOfTurn, Outcome.Exile);
        staticText = "Until end of turn, if a creature would enter under an opponents control and it wasn't cast, exile it instead";
    }

    private SunguardsInvocationContainmentEffect(final SunguardsInvocationContainmentEffect effect) {
        super(effect);
    }

    @Override
    public SunguardsInvocationContainmentEffect copy() {
        return new SunguardsInvocationContainmentEffect(this);
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        Player controller = game.getPlayer(source.getControllerId());
        Card targetCard = game.getCard(event.getTargetId());
        if (targetCard == null) {
            targetCard = ((EntersTheBattlefieldEvent) event).getTarget();
        }
        if (controller != null && targetCard != null && source.getControllerId() != targetCard.getOwnerId()) {
            controller.moveCards(targetCard, Zone.EXILED, source, game, false, false, false, null);
            return true;
        }
        return false;
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ENTERS_THE_BATTLEFIELD;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        EntersTheBattlefieldEvent entersTheBattlefieldEvent = (EntersTheBattlefieldEvent) event;
        if (entersTheBattlefieldEvent.getTarget().isCreature(game)) {
            PermanentWasCastWatcher watcher = game.getState().getWatcher(PermanentWasCastWatcher.class);
            return watcher != null && !watcher.wasPermanentCastThisTurn(event.getTargetId()) && game.getPermanent(event.getTargetId()).getControllerId() != source.getControllerId();
        }
        return false;
    }
}


class SunguardsInvocationMindcensorEffect extends ReplacementEffectImpl {
    SunguardsInvocationMindcensorEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Benefit);
        staticText = "If an opponent would search a library this turn, that player searches the top two cards of that library instead";
    }

    private SunguardsInvocationMindcensorEffect(final SunguardsInvocationMindcensorEffect effect) {
        super(effect);
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        event.setAmount(2);
        return false;
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.SEARCH_LIBRARY;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        Player controller = game.getPlayer(source.getControllerId());
        return controller != null && game.isOpponent(controller, event.getPlayerId());
    }

    @Override
    public SunguardsInvocationMindcensorEffect copy() {
        return new SunguardsInvocationMindcensorEffect(this);
    }
}

class SunguardsInvocationTutorEffect extends OneShotEffect {

    SunguardsInvocationTutorEffect() {
        super(Outcome.Benefit);
        this.staticText = "Each player searches their library for a creature or land card with mana value 1 or less, puts it onto the battlefield tapped, then shuffles";
    }

    private SunguardsInvocationTutorEffect(final SunguardsInvocationTutorEffect effect) {
        super(effect);
    }

    @Override
    public SunguardsInvocationTutorEffect copy() {
        return new SunguardsInvocationTutorEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        FilterCard filter = new FilterCard("creature or land card with mana value 1 or less");
        filter.add(Predicates.or(CardType.CREATURE.getPredicate(), CardType.LAND.getPredicate()));
        filter.add(new ManaValuePredicate(ComparisonType.OR_LESS, 1));
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            for (UUID playerId : game.getState().getPlayersInRange(controller.getId(), game)) {
                Player player = game.getPlayer(playerId);
                if (player != null) {
                    TargetCardInLibrary target = new TargetCardInLibrary(1, 1, filter);
                    if (player.searchLibrary(target, source, game)) {
                        player.moveCards(new CardsImpl(target.getTargets()).getCards(game), Zone.BATTLEFIELD, source, game);
                        player.shuffleLibrary(source, game);
                    }
                }
            }
            return true;
        }
        return false;
    }
}
