package mage.abilities.common;

import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.effects.Effect;
import mage.constants.PhaseStep;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.watchers.common.CardsDrawnDuringDrawStepWatcher;

/**
 * @author Ian
 */
public class YouDrawCardExceptFirstCardDrawStepTriggeredAbility extends TriggeredAbilityImpl {

    public YouDrawCardExceptFirstCardDrawStepTriggeredAbility(Zone zone, Effect effect, Boolean optional) {
        super(zone, effect, optional);
        this.addWatcher(new CardsDrawnDuringDrawStepWatcher());
        setTriggerPhrase("Whenever you draw a card except the first one you draw in each of your draw steps, ");
    }

    public YouDrawCardExceptFirstCardDrawStepTriggeredAbility(YouDrawCardExceptFirstCardDrawStepTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DREW_CARD;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (this.getControllerId() == event.getPlayerId()) {
            if (game.isActivePlayer(event.getPlayerId())
                    && game.getPhase().getStep().getType() == PhaseStep.DRAW) {
                CardsDrawnDuringDrawStepWatcher watcher = game.getState().getWatcher(CardsDrawnDuringDrawStepWatcher.class);
                return watcher != null && watcher.getAmountCardsDrawn(event.getPlayerId()) > 1;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public YouDrawCardExceptFirstCardDrawStepTriggeredAbility copy() {
        return new YouDrawCardExceptFirstCardDrawStepTriggeredAbility(this);
    }
}
