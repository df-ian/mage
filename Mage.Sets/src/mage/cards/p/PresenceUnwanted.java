package mage.cards.p;

import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.Cards;
import mage.cards.CardsImpl;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.common.FilterCreatureCard;
import mage.filter.common.FilterLandCard;
import mage.game.Game;
import mage.players.Player;
import mage.target.TargetCard;
import mage.target.common.TargetCardInLibrary;
import mage.target.common.TargetCardWithDifferentNameInLibrary;
import mage.target.common.TargetOpponent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class PresenceUnwanted extends CardImpl {

    public PresenceUnwanted(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{2}{G}");

        // Search your library for four land cards with different names and reveal them. An opponent chooses two of those cards. Put the chosen cards into your graveyard and the rest into your hand. Then shuffle your library.
        this.getSpellAbility().addEffect(new PresenceUnwantedEffect());
        this.getSpellAbility().addTarget(new TargetOpponent());
    }

    private PresenceUnwanted(final PresenceUnwanted card) {
        super(card);
    }

    @Override
    public PresenceUnwanted copy() {
        return new PresenceUnwanted(this);
    }
}

class PresenceUnwantedEffect extends OneShotEffect {

    private static final FilterCard filter = new FilterCreatureCard("creature cards with different names");
    private static final FilterCard filter2 = new FilterCard("cards to put in graveyard");

    public PresenceUnwantedEffect() {
        super(Outcome.DrawCard);
        this.staticText = "Search your library for up to four creature cards with different names and reveal them. " +
                "Target opponent chooses two of those cards. Put the chosen cards into your graveyard " +
                "and the rest into your hand. Then shuffle";
    }

    private PresenceUnwantedEffect(final PresenceUnwantedEffect effect) {
        super(effect);
    }

    @Override
    public PresenceUnwantedEffect copy() {
        return new PresenceUnwantedEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player opponent = game.getPlayer(getTargetPointer().getFirst(game, source));
        Player player = game.getPlayer(source.getControllerId());
        if (player == null) {
            return false;
        }
        TargetCardInLibrary targetCards = new TargetCardWithDifferentNameInLibrary(0, 4, filter);
        player.searchLibrary(targetCards, source, game);
        Cards cards = new CardsImpl(targetCards.getTargets());
        cards.retainZone(Zone.LIBRARY, game);
        if (cards.isEmpty()) {
            player.shuffleLibrary(source, game);
        }
        player.revealCards(source, cards, game);

        if (cards.size() > 2) {
            if (opponent != null) {
                Cards cardsToKeep = new CardsImpl(cards);
                TargetCard targetDiscard = new TargetCard(2, Zone.LIBRARY, filter2);
                if (opponent.choose(Outcome.Discard, cards, targetDiscard, source, game)) {
                    cardsToKeep.removeIf(targetDiscard.getTargets()::contains);
                    cards.removeAll(cardsToKeep);
                }
                player.moveCards(cardsToKeep, Zone.HAND, source, game);
            }
        }

        player.moveCards(cards, Zone.GRAVEYARD, source, game);
        player.shuffleLibrary(source, game);
        return true;
    }
}
