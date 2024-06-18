package mage.cards.r;

import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.CardTypesInGraveyardCount;
import mage.abilities.effects.common.continuous.SetBasePowerToughnessPlusOneSourceEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;

import java.util.UUID;

/**
 * @author Ian
 */
public final class RampagingLhurgoyf extends CardImpl {

    private static final DynamicValue powerValue = CardTypesInGraveyardCount.ALL;

    public RampagingLhurgoyf(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{G}");
        this.subtype.add(SubType.LHURGOYF);

        this.power = new MageInt(0);
        this.toughness = new MageInt(1);

        // Tarmogoyf's power is equal to the number of card types among cards in all graveyards and its toughness is equal to that number plus 1.
        this.addAbility(new SimpleStaticAbility(Zone.ALL, new SetBasePowerToughnessPlusOneSourceEffect(powerValue)));
    }

    private RampagingLhurgoyf(final RampagingLhurgoyf card) {
        super(card);
    }

    @Override
    public RampagingLhurgoyf copy() {
        return new RampagingLhurgoyf(this);
    }
}
