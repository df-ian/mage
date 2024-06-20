package mage.cards.h;

import mage.abilities.Ability;
import mage.abilities.common.BeginningOfEndStepTriggeredAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.hint.ConditionHint;
import mage.abilities.hint.Hint;
import mage.cards.*;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.common.FilterCreatureCard;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetCard;
import mage.target.common.TargetSacrifice;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Ian
 */
public final class HarrowingEmergence extends CardImpl {

    private static final FilterPermanent filter
            = new FilterControlledPermanent("if you control an artifact or creature");

    static {
        filter.add(Predicates.or(CardType.ARTIFACT.getPredicate(), CardType.CREATURE.getPredicate()));
    }

    private static final Condition condition = new PermanentsOnTheBattlefieldCondition(filter);
    private static final Hint hint = new ConditionHint(condition);

    public HarrowingEmergence(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{1}{B}");

        // At the beginning of your end step, if you control a creature, look at the top seven cards of your library. Then you may sacrifice a creature. If you do, you may put a creature card with mana value X or less from among those cards onto the battlefield, where X is 1 plus the sacrificed creature's mana value. Put the rest on the bottom of your library in a random order.
        this.addAbility(new BeginningOfEndStepTriggeredAbility(
                new HarrowingEmergenceEffect(), TargetController.YOU, condition, false
        ).addHint(hint));
    }

    private HarrowingEmergence(final HarrowingEmergence card) {
        super(card);
    }

    @Override
    public HarrowingEmergence copy() {
        return new HarrowingEmergence(this);
    }
}

class HarrowingEmergenceEffect extends OneShotEffect {
    private static final FilterPermanent filter
            = new FilterControlledPermanent("if you control an artifact or creature");

    static {
        filter.add(Predicates.or(CardType.ARTIFACT.getPredicate(), CardType.CREATURE.getPredicate()));
    }
    HarrowingEmergenceEffect() {
        super(Outcome.Benefit);
        this.staticText = "look at the top five cards of your library. Then you may sacrifice an artifact creature. "
                + "If you do, you may put an artifact or creature card with mana value X or less from among those cards onto the battlefield, "
                + "where X is 2 plus the sacrificed permanent's mana value. Put the rest on the bottom of your library in a random order.";
    }

    private HarrowingEmergenceEffect(final HarrowingEmergenceEffect effect) {
        super(effect);
    }

    @Override
    public HarrowingEmergenceEffect copy() {
        return new HarrowingEmergenceEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null) {
            return false;
        }

        // Look at the top seven cards of your library.
        Cards cards = new CardsImpl(controller.getLibrary().getTopCards(game, 5));
        controller.lookAtCards(source, null, cards, game);

        // Then you may sacrifice a creature.
        TargetSacrifice sacrificeTarget = new TargetSacrifice(0, 1, filter);
        controller.choose(Outcome.Sacrifice, sacrificeTarget, source, game);
        Permanent sacrificed = game.getPermanent(sacrificeTarget.getFirstTarget());
        if (sacrificed == null || !sacrificed.sacrifice(source, game)) {
            return endOfApply(cards, controller, game, source);
        }
        int mv = 2 + sacrificed.getManaValue();
        // If you do, you may put a creature card with mana value X or less from among those cards onto the battlefield, where X is 1 plus the sacrificed creature's mana value.
        FilterCard filter2 = new FilterCard("artifact or creature card with mana value " + mv + " or less");
        filter2.add(Predicates.or(CardType.ARTIFACT.getPredicate(), CardType.CREATURE.getPredicate()));
        filter2.add(new ManaValuePredicate(ComparisonType.OR_LESS, mv));
        TargetCard target = new TargetCard(0, 1, Zone.LIBRARY, filter2);
        target.withNotTarget(true);
        controller.choose(Outcome.PutCardInPlay, cards, target, source, game);
        Set<Card> putIntoPlay = target
                .getTargets()
                .stream()
                .map(game::getCard)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        controller.moveCards(putIntoPlay, Zone.BATTLEFIELD, source, game);
        return endOfApply(cards, controller, game, source);
    }

    private boolean endOfApply(Cards cards, Player controller, Game game, Ability source) {
        // Put the rest on the bottom of your library in a random order.
        cards.retainZone(Zone.LIBRARY, game);
        controller.putCardsOnBottomOfLibrary(cards, game, source, false);
        return true;
    }
}