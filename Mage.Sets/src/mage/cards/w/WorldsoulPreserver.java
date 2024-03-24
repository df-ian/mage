package mage.cards.w;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.LandfallAbility;
import mage.abilities.common.PutIntoGraveFromAnywhereSourceAbility;
import mage.abilities.effects.common.ExileSourceEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.counters.CounterType;
import mage.target.common.TargetCreaturePermanent;
import mage.target.common.TargetCreaturePermanentAmount;

import java.util.UUID;

/**
 * @author Ian
 */
public final class WorldsoulPreserver extends CardImpl {

    public WorldsoulPreserver(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G}{W}");

        this.subtype.add(SubType.ELF);
        this.subtype.add(SubType.DRUID);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        this.addAbility(LifelinkAbility.getInstance());

        Ability a = new LandfallAbility(new AddCountersTargetEffect(CounterType.P1P1.createInstance(2)));
        a.addTarget(new TargetCreaturePermanent(0, 1));

        this.addAbility(a);
    }

    private WorldsoulPreserver(final WorldsoulPreserver card) {
        super(card);
    }

    @Override
    public WorldsoulPreserver copy() {
        return new WorldsoulPreserver(this);
    }
}
