package mage.cards.j;

import mage.abilities.Ability;
import mage.abilities.common.BeginningOfCombatTriggeredAbility;
import mage.abilities.common.LeavesBattlefieldAllTriggeredAbility;
import mage.abilities.condition.common.SourceHasCountersCondition;
import mage.abilities.decorator.ConditionalInterveningIfTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SuperType;
import mage.constants.TargetController;
import mage.counters.Counter;
import mage.counters.Counters;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.permanent.Permanent;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class JadeObelisk extends CardImpl {

    public JadeObelisk(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{G}");

        // Whenever a creature you control leaves the battlefield, if it had counters on it, put those counters on The Ozolith.
        this.addAbility(new JadeObeliskTriggeredAbility());

        // At the beginning of combat on your turn, if The Ozolith has counters on it, you may move all counters from The Ozolith onto target creature.
        Ability ability = new ConditionalInterveningIfTriggeredAbility(
                new BeginningOfCombatTriggeredAbility(
                        new JadeObeliskMoveCountersEffect(), TargetController.YOU, true
                ), SourceHasCountersCondition.instance, "At the beginning of combat on your turn, " +
                "if {this} has counters on it, you may move all counters from {this} onto target creature."
        );
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);
    }

    private JadeObelisk(final JadeObelisk card) {
        super(card);
    }

    @Override
    public JadeObelisk copy() {
        return new JadeObelisk(this);
    }
}

class JadeObeliskTriggeredAbility extends LeavesBattlefieldAllTriggeredAbility {

    JadeObeliskTriggeredAbility() {
        super(null, StaticFilters.FILTER_CONTROLLED_CREATURE);
    }

    private JadeObeliskTriggeredAbility(final JadeObeliskTriggeredAbility ability) {
        super(ability);
    }

    public JadeObeliskTriggeredAbility copy() {
        return new JadeObeliskTriggeredAbility(this);
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (!super.checkTrigger(event, game)) {
            return false;
        }
        Permanent permanent = ((ZoneChangeEvent) event).getTarget();
        Counters counters = permanent.getCounters(game);
        if (counters.values().stream().mapToInt(Counter::getCount).noneMatch(x -> x > 0)) {
            return false;
        }
        this.getEffects().clear();
        this.addEffect(new JadeObeliskLeaveEffect(counters));
        return true;
    }

    public String getRule() {
        return "Whenever a creature you control leaves the battlefield, " +
                "if it had counters on it, put those counters on {this}.";
    }
}

class JadeObeliskLeaveEffect extends OneShotEffect {

    private final Counters counters;

    JadeObeliskLeaveEffect(Counters counters) {
        super(Outcome.Benefit);
        this.counters = counters.copy();
    }

    private JadeObeliskLeaveEffect(final JadeObeliskLeaveEffect effect) {
        super(effect);
        this.counters = effect.counters.copy();
    }

    @Override
    public JadeObeliskLeaveEffect copy() {
        return new JadeObeliskLeaveEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(source.getSourceId());
        if (permanent == null) {
            return false;
        }
        counters.values()
                .forEach(counter -> permanent.addCounters(counter, source.getControllerId(), source, game));
        return true;
    }
}

class JadeObeliskMoveCountersEffect extends OneShotEffect {

    JadeObeliskMoveCountersEffect() {
        super(Outcome.Benefit);
    }

    private JadeObeliskMoveCountersEffect(final JadeObeliskMoveCountersEffect effect) {
        super(effect);
    }

    @Override
    public JadeObeliskMoveCountersEffect copy() {
        return new JadeObeliskMoveCountersEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(source.getSourceId());
        Permanent creature = game.getPermanent(source.getFirstTarget());
        if (permanent == null || creature == null) {
            return false;
        }
        permanent.getCounters(game)
                .copy()
                .values()
                .stream()
                .filter(counter -> creature.addCounters(counter, source.getControllerId(), source, game))
                .forEach(counter -> permanent.removeCounters(counter, source, game));
        return true;
    }
}
