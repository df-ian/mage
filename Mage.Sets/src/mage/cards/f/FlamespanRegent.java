
package mage.cards.f;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.keyword.BlitzAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class FlamespanRegent extends CardImpl {

    public FlamespanRegent(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{R}{R}");
        this.subtype.add(SubType.DRAGON);
        this.power = new MageInt(6);
        this.toughness = new MageInt(5);

        this.addAbility(FlyingAbility.getInstance());

        // Whenever Raging Regisaur attacks, it deals 1 damage to any target.
        Ability ability = new AttacksTriggeredAbility(new DamageTargetEffect(3, "it"), false);
        ability.addTarget(new TargetAnyTarget());
        this.addAbility(ability);

        this.addAbility(new BlitzAbility(this, "{3}{R}{R}"));
    }

    private FlamespanRegent(final FlamespanRegent card) {
        super(card);
    }

    @Override
    public FlamespanRegent copy() {
        return new FlamespanRegent(this);
    }
}
