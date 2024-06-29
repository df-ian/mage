
package mage.cards.c;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfPostCombatMainTriggeredAbility;
import mage.abilities.costs.Cost;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.keyword.IntimidateAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.abilities.keyword.PartnerAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.game.Game;
import mage.game.events.DamagedPlayerEvent;
import mage.game.events.GameEvent;
import mage.game.events.GameEvent.EventType;
import mage.players.Player;
import mage.watchers.Watcher;

import java.util.*;

/**
 *
 * @author LevelX2
 */
public final class CynthaLineweaverAdept extends CardImpl {

    public CynthaLineweaverAdept(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}{B}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.KOR);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // Lifelink
        this.addAbility(IntimidateAbility.getInstance());

        // At the beginning of your postcombat main phase, you may pay X life, where X is the number of players that lost life this turn. If you do, draw X cards.
        this.addAbility(new BeginningOfPostCombatMainTriggeredAbility(new CynthaLineweaverAdeptEffect(), TargetController.YOU, true), new CynthaLineweaverAdeptWatcher());

    }

    private CynthaLineweaverAdept(final CynthaLineweaverAdept card) {
        super(card);
    }

    @Override
    public CynthaLineweaverAdept copy() {
        return new CynthaLineweaverAdept(this);
    }
}

class CynthaLineweaverAdeptEffect extends OneShotEffect {

    CynthaLineweaverAdeptEffect() {
        super(Outcome.DrawCard);
        this.staticText = "you may pay X life, where X is the number of players that lost life this turn. If you do, draw X cards";
    }

    private CynthaLineweaverAdeptEffect(final CynthaLineweaverAdeptEffect effect) {
        super(effect);
    }

    @Override
    public CynthaLineweaverAdeptEffect copy() {
        return new CynthaLineweaverAdeptEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            CynthaLineweaverAdeptWatcher watcher = game.getState().getWatcher(CynthaLineweaverAdeptWatcher.class);
            if (watcher != null) {
                int cardsToDraw = watcher.numPlayersLostLife();
                Cost cost = new PayLifeCost(cardsToDraw);
                if (cost.canPay(source, source, source.getControllerId(), game)
                        && cost.pay(source, game, source, source.getControllerId(), false)) {
                    controller.drawCards(cardsToDraw, source, game);
                }
                return true;
            }
        }
        return false;
    }
}

class CynthaLineweaverAdeptWatcher extends Watcher {

    // private final Set<UUID> players = new HashSet<>();
    private final List<Player> players = new ArrayList<>();

    public CynthaLineweaverAdeptWatcher() {
        super(WatcherScope.GAME);
    }

    @Override
    public void watch(GameEvent event, Game game) {
        if (event.getType() == EventType.LOST_LIFE) {
            if (!players.contains(game.getPlayer(event.getTargetId()))) { // opponenets can die before number of opponents are checked
                players.add(game.getPlayer(event.getTargetId()));
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        players.clear();
    }

    public int numPlayersLostLife() {
        return this.players.size();
    }

}
