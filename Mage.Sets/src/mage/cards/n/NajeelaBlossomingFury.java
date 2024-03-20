
package mage.cards.n;

import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.AttacksAllTriggeredAbility;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.condition.common.IsPhaseCondition;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalActivatedAbility;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.common.AdditionalCombatPhaseEffect;
import mage.abilities.effects.common.CreateTokenTargetEffect;
import mage.abilities.effects.common.UntapAllEffect;
import mage.abilities.effects.common.continuous.GainAbilityAllEffect;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;
import mage.game.permanent.token.WarriorToken;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class NajeelaBlossomingFury extends CardImpl {

    public NajeelaBlossomingFury(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{R}{R}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        WarriorToken token = new WarriorToken();
        token.addAbility(FirstStrikeAbility.getInstance());
        token.setColor(ObjectColor.RED);
        // Whenever a Warrior attacks, you may have its controller create a 1/1 white Warrior creature token that's tapped and attacking.
        this.addAbility(new AttacksTriggeredAbility(new CreateTokenTargetEffect(token, StaticValue.get(1), true, true)
                        .setText("create a 1/1 red Warrior creature token with first strike that's tapped and attacking")));

    }

    private NajeelaBlossomingFury(final NajeelaBlossomingFury card) {
        super(card);
    }

    @Override
    public NajeelaBlossomingFury copy() {
        return new NajeelaBlossomingFury(this);
    }
}
