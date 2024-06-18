
package mage.cards.s;

import mage.MageInt;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class ScionOfSkithryx extends CardImpl {

    public ScionOfSkithryx(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT,CardType.CREATURE},"{1}{B/P}");
        this.subtype.add(SubType.PHYREXIAN);
        this.subtype.add(SubType.DRAKE);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        this.addAbility(FlyingAbility.getInstance());
        this.addAbility(LifelinkAbility.getInstance());
        this.addAbility(HasteAbility.getInstance());
    }

    private ScionOfSkithryx(final ScionOfSkithryx card) {
        super(card);
    }

    @Override
    public ScionOfSkithryx copy() {
        return new ScionOfSkithryx(this);
    }
}
