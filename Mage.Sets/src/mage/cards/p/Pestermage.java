
package mage.cards.p;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.MayTapOrUntapTargetEffect;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.target.TargetPermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class Pestermage extends CardImpl {

    public Pestermage(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{2}{U}");
        this.subtype.add(SubType.FAERIE);
        this.subtype.add(SubType.WIZARD);

        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        // Flash
        this.addAbility(FlashAbility.getInstance());
        // Flying
        this.addAbility(FlyingAbility.getInstance());
        // When Pestermite enters the battlefield, you may tap or untap target permanent.
        Ability ability = new EntersBattlefieldTriggeredAbility(new MayTapOrUntapTargetEffect());
        ability.addTarget(new TargetPermanent());
        this.addAbility(ability);
    }

    private Pestermage(final Pestermage card) {
        super(card);
    }

    @Override
    public Pestermage copy() {
        return new Pestermage(this);
    }
}
