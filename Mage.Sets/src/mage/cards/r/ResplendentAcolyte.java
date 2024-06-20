package mage.cards.r;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfCombatTriggeredAbility;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.TargetController;
import mage.counters.CounterType;
import mage.target.common.TargetControlledCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ResplendentAcolyte extends CardImpl {

    public ResplendentAcolyte(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.CLERIC);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        this.addAbility(VigilanceAbility.getInstance());

        // At the beginning of combat on your turn, put a +1/+1 counter on target creature you control.
        Ability ability = new BeginningOfCombatTriggeredAbility(
                new AddCountersTargetEffect(CounterType.P1P1.createInstance()), TargetController.YOU, false
        );
        ability.addTarget(new TargetControlledCreaturePermanent());
        this.addAbility(ability);
    }

    private ResplendentAcolyte(final ResplendentAcolyte card) {
        super(card);
    }

    @Override
    public ResplendentAcolyte copy() {
        return new ResplendentAcolyte(this);
    }
}
