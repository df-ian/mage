
package mage.cards.s;

import mage.MageObjectReference;
import mage.Mana;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CounterTargetWithReplacementEffect;
import mage.abilities.effects.common.DamageControllerEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.continuous.GainSuspendEffect;
import mage.abilities.keyword.SuspendAbility;
import mage.abilities.mana.SimpleManaAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.PutCards;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.stack.Spell;
import mage.players.Player;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SuspensionCanopy extends CardImpl {

    public SuspensionCanopy(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.LAND},"");

        Ability ability = new SimpleManaAbility(Zone.BATTLEFIELD, Mana.ColorlessMana(3), new TapSourceCost());
        ability.addCost(new ManaCostsImpl<>("{2}"));
        this.addAbility(ability);

        this.addAbility(new SuspensionCanopyTriggeredAbility(new DoIfCostPaid(new SuspensionCanopyEffect(), new ManaCostsImpl<>("{1}"))));
    }

    private SuspensionCanopy(final SuspensionCanopy card) {
        super(card);
    }

    @Override
    public SuspensionCanopy copy() {
        return new SuspensionCanopy(this);
    }
}

class SuspensionCanopyTriggeredAbility extends TriggeredAbilityImpl {

    public SuspensionCanopyTriggeredAbility(Effect e) {
        super(Zone.BATTLEFIELD, e, false);
    }

    SuspensionCanopyTriggeredAbility(final SuspensionCanopyTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.SPELL_CAST;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        this.getEffects().get(0).setTargetPointer(new FixedTarget(event.getTargetId()));
        return true;
    }

    @Override
    public String getRule() {
        return "When a player casts a spell, you may pay {1}. If you do, exile that spell and put a time counter on it. If it doesn't have suspend, it gains suspend.";
    }

    @Override
    public SuspensionCanopyTriggeredAbility copy() {
        return new SuspensionCanopyTriggeredAbility(this);
    }
}

class SuspensionCanopyEffect extends OneShotEffect {

    SuspensionCanopyEffect() {
        super(Outcome.Benefit);
        this.staticText = "Exile it with a time counter on it. If it doesn't have suspend, it gains suspend";
    }

    private SuspensionCanopyEffect(final SuspensionCanopyEffect effect) {
        super(effect);
    }

    @Override
    public SuspensionCanopyEffect copy() {
        return new SuspensionCanopyEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Spell spell = game.getStack().getSpell(getTargetPointer().getFirst(game, source));
        if (controller != null && spell != null) {
            Effect effect = new CounterTargetWithReplacementEffect(PutCards.EXILED);
            effect.setTargetPointer(this.getTargetPointer().copy());
            Card card = game.getCard(spell.getSourceId());
            if (card != null && effect.apply(game, source) && game.getState().getZone(card.getId()) == Zone.EXILED) {
                boolean hasSuspend = card.getAbilities(game).containsClass(SuspendAbility.class);
                UUID exileId = SuspendAbility.getSuspendExileId(controller.getId(), game);
                if (controller.moveCardToExileWithInfo(card, exileId, "Suspended cards of " + controller.getName(), source, game, Zone.HAND, true)) {
                    card.addCounters(CounterType.TIME.createInstance(3), source.getControllerId(), source, game);
                    if (!hasSuspend) {
                        game.addEffect(new GainSuspendEffect(new MageObjectReference(card, game)), source);
                    }
                    game.informPlayers(controller.getLogName() + " suspends 3 - " + card.getName());
                }
            }
            return true;
        }
        return false;
    }
}