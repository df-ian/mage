
package mage.cards.r;

import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayEffect;
import mage.abilities.mana.ColorlessManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.CardsImpl;
import mage.constants.*;
import mage.filter.StaticFilters;
import mage.filter.common.FilterLandPermanent;
import mage.filter.predicate.Predicates;
import mage.game.Game;
import mage.players.Player;
import mage.target.common.TargetCardInLibrary;
import mage.target.common.TargetLandPermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class RuinousSands extends CardImpl {

    private static final FilterLandPermanent filter = new FilterLandPermanent("land an opponent controls");

    static {
        filter.add(TargetController.OPPONENT.getControllerPredicate());
    }

    public RuinousSands(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");

        // {T}: Add {C}.
        this.addAbility(new ColorlessManaAbility());

        // {2}, {T}, Sacrifice Field of Ruin: Destroy target nonbasic land an opponent controls. Each player searches their library for a basic land card, puts it onto the battlefield, then shuffles their library.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new DestroyTargetEffect(), new TapSourceCost());
        ability.addCost(new SacrificeSourceCost());
        ability.addEffect(new RuinousSandsEffect());
        ability.addTarget(new TargetLandPermanent(filter));
        this.addAbility(ability);
    }

    private RuinousSands(final RuinousSands card) {
        super(card);
    }

    @Override
    public RuinousSands copy() {
        return new RuinousSands(this);
    }
}

class RuinousSandsEffect extends OneShotEffect {

    RuinousSandsEffect() {
        super(Outcome.Benefit);
        this.staticText = "Each player searches their library for a basic land card, puts it onto the battlefield tapped, then shuffles";
    }

    private RuinousSandsEffect(final RuinousSandsEffect effect) {
        super(effect);
    }

    @Override
    public RuinousSandsEffect copy() {
        return new RuinousSandsEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            for (UUID playerId : game.getState().getPlayersInRange(controller.getId(), game)) {
                Player player = game.getPlayer(playerId);
                if (player != null) {
                    TargetCardInLibrary target = new TargetCardInLibrary(0, 1, StaticFilters.FILTER_CARD_BASIC_LAND);
                    new SearchLibraryPutInPlayEffect(target, true).apply(game, source);
//                    if (player.searchLibrary(target, source, game)) {
//                        player.moveCards(new CardsImpl(target.getTargets()).getCards(game), Zone.BATTLEFIELD, source, game);
//                        player.shuffleLibrary(source, game);
//                    }
                }
            }
            return true;
        }
        return false;
    }
}
