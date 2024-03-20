
package mage.cards.r;

import mage.MageInt;
import mage.abilities.common.CantBlockAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.game.permanent.token.TreasureToken;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class RapaciousRodent extends CardImpl {

    public RapaciousRodent(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{B}");

        this.subtype.add(SubType.ZOMBIE);
        this.subtype.add(SubType.RAT);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        this.addAbility(new CantBlockAbility());

        // When Wily Goblin enters the battlefield, create a colorless Treasure artifact token with "{T}, Sacrifice this artifact: Add one mana of any color."
        this.addAbility(new EntersBattlefieldTriggeredAbility(new CreateTokenEffect(new TreasureToken(), 1, true)));
    }

    private RapaciousRodent(final RapaciousRodent card) {
        super(card);
    }

    @Override
    public RapaciousRodent copy() {
        return new RapaciousRodent(this);
    }
}
