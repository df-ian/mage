
package mage.cards.j;

import mage.MageInt;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.DrawCardTargetEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.GameEvent.EventType;
import mage.game.permanent.Permanent;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class JadeSphinx extends CardImpl {

    public JadeSphinx(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{U}{U}");
        this.subtype.add(SubType.SPHINX);

        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        // Flying
        this.addAbility(FlyingAbility.getInstance());
        this.addAbility(new WardAbility(new ManaCostsImpl<>("{2}")));
        // Whenever an opponent draws a card, you may draw two cards.
        this.addAbility(new JadeSphinxTriggeredAbility());

        this.addAbility(new JadeSphinxAbility());
    }

    private JadeSphinx(final JadeSphinx card) {
        super(card);
    }

    @Override
    public JadeSphinx copy() {
        return new JadeSphinx(this);
    }

}

class JadeSphinxTriggeredAbility extends TriggeredAbilityImpl {

    JadeSphinxTriggeredAbility() {
        super(Zone.BATTLEFIELD, new GainLifeEffect(1), false);
    }

    private JadeSphinxTriggeredAbility(final JadeSphinxTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public JadeSphinxTriggeredAbility copy() {
        return new JadeSphinxTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == EventType.DREW_CARD;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        return game.getPermanent(sourceId) != null; // the Sphinx must be on the battlefield
    }

    @Override
    public String getRule() {
        return "Whenever a player draws a card, you gain 1 life.";
    }
}


class JadeSphinxAbility extends TriggeredAbilityImpl {

    public JadeSphinxAbility() {
        super(Zone.BATTLEFIELD, new DrawCardTargetEffect(1));
    }

    private JadeSphinxAbility(final JadeSphinxAbility ability) {
        super(ability);
    }

    @Override
    public JadeSphinxAbility copy() {
        return new JadeSphinxAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DRAW_STEP_PRE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        this.getEffects().get(0).setTargetPointer(new FixedTarget(event.getPlayerId()));
        return true;
    }

    @Override
    public boolean checkInterveningIfClause(Game game) {
        Permanent source = game.getPermanentOrLKIBattlefield(this.sourceId);
        return source != null;
    }

    @Override
    public String getRule() {
        return "At the beginning of each player's draw step, that player draws an additional card.";
    }

}