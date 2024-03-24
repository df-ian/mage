
package mage.cards.o;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.keyword.EscapeAbility;
import mage.abilities.keyword.EvokeAbility;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.LifelinkAbility;
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
public final class OrdruunReckoner extends CardImpl {

    public OrdruunReckoner(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{R}{W}");
        this.subtype.add(SubType.MINOTAUR);
        this.subtype.add(SubType.SOLDIER);

        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        this.addAbility(HasteAbility.getInstance());

        Ability ability = new EntersBattlefieldTriggeredAbility(new DamageTargetEffect(2, "it"), false);
        ability.addTarget(new TargetAnyTarget());
        ability.addEffect(new GainLifeEffect(2).concatBy("and"));
        this.addAbility(ability);
    }

    private OrdruunReckoner(final OrdruunReckoner card) {
        super(card);
    }

    @Override
    public OrdruunReckoner copy() {
        return new OrdruunReckoner(this);
    }
}
