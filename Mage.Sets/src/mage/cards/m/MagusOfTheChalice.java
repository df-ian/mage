
package mage.cards.m;

import mage.MageInt;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.effects.common.EntersBattlefieldWithXCountersEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.game.stack.Spell;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class MagusOfTheChalice extends CardImpl {

    public MagusOfTheChalice(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{X}{W}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);
        this.power = new MageInt(0);
        this.toughness = new MageInt(1);


        // Chalice of the Void enters the battlefield with X charge counters on it.
        this.addAbility(new EntersBattlefieldAbility(new EntersBattlefieldWithXCountersEffect(CounterType.P1P1.createInstance())));

        // Whenever a player casts a spell with converted mana cost equal to the number of charge counters on Chalice of the Void, counter that spell.
        this.addAbility(new MagusOfTheChaliceTriggeredAbility());
    }

    private MagusOfTheChalice(final MagusOfTheChalice card) {
        super(card);
    }

    @Override
    public MagusOfTheChalice copy() {
        return new MagusOfTheChalice(this);
    }
}

class MagusOfTheChaliceTriggeredAbility extends TriggeredAbilityImpl {

    public MagusOfTheChaliceTriggeredAbility() {
        super(Zone.BATTLEFIELD, new CounterTargetEffect());
    }

    private MagusOfTheChaliceTriggeredAbility(final MagusOfTheChaliceTriggeredAbility abiltity) {
        super(abiltity);
    }

    @Override
    public MagusOfTheChaliceTriggeredAbility copy() {
        return new MagusOfTheChaliceTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.SPELL_CAST;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        Permanent chalice = game.getPermanent(getSourceId());
        Spell spell = game.getStack().getSpell(event.getTargetId());
        if (spell != null && chalice != null && spell.getManaValue() == chalice.getCounters(game).getCount(CounterType.P1P1)) {
            for (Effect effect : this.getEffects()) {
                effect.setTargetPointer(new FixedTarget(event.getTargetId()));
            }
            return true;
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever a player casts a spell with mana value equal to the number of charge counters on {this}, counter that spell.";
    }
}
