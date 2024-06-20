package mage.cards.u;

import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.continuous.HasSubtypesSourceEffect;
import mage.abilities.keyword.WardAbility;
import mage.abilities.mana.AnyColorManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;

import java.util.UUID;

/**
 * @author Ian
 */
public final class UndermountainParagon extends CardImpl {

    public UndermountainParagon(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}");

        this.subtype.add(SubType.HUMAN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        // Stonework Packbeast is also a Cleric, Rogue, Warrior, and Wizard.
        this.addAbility(new SimpleStaticAbility(Zone.ALL, new HasSubtypesSourceEffect(
                SubType.CLERIC, SubType.ROGUE, SubType.WARRIOR, SubType.WIZARD
        )));

        this.addAbility(new WardAbility(new ManaCostsImpl<>("{1}")));
    }

    private UndermountainParagon(final UndermountainParagon card) {
        super(card);
    }

    @Override
    public UndermountainParagon copy() {
        return new UndermountainParagon(this);
    }
}
