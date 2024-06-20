package mage.cards.s;

import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.ExileSourceFromGraveCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.token.FoodToken;
import mage.game.permanent.token.RedElementalToken;
import mage.players.Player;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SeasonedPyromancy extends CardImpl {

    public SeasonedPyromancy(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{R}");

        this.getSpellAbility().addEffect(new SeasonedPyromancyEffect());

        Ability ability = new SimpleActivatedAbility(
                Zone.GRAVEYARD,
                new CreateTokenEffect(new RedElementalToken(), 1),
                new ManaCostsImpl<>("{1}{R}")
        );
        ability.addCost(new ExileSourceFromGraveCost());
        this.addAbility(ability);
    }


    private SeasonedPyromancy(final SeasonedPyromancy card) {
        super(card);
    }

    @Override
    public SeasonedPyromancy copy() {
        return new SeasonedPyromancy(this);
    }
}


class SeasonedPyromancyEffect extends OneShotEffect {

    SeasonedPyromancyEffect() {
        super(Outcome.Benefit);
        staticText = "discard a card, then draw a card. " +
                "For each nonland card discarded this way, " +
                "create a 1/1 red Elemental creature token.";
    }

    private SeasonedPyromancyEffect(final SeasonedPyromancyEffect effect) {
        super(effect);
    }

    @Override
    public SeasonedPyromancyEffect copy() {
        return new SeasonedPyromancyEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        if (player == null) {
            return false;
        }
        int nonlands = player
                .discard(1, false, false, source, game)
                .count(StaticFilters.FILTER_CARD_NON_LAND, game);
        player.drawCards(1, source, game);
        if (nonlands == 0) {
            return true;
        }
        new RedElementalToken().putOntoBattlefield(nonlands, game, source, source.getControllerId());
        return true;
    }
}
