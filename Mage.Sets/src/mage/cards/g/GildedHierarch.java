

package mage.cards.g;

import mage.MageInt;
import mage.abilities.keyword.ExaltedAbility;
import mage.abilities.mana.AnyColorManaAbility;
import mage.abilities.mana.BlueManaAbility;
import mage.abilities.mana.GreenManaAbility;
import mage.abilities.mana.WhiteManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;


/**
 *
 * @author Ian
 */
public final class GildedHierarch extends CardImpl {

    public GildedHierarch(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{G}");

        this.subtype.add(SubType.ELF);
        this.subtype.add(SubType.DRUID);
        this.power = new MageInt(0);
        this.toughness = new MageInt(1);
        this.addAbility(new ExaltedAbility());
        this.addAbility(new AnyColorManaAbility());
    }

    private GildedHierarch(final GildedHierarch card) {
        super(card);
    }

    @Override
    public GildedHierarch copy() {
        return new GildedHierarch(this);
    }
}
