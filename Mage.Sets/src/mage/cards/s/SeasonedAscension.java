package mage.cards.s;

import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.effects.common.replacement.ModifyCountersAddedEffect;
import mage.abilities.keyword.IndestructibleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.counters.CounterType;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SeasonedAscension extends CardImpl {

    public SeasonedAscension(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{G/W}");

        // If one or more +1/+1 counters would be put on a creature you control, that many plus one +1/+1 counters are put on it instead.
        this.addAbility(new SimpleStaticAbility(new ModifyCountersAddedEffect(
                StaticFilters.FILTER_CONTROLLED_CREATURE, CounterType.P1P1
        )));

        this.addAbility(new SimpleActivatedAbility(new GainAbilityControlledEffect(IndestructibleAbility.getInstance(), Duration.EndOfTurn), new SacrificeSourceCost()));

    }

    private SeasonedAscension(final SeasonedAscension card) {
        super(card);
    }

    @Override
    public SeasonedAscension copy() {
        return new SeasonedAscension(this);
    }
}
