
package mage.cards.t;

import mage.MageInt;
import mage.abilities.keyword.ShroudAbility;
import mage.abilities.mana.BlackManaAbility;
import mage.abilities.mana.GreenManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class TwistedBog extends CardImpl {

    public TwistedBog(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.LAND,CardType.CREATURE},"");
        this.subtype.add(SubType.SWAMP);
        this.subtype.add(SubType.HORROR);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        this.color.setBlack(true);

        this.addAbility(ShroudAbility.getInstance());

        // <i>(Dryad Arbor isn't a spell, it's affected by summoning sickness, and it has "{tap}: Add {G}.")</i>
        this.addAbility(new BlackManaAbility());
    }

    private TwistedBog(final TwistedBog card) {
        super(card);
    }

    @Override
    public TwistedBog copy() {
        return new TwistedBog(this);
    }
}
