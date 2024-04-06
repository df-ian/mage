package mage.cards.w;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTappedAbility;
import mage.abilities.common.EntersBattlefieldThisOrAnotherTriggeredAbility;
import mage.abilities.condition.Condition;
import mage.abilities.decorator.ConditionalInterveningIfTriggeredAbility;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.keyword.MenaceAbility;
import mage.abilities.mana.ColorlessManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.token.WhisperingHengeToken;
import mage.players.Player;

import java.util.UUID;

/**
 * @author Ian
 */
public final class WhisperingHenge extends CardImpl {

    public WhisperingHenge(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");


        // {T}: Add {C}.
        this.addAbility(new ColorlessManaAbility());

        // Whenever Field of the Dead or another land enters the battlefield under your control, if you control seven or more lands with different names, create a 2/2 black Zombie creature token.
        this.addAbility(new ConditionalInterveningIfTriggeredAbility(
                new EntersBattlefieldThisOrAnotherTriggeredAbility(
                        new CreateTokenEffect(new WhisperingHengeToken()), StaticFilters.FILTER_LAND, false, true
                ), WhisperingHengeCondition.instance, "Whenever {this} or another land " +
                "enters the battlefield under your control, if you control seven or more lands with different names, " +
                "create a 1/1 black Wraith creature token."
        ));
    }

    private WhisperingHenge(final WhisperingHenge card) {
        super(card);
    }

    @Override
    public WhisperingHenge copy() {
        return new WhisperingHenge(this);
    }
}

enum WhisperingHengeCondition implements Condition {
    instance;

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        if (player == null) {
            return false;
        }
        return game
                .getBattlefield()
                .getAllActivePermanents(StaticFilters.FILTER_LAND, source.getControllerId(), game)
                .stream()
                .map(permanent -> permanent.getName())
                .filter(s -> s.length() > 0)
                .distinct()
                .count() >= 7;
    }
}