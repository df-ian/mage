
package mage.cards.f;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.ReturnFromGraveyardToHandTargetEffect;
import mage.abilities.keyword.EvokeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.target.common.TargetCardInYourGraveyard;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class FeywildWitness extends CardImpl {

    public FeywildWitness(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{2}{G}");
        this.subtype.add(SubType.MERFOLK);
        this.subtype.add(SubType.DRUID);

        this.power = new MageInt(1);
        this.toughness = new MageInt(3);

        // When Eternal Witness enters the battlefield, you may return target card from your graveyard to your hand.
        Ability ability = new EntersBattlefieldTriggeredAbility(new ReturnFromGraveyardToHandTargetEffect(), true);
        ability.addTarget(new TargetCardInYourGraveyard());
        this.addAbility(ability);

        this.addAbility(new EvokeAbility("{1}{G}"));
    }

    private FeywildWitness(final FeywildWitness card) {
        super(card);
    }

    @Override
    public FeywildWitness copy() {
        return new FeywildWitness(this);
    }
}
