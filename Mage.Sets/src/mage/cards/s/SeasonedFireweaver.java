package mage.cards.s;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.ExileSourceFromGraveCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.token.RedElementalToken;
import mage.players.Player;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SeasonedFireweaver extends CardImpl {

    public SeasonedFireweaver(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        this.addAbility(HasteAbility.getInstance());

        // When Seasoned Pyromancer enters the battlefield, discard two cards, then draw two cards. For each nonland card discarded this way, create a 1/1 red Elemental creature token.
        this.addAbility(new EntersBattlefieldTriggeredAbility(new SeasonedFireweaverEffect()));

    }

    private SeasonedFireweaver(final SeasonedFireweaver card) {
        super(card);
    }

    @Override
    public SeasonedFireweaver copy() {
        return new SeasonedFireweaver(this);
    }
}

class SeasonedFireweaverEffect extends OneShotEffect {

    SeasonedFireweaverEffect() {
        super(Outcome.Benefit);
        staticText = "discard a card, then draw a card. " +
                "For each nonland card discarded this way, " +
                "create a 1/1 red Elemental creature token.";
    }

    private SeasonedFireweaverEffect(final SeasonedFireweaverEffect effect) {
        super(effect);
    }

    @Override
    public SeasonedFireweaverEffect copy() {
        return new SeasonedFireweaverEffect(this);
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
