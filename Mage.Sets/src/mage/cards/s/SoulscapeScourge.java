
package mage.cards.s;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.LookLibraryAndPickControllerEffect;
import mage.abilities.effects.common.RevealTopLandToBattlefieldElseHandEffect;
import mage.abilities.keyword.EscapeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.PutCards;
import mage.constants.SubType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SoulscapeScourge extends CardImpl {

    public SoulscapeScourge(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{B}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // When Coiling Oracle enters the battlefield, reveal the top card of your library. If it's a land card, put it onto the battlefield. Otherwise, put that card into your hand.
        this.addAbility(new EntersBattlefieldTriggeredAbility(new LookLibraryAndPickControllerEffect(3, 1, PutCards.HAND, PutCards.GRAVEYARD)));

        this.addAbility(new EscapeAbility(this, "{3}{B}", 3));
    }

    private SoulscapeScourge(final SoulscapeScourge card) {
        super(card);
    }

    @Override
    public SoulscapeScourge copy() {
        return new SoulscapeScourge(this);
    }
}
