
package mage.cards.v;

import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateDelayedTriggeredAbilityEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.GameEvent.EventType;
import mage.game.stack.Spell;

import java.util.UUID;

public final class VerdantAffinity extends CardImpl {

    public VerdantAffinity (UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{G}");


        // Whenever you cast a creature spell this turn, draw a card.
        this.getSpellAbility().addEffect(new CreateDelayedTriggeredAbilityEffect(new VerdantAffinityTriggeredAbility()));

        this.addAbility(new FlashbackAbility(this, new ManaCostsImpl<>("{3}{G}")));
    }

    private VerdantAffinity(final VerdantAffinity card) {
        super(card);
    }

    @Override
    public VerdantAffinity copy() {
        return new VerdantAffinity(this);
    }

}

class VerdantAffinityTriggeredAbility extends DelayedTriggeredAbility {

    public VerdantAffinityTriggeredAbility() {
        super(new DrawCardSourceControllerEffect(1), Duration.EndOfTurn, false);
    }

    private VerdantAffinityTriggeredAbility(final VerdantAffinityTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == EventType.SPELL_CAST;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getPlayerId().equals(this.getControllerId())) {
            Spell spell = game.getStack().getSpell(event.getTargetId());
            if (spell != null && StaticFilters.FILTER_SPELL_A_CREATURE.match(spell, game)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public VerdantAffinityTriggeredAbility copy() {
        return new VerdantAffinityTriggeredAbility(this);
    }

    @Override
    public String getRule() {
        return "Whenever you cast a creature spell this turn, draw a card.";
    }
}