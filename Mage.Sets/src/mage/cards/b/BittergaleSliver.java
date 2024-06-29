package mage.cards.b;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.keyword.DeathtouchAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 * @author Ian
 */
public final class BittergaleSliver extends CardImpl {

    public BittergaleSliver(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{U/B}");

        this.subtype.add(SubType.SLIVER);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // Sliver creatures you control have flying and haste.
        Ability ability = new SimpleStaticAbility(new GainAbilityControlledEffect(
                FlyingAbility.getInstance(), Duration.WhileOnBattlefield,
                StaticFilters.FILTER_PERMANENT_ALL_SLIVERS
        ).setText("Sliver creatures you control have flying"));
        ability.addEffect(new GainAbilityControlledEffect(
                DeathtouchAbility.getInstance(), Duration.WhileOnBattlefield,
                StaticFilters.FILTER_PERMANENT_ALL_SLIVERS
        ).setText("and deathtouch"));
        this.addAbility(ability);
    }

    private BittergaleSliver(final BittergaleSliver card) {
        super(card);
    }

    @Override
    public BittergaleSliver copy() {
        return new BittergaleSliver(this);
    }
}
