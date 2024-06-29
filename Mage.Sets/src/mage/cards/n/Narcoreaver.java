
package mage.cards.n;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.ZoneChangeTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.ReturnSourceFromGraveyardToBattlefieldEffect;
import mage.abilities.keyword.CyclingAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.events.ZoneChangeGroupEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class Narcoreaver extends CardImpl {

    public Narcoreaver(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{B}");
        this.subtype.add(SubType.SNAKE);
        this.subtype.add(SubType.HORROR);

        this.power = new MageInt(3);
        this.toughness = new MageInt(1);

        this.addAbility(new MenaceAbility());
        // When Narcoreaver is put into your graveyard from your library, you may put it onto the battlefield.
        this.addAbility(new NarcoreaverTriggeredAbility());

        this.addAbility(new CyclingAbility(new ManaCostsImpl<>("{2}")));
    }

    private Narcoreaver(final Narcoreaver card) {
        super(card);
    }

    @Override
    public Narcoreaver copy() {
        return new Narcoreaver(this);
    }
}

class NarcoreaverEffect extends OneShotEffect {
    public NarcoreaverEffect() {
        super(Outcome.PutCreatureInPlay);
    }

    private NarcoreaverEffect(final NarcoreaverEffect e) {
        super(e);
    }

    @Override
    public Effect copy() {
        return new NarcoreaverEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        if (player == null) {
            return false;
        }
        Card cardSrc = game.getCard(source.getId());
        if (cardSrc == null) {
            return false;
        }
        player.moveCards(cardSrc, Zone.BATTLEFIELD, source, game);
        Permanent permanent = game.getPermanent(cardSrc.getId());
        if (permanent != null) {
            permanent.addCounters(CounterType.FINALITY.createInstance(), source, game);
        }
        return true;
    }
}

class NarcoreaverTriggeredAbility extends TriggeredAbilityImpl {

    public NarcoreaverTriggeredAbility() {
        super(Zone.BATTLEFIELD, new NarcoreaverEffect(), false);
    }

    private NarcoreaverTriggeredAbility(final NarcoreaverTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ZONE_CHANGE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        ZoneChangeEvent zEvent = (ZoneChangeEvent) event;
        return zEvent != null
                && Zone.GRAVEYARD == zEvent.getToZone()
                && Zone.HAND != zEvent.getFromZone();
    }

    @Override
    public NarcoreaverTriggeredAbility copy() {
        return new NarcoreaverTriggeredAbility(this);
    }

    @Override
    public String getRule() {
        return "When {this} is put into your graveyard from anywhere other than your hand, you may put it onto the battlefield with a finality counter.";
    }
}