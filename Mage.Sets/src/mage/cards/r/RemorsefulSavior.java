package mage.cards.r;

import mage.MageInt;
import mage.abilities.common.LeavesBattlefieldAllTriggeredAbility;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.counters.CounterType;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 * @author Ian
 */
public final class RemorsefulSavior extends CardImpl {

    public RemorsefulSavior(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}");

        this.subtype.add(SubType.ANGEL);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        this.addAbility(new LeavesBattlefieldAllTriggeredAbility(new AddCountersSourceEffect(CounterType.P1P1.createInstance(1)), StaticFilters.FILTER_CONTROLLED_A_PERMANENT));
    }

    private RemorsefulSavior(final RemorsefulSavior card) {
        super(card);
    }

    @Override
    public RemorsefulSavior copy() {
        return new RemorsefulSavior(this);
    }
}
