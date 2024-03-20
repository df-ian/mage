package mage.cards.g;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.DelveAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.Cards;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.util.CardUtil;

import java.util.UUID;

/**
 *
 * @author weirddan455
 */
public final class GravetideRegent extends CardImpl {

    public GravetideRegent(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{U}{B}");

        this.subtype.add(SubType.DRAGON);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Delve
        this.addAbility(new DelveAbility());

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Whenever a card leaves your graveyard, put a +1/+1 counter on Gravetide Regent.
        this.addAbility(new GravetideRegentTriggeredAbility());
    }

    private GravetideRegent(final GravetideRegent card) {
        super(card);
    }

    @Override
    public GravetideRegent copy() {
        return new GravetideRegent(this);
    }
}

class GravetideRegentTriggeredAbility extends TriggeredAbilityImpl {

    public GravetideRegentTriggeredAbility() {
        super(Zone.BATTLEFIELD, new AddCountersSourceEffect(CounterType.P1P1.createInstance()));
        setTriggerPhrase("Whenever a card leaves your graveyard, ");
    }

    private GravetideRegentTriggeredAbility(final GravetideRegentTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public GravetideRegentTriggeredAbility copy() {
        return new GravetideRegentTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ZONE_CHANGE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        ZoneChangeEvent zEvent = (ZoneChangeEvent) event;
        if (zEvent.getFromZone() == Zone.GRAVEYARD) {
            Card card = game.getCard(zEvent.getTargetId());
            return card != null && card.getOwnerId().equals(getControllerId());
        }
        return false;
    }
}
