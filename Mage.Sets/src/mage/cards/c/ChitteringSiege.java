
package mage.cards.c;

import mage.abilities.Ability;
import mage.abilities.condition.common.DeliriumCondition;
import mage.abilities.condition.common.RevoltCondition;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.game.Game;
import mage.game.permanent.token.SpiritToken;
import mage.players.Player;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class ChitteringSiege extends CardImpl {

    public ChitteringSiege(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{4}{G}");

        // Destroy target creature if it has converted mana cost 2 or less.
        // <i>Revolt</i> &mdash; Destroy that creature if it has converted mana cost 4 or less instead if a permanent you controlled left the battlefield this turn.
        this.getSpellAbility().addEffect(new ChitteringSiegeEffect());
    }

    private ChitteringSiege(final ChitteringSiege card) {
        super(card);
    }

    @Override
    public ChitteringSiege copy() {
        return new ChitteringSiege(this);
    }
}

class ChitteringSiegeEffect extends OneShotEffect {

    ChitteringSiegeEffect() {
        super(Outcome.PutCreatureInPlay);
        this.staticText = "Create five 1/1 green Squirrel creature tokens.<br><i>Delirium</i> &mdash; Create fifteen of those tokens instead if there are four or more card types among cards in your graveyard.";
    }

    private ChitteringSiegeEffect(final ChitteringSiegeEffect effect) {
        super(effect);
    }

    @Override
    public ChitteringSiegeEffect copy() {
        return new ChitteringSiegeEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            new CreateTokenEffect(new SpiritToken(), DeliriumCondition.instance.apply(game, source) ? 15 : 5).apply(game, source);
            return true;
        }
        return false;
    }
}
