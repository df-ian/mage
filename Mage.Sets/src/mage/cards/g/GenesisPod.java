
package mage.cards.g;

import mage.abilities.Ability;
import mage.abilities.common.ActivateAsSorceryActivatedAbility;
import mage.abilities.costs.Cost;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ComparisonType;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCardInLibrary;

import java.util.UUID;

/**
 * @author Ian
 */
public final class GenesisPod extends CardImpl {

    public GenesisPod(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}{G}");

        // {1}{G/P}, {tap}, Sacrifice a creature: Search your library for a creature card with converted mana cost equal to 1 plus the sacrificed creature's converted mana cost,
        // put that card onto the battlefield, then shuffle your library. Activate this ability only any time you could cast a sorcery.
        Ability ability = new ActivateAsSorceryActivatedAbility(
                Zone.BATTLEFIELD, new GenesisPodEffect(), new ManaCostsImpl<>("{G}")
        );
        ability.addCost(new TapSourceCost());
        ability.addCost(new SacrificeTargetCost(StaticFilters.FILTER_CONTROLLED_CREATURE));
        this.addAbility(ability);
    }

    private GenesisPod(final GenesisPod card) {
        super(card);
    }

    @Override
    public GenesisPod copy() {
        return new GenesisPod(this);
    }
}

class GenesisPodEffect extends OneShotEffect {

    GenesisPodEffect() {
        super(Outcome.Benefit);
        staticText = "Search your library for a creature card with mana value equal to 1 " +
                "plus the sacrificed creature's mana value, put that card " +
                "onto the battlefield, then shuffle";
    }

    private GenesisPodEffect(final GenesisPodEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent sacrificedPermanent = null;
        for (Cost cost : source.getCosts()) {
            if (cost instanceof SacrificeTargetCost) {
                SacrificeTargetCost sacrificeCost = (SacrificeTargetCost) cost;
                if (!sacrificeCost.getPermanents().isEmpty()) {
                    sacrificedPermanent = sacrificeCost.getPermanents().get(0);
                }
                break;
            }
        }
        Player controller = game.getPlayer(source.getControllerId());
        if (sacrificedPermanent == null || controller == null) {
            return false;
        }
        int newConvertedCost = sacrificedPermanent.getManaValue() + 1;
        FilterCard filter = new FilterCard("creature card with mana value " + newConvertedCost);
        filter.add(new ManaValuePredicate(ComparisonType.EQUAL_TO, newConvertedCost));
        filter.add(CardType.CREATURE.getPredicate());
        TargetCardInLibrary target = new TargetCardInLibrary(filter);
        if (controller.searchLibrary(target, source, game)) {
            Card card = controller.getLibrary().getCard(target.getFirstTarget(), game);
            controller.moveCards(card, Zone.BATTLEFIELD, source, game);
        }
        controller.shuffleLibrary(source, game);
        return true;
    }

    @Override
    public GenesisPodEffect copy() {
        return new GenesisPodEffect(this);
    }
}
