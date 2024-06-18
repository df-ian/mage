
package mage.abilities.effects.common;

import mage.abilities.Ability;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.game.permanent.token.Token;
import mage.players.Player;
import mage.target.TargetPlayer;

/**
 * @author JRHerlehy
 */
public class ExileAndCreateTokenEffect extends OneShotEffect {

    private Token token;

    public ExileAndCreateTokenEffect(Token token) {
        super(Outcome.Removal);
        this.token = token;
        staticText = "Exile target creature. Its controller creates a " + token.getDescription();
    }

    private ExileAndCreateTokenEffect(final ExileAndCreateTokenEffect effect) {
        super(effect);
    }

    @Override
    public ExileAndCreateTokenEffect copy() {
        return new ExileAndCreateTokenEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanentOrLKIBattlefield(getTargetPointer().getFirst(game, source));
        if (permanent == null) {
            return false;
        }
        Player controller = game.getPlayer(source.getControllerId());
        Player player = game.getPlayer(permanent.getControllerId());
        if (controller == null || player == null) {
            return true;
        }
        this.token.putOntoBattlefield(1, game, source, player.getId());
        return true;
    }
}
