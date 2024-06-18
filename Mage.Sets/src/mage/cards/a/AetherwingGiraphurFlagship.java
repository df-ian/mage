package mage.cards.a;

import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.effects.common.continuous.SetBasePowerSourceEffect;
import mage.abilities.effects.common.continuous.SetBasePowerToughnessSourceEffect;
import mage.abilities.keyword.CrewAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.constants.Zone;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.Predicates;

import java.util.UUID;

/**
 * @author Ian
 */
public final class AetherwingGiraphurFlagship extends CardImpl {

    private static final FilterControlledPermanent filter = new FilterControlledPermanent("nonland permanents you control");

    static {
        filter.add(Predicates.not(CardType.LAND.getPredicate()));
    }

    private static final DynamicValue starValue = new PermanentsOnBattlefieldCount(filter);

    public AetherwingGiraphurFlagship(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{1}");

        this.supertype.add(SuperType.LEGENDARY);

        this.subtype.add(SubType.VEHICLE);
        this.power = new MageInt(0);
        this.toughness = new MageInt(2);

        // Regal Bunnicorn's power and toughness are each equal to the number of nonland permanents you control.
        this.addAbility(new SimpleStaticAbility(Zone.ALL, new SetBasePowerSourceEffect(starValue)));

        this.addAbility(new CrewAbility(1));
    }

    private AetherwingGiraphurFlagship(final AetherwingGiraphurFlagship card) {
        super(card);
    }

    @Override
    public AetherwingGiraphurFlagship copy() {
        return new AetherwingGiraphurFlagship(this);
    }
}
