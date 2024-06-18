package mage.cards.c;

import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.costs.Cost;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.keyword.SpliceAbility;
import mage.cards.*;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterPermanentCard;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Library;
import mage.players.Player;
import mage.target.Target;
import mage.target.TargetPermanent;
import mage.util.CardUtil;

import java.util.HashSet;
import java.util.UUID;

/**
 * @author Ian
 */
public final class CallOfTheMultiverse extends CardImpl {

    public CallOfTheMultiverse(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{X}{R}{R}{R}");

        this.getSpellAbility().addCost(new SacrificeTargetCost(new FilterPermanent()));

        // Target permanent's controller sacrifices it. If they do, that player reveals cards from the top of their library until they reveal a permanent card that shares a card type with the sacrificed permanent, puts that card onto the battlefield, then shuffles their library.
        this.getSpellAbility().addEffect(new CallOfTheMultiverseEffect());
    }

    private CallOfTheMultiverse(final CallOfTheMultiverse card) {
        super(card);
    }

    @Override
    public CallOfTheMultiverse copy() {
        return new CallOfTheMultiverse(this);
    }
}

class CallOfTheMultiverseEffect extends OneShotEffect {

    private static final FilterPermanentCard filter = new FilterPermanentCard();

    public CallOfTheMultiverseEffect() {
        super(Outcome.Detriment);
        this.staticText = "Reveal cards from the top of your library until you reveal a permanent card that shares a card type with the sacrificed permanent with mana value greater than X. Put that card onto the battlefield and the rest on the bottom of your library in a random order.";
    }

    private CallOfTheMultiverseEffect(final CallOfTheMultiverseEffect effect) {
        super(effect);
    }

    @Override
    public CallOfTheMultiverseEffect copy() {
        return new CallOfTheMultiverseEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        HashSet<CardType> types = new HashSet<>();
        for (Cost cost : source.getCosts()) {
            if (cost instanceof SacrificeTargetCost) {
                for (Permanent sacrificed : ((SacrificeTargetCost) cost).getPermanents()) {
                    types.addAll(sacrificed.getCardType(game));
                }
            }
        }

        MageObject sourceObject = source.getSourceObject(game);
        if (sourceObject != null) {
            Player permanentController = game.getPlayer(source.getControllerId());
            if (permanentController != null) {
                Library library = permanentController.getLibrary();
                if (library.hasCards()) {
                    Cards cards = new CardsImpl();
                    Card permanentCard = null;
                    for (Card card : permanentController.getLibrary().getCards(game)) {
                        cards.add(card);
                        if (card.isPermanent(game) && card.getManaValue() > CardUtil.getSourceCostsTag(game, source, "X", 0)) {
                            for (CardType cardType : types) {
                                if (card.getCardType(game).contains(cardType)) {
                                    permanentCard = card;
                                    break;
                                }
                            }
                        }
                    }
                    permanentController.revealCards(source, cards, game);
                    if (permanentCard != null) {
                        permanentController.moveCards(permanentCard, Zone.BATTLEFIELD, source, game);
                    }
                    permanentController.shuffleLibrary(source, game);

                }
                return true;
            }
            return false;
        }
        return true;
    }
}
