package mage.cards.e;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.LeavesBattlefieldAllTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.ExileUntilSourceLeavesEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.counters.CounterType;
import mage.filter.StaticFilters;
import mage.target.common.TargetCreaturePermanent;
import mage.target.common.TargetOpponentsCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class ElspethGodsend extends CardImpl {

    public ElspethGodsend(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{3}{W}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN, SubType.WARRIOR);

        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        this.addAbility(FirstStrikeAbility.getInstance());
        this.addAbility(new WardAbility(new ManaCostsImpl<>("{2}")));

        // When Banisher Priest enters the battlefield, exile target creature an opponent controls until Banisher Priest leaves the battlefield.
        Ability ability = new EntersBattlefieldTriggeredAbility(new ExileUntilSourceLeavesEffect(), true);
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);

        ability = new LeavesBattlefieldAllTriggeredAbility(new AddCountersTargetEffect(CounterType.P1P1.createInstance(2)), StaticFilters.FILTER_CONTROLLED_PERMANENT);
        ability.addTarget(new TargetCreaturePermanent(0, 1));
        ability.addEffect(new GainLifeEffect(2).concatBy("and"));
        this.addAbility(ability);
    }

    private ElspethGodsend(final ElspethGodsend card) {
        super(card);
    }

    @Override
    public ElspethGodsend copy() {
        return new ElspethGodsend(this);
    }
}
