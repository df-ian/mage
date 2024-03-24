package mage.cards.a;

import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.LeavesBattlefieldAllTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.dynamicvalue.common.CountersSourceCount;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.common.continuous.BoostAllEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SuperType;
import mage.constants.TargetController;
import mage.counters.CounterType;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class AureliasRenown extends CardImpl {

    private static final CountersSourceCount xValue = new CountersSourceCount(CounterType.VENGEANCE);

    public AureliasRenown(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{1}{R}{W}");

        this.addSuperType(SuperType.LEGENDARY);

        // At the beginning of each upkeep, put a strife counter on Crescendo of War.
        this.addAbility(new LeavesBattlefieldAllTriggeredAbility(new AddCountersSourceEffect(CounterType.VENGEANCE.createInstance(1), true), StaticFilters.FILTER_PERMANENT_A_CREATURE));

        // Attacking creatures get +1/+0 for each strife counter on Crescendo of War.
        this.addAbility(new SimpleStaticAbility(new BoostAllEffect(xValue, StaticValue.get(0),
                Duration.WhileOnBattlefield, StaticFilters.FILTER_PERMANENT_CREATURES_CONTROLLED, false)));
    }

    private AureliasRenown(final AureliasRenown card) {
        super(card);
    }

    @Override
    public AureliasRenown copy() {
        return new AureliasRenown(this);
    }
}
