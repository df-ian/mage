
package mage.cards.p;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.keyword.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class PersistentCombatant extends CardImpl {

    public PersistentCombatant(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{R}{W}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WARRIOR);

        this.power = new MageInt(3);
        this.toughness = new MageInt(1);

        this.addAbility(LifelinkAbility.getInstance());

        Ability ability = new EntersBattlefieldTriggeredAbility(new DamageTargetEffect(1, "it"), false);
        ability.addTarget(new TargetAnyTarget());
        this.addAbility(ability);

        this.addAbility(new EvokeAbility("{R}{W}"));
        this.addAbility(new PersistAbility());
    }

    private PersistentCombatant(final PersistentCombatant card) {
        super(card);
    }

    @Override
    public PersistentCombatant copy() {
        return new PersistentCombatant(this);
    }
}
