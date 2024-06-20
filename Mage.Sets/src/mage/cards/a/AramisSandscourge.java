package mage.cards.a;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.CompoundAbility;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.FullPartyCondition;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.decorator.ConditionalPreventionEffect;
import mage.abilities.dynamicvalue.common.PartyCount;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.common.PreventDamageToAttachedEffect;
import mage.abilities.effects.common.continuous.BoostAllEffect;
import mage.abilities.effects.common.continuous.BoostEquippedEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.hint.common.PartyCountHint;
import mage.abilities.keyword.DoubleStrikeAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 * @author Ian
 */
public final class AramisSandscourge extends CardImpl {

    public AramisSandscourge(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W}{B}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);
        this.power = new MageInt(2);
        this.toughness = new MageInt(4);

        // Trample
        this.addAbility(VigilanceAbility.getInstance());

        Ability ability = new SimpleStaticAbility(new BoostAllEffect(
                PartyCount.instance, StaticValue.get(0), Duration.WhileOnBattlefield, StaticFilters.FILTER_CONTROLLED_CREATURES, false
        ).setText("Creatures you control get +1/+0 for each creature in your party"));

        this.addAbility(ability);

        this.addAbility(new SimpleStaticAbility(new ConditionalContinuousEffect(
                new GainAbilityControlledEffect(
                        new CompoundAbility(DoubleStrikeAbility.getInstance(), LifelinkAbility.getInstance()),
                        Duration.WhileOnBattlefield,
                        StaticFilters.FILTER_CONTROLLED_CREATURES
                ), FullPartyCondition.instance, "as long as you have a full party, " +
                "creatures you control have double strike and lifelink"
        )).addHint(PartyCountHint.instance));
    }

    private AramisSandscourge(final AramisSandscourge card) {
        super(card);
    }

    @Override
    public AramisSandscourge copy() {
        return new AramisSandscourge(this);
    }
}
