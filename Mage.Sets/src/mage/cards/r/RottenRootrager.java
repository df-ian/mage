
package mage.cards.r;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTappedAbility;
import mage.abilities.keyword.RetraceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class RottenRootrager extends CardImpl {

    public RottenRootrager(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{B}");
        this.subtype.add(SubType.PLANT);
        this.subtype.add(SubType.HORROR);

        this.color.setBlack(true);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.addAbility(new EntersBattlefieldTappedAbility());
        this.addAbility(new RetraceAbility(this));
    }

    private RottenRootrager(final RottenRootrager card) {
        super(card);
    }

    @Override
    public RottenRootrager copy() {
        return new RottenRootrager(this);
    }
}
