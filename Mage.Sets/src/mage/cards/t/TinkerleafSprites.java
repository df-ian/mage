
package mage.cards.t;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.keyword.ScryEffect;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.FlyingAbility;
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
public final class TinkerleafSprites extends CardImpl {

    public TinkerleafSprites(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT, CardType.CREATURE},"{W}");
        this.subtype.add(SubType.FAERIE);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        this.addAbility(FlashAbility.getInstance());

        this.addAbility(FlyingAbility.getInstance());
        this.addAbility(LifelinkAbility.getInstance());

        // When Omenspeaker enters the battlefield, scry 2.
        this.addAbility(new EntersBattlefieldTriggeredAbility(new ScryEffect(1), false));
    }

    private TinkerleafSprites(final TinkerleafSprites card) {
        super(card);
    }

    @Override
    public TinkerleafSprites copy() {
        return new TinkerleafSprites(this);
    }
}
