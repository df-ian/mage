package mage.cards.s;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SpellCastOpponentTriggeredAbility;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.LoseLifeTargetEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.counters.CounterType;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SunriseChampion extends CardImpl {

    public SunriseChampion(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{W}");
        this.subtype.add(SubType.ELF);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        this.addAbility(VigilanceAbility.getInstance());

        // Whenever an opponent casts a noncreature spell, that player loses 2 life and you gain 2 life.
        Ability ability = new SpellCastOpponentTriggeredAbility(Zone.BATTLEFIELD, new AddCountersSourceEffect(CounterType.P1P1.createInstance(1)),
                StaticFilters.FILTER_SPELL_A_NON_CREATURE, false, SetTargetPointer.PLAYER);
        this.addAbility(ability);
    }

    private SunriseChampion(final SunriseChampion card) {
        super(card);
    }

    @Override
    public SunriseChampion copy() {
        return new SunriseChampion(this);
    }
}
