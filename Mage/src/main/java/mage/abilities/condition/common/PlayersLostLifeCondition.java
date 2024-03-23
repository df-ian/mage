package mage.abilities.condition.common;

import mage.abilities.Ability;
import mage.abilities.condition.Condition;
import mage.abilities.dynamicvalue.common.OpponentsLostLifeCount;
import mage.constants.ColoredManaSymbol;
import mage.game.Game;
import mage.players.Player;
import mage.watchers.common.PlayerLostLifeWatcher;

/**
 * @author Ian
 */
public enum PlayersLostLifeCondition implements Condition {
    instance(0),
    revenant(2);

    protected int amt = 0;


    PlayersLostLifeCondition(int amt) {
        this.amt = amt;
    }

    @Override
    public boolean apply(Game game, Ability source) {
        PlayerLostLifeWatcher watcher = game.getState().getWatcher(PlayerLostLifeWatcher.class);
        for (Player player : game.getState().getPlayers().values()) {
            if (watcher.getLifeLost(player.getId()) >= amt) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "a player lost " + amt + " or more life this turn";
    }
}
