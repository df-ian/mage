
package mage.cards.s;

import mage.MageInt;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.effects.common.LoseLifeSourceControllerEffect;
import mage.abilities.effects.common.discard.DiscardTargetEffect;
import mage.abilities.keyword.DeathtouchAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SwiftslashThief extends CardImpl {

    public SwiftslashThief(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{B}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ROGUE);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // Flying
        this.addAbility(DeathtouchAbility.getInstance());
        // Haste
        this.addAbility(HasteAbility.getInstance());
        DealsCombatDamageToAPlayerTriggeredAbility a = new DealsCombatDamageToAPlayerTriggeredAbility(new DiscardTargetEffect(1), false, true);
        a.addEffect(new LoseLifeSourceControllerEffect(1).concatBy("."));
        this.addAbility(a);
    }

    private SwiftslashThief(final SwiftslashThief card) {
        super(card);
    }

    @Override
    public SwiftslashThief copy() {
        return new SwiftslashThief(this);
    }
}
