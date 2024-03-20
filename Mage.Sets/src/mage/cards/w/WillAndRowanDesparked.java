
package mage.cards.w;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.DrawDiscardControllerEffect;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.ProwessAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class WillAndRowanDesparked extends CardImpl {

    public WillAndRowanDesparked(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{U}{R}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);

        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Haste
        this.addAbility(HasteAbility.getInstance());
        // Prowess
        this.addAbility(new ProwessAbility());

        this.addAbility(new EntersBattlefieldTriggeredAbility(new DrawDiscardControllerEffect(2, 2, false)));
    }

    private WillAndRowanDesparked(final WillAndRowanDesparked card) {
        super(card);
    }

    @Override
    public WillAndRowanDesparked copy() {
        return new WillAndRowanDesparked(this);
    }
}
