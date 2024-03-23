package mage.cards.a;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DealsDamageToAPlayerAllTriggeredAbility;
import mage.abilities.dynamicvalue.common.SavedDamageValue;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SetTargetPointer;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.StaticFilters;
import mage.game.permanent.token.TreasureToken;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class ApexOfThePeaks extends CardImpl {

    public ApexOfThePeaks(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{5}{R}{R}");

        this.subtype.add(SubType.DRAGON);
        this.power = new MageInt(7);
        this.toughness = new MageInt(7);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        DamageTargetEffect d = new DamageTargetEffect(SavedDamageValue.MUCH);
        d.setSourceName("it");
        // Whenever a creature you control deals combat damage to a player, create that many Treasure tokens.
        Ability a = new DealsDamageToAPlayerAllTriggeredAbility(d,
                StaticFilters.FILTER_CONTROLLED_A_CREATURE,
                false, SetTargetPointer.NONE, true
        );
        a.addTarget(new TargetAnyTarget());
        this.addAbility(a);
    }

    private ApexOfThePeaks(final ApexOfThePeaks card) {
        super(card);
    }

    @Override
    public ApexOfThePeaks copy() {
        return new ApexOfThePeaks(this);
    }
}
