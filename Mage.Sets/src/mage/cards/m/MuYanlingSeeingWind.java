package mage.cards.m;

import mage.abilities.Ability;
import mage.abilities.CompoundAbility;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.LoyaltyAbility;
import mage.abilities.common.DrawCardControllerTriggeredAbility;
import mage.abilities.common.DrawNthCardTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.DrawDiscardControllerEffect;
import mage.abilities.effects.common.GetEmblemEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.effects.common.continuous.MaximumHandSizeControllerEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.DoubleStrikeAbility;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.HexproofAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.counters.CounterType;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.game.Game;
import mage.game.command.Emblem;
import mage.game.events.DamagedPlayerEvent;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.game.permanent.token.BlueBirdToken;
import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class MuYanlingSeeingWind extends CardImpl {

    public MuYanlingSeeingWind(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.PLANESWALKER}, "{1}{U}{U}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.YANLING);
        this.setStartingLoyalty(3);

        // Whenever you draw a card, put a loyalty counter on Teferi, Temporal Pilgrim.
        this.addAbility(new DrawNthCardTriggeredAbility(new CreateTokenEffect(new BlueBirdToken()), false, 2));
        
        this.addAbility(new LoyaltyAbility(new MuYanlingSeeingWindEffect1(), 1));
        
        this.addAbility(new LoyaltyAbility(new GetEmblemEffect(new MuYanlingEmblem()), 0));
    }

    private MuYanlingSeeingWind(final MuYanlingSeeingWind card) {
        super(card);
    }

    @Override
    public MuYanlingSeeingWind copy() {
        return new MuYanlingSeeingWind(this);
    }
}

class MuYanlingEmblem extends Emblem {
    public MuYanlingEmblem() {
        super("Emblem Mu Yanling");
        Effect e = new MaximumHandSizeControllerEffect(Integer.MAX_VALUE, Duration.Custom, MaximumHandSizeControllerEffect.HandSizeModification.SET);
        e.setText("You have no maximum hand size");
        this.getAbilities().add(new SimpleStaticAbility(Zone.COMMAND, e));
    }

    private MuYanlingEmblem(final MuYanlingEmblem card) {
        super(card);
    }

    @Override
    public MuYanlingEmblem copy() {
        return new MuYanlingEmblem(this);
    }
}

class MuYanlingSeeingWindEffect1 extends OneShotEffect {

    MuYanlingSeeingWindEffect1() {
        super(Outcome.DrawCard);
        this.staticText = "Whenever a creature you control deals combat damage to a player this turn, draw a card";
    }

    private MuYanlingSeeingWindEffect1(final MuYanlingSeeingWindEffect1 effect) {
        super(effect);
    }

    @Override
    public MuYanlingSeeingWindEffect1 copy() {
        return new MuYanlingSeeingWindEffect1(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        DelayedTriggeredAbility delayedAbility = new MuYanlingSeeingWindDamageTriggeredAbility();
        game.addDelayedTriggeredAbility(delayedAbility, source);
        return true;
    }
}

class MuYanlingSeeingWindDamageTriggeredAbility extends DelayedTriggeredAbility {

    MuYanlingSeeingWindDamageTriggeredAbility() {
        super(new DrawCardSourceControllerEffect(1), Duration.EndOfTurn, false);
    }

    private MuYanlingSeeingWindDamageTriggeredAbility(final MuYanlingSeeingWindDamageTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public MuYanlingSeeingWindDamageTriggeredAbility copy() {
        return new MuYanlingSeeingWindDamageTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DAMAGED_PLAYER;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.DAMAGED_PLAYER) {
            if (((DamagedPlayerEvent) event).isCombatDamage()) {
                Permanent creature = game.getPermanent(event.getSourceId());
                if (creature != null && creature.isControlledBy(controllerId)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever a creature you control deals combat damage to a player this turn, draw a card";
    }
}