
package mage.cards.r;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.dynamicvalue.common.SourcePermanentPowerCount;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.filter.StaticFilters;
import mage.target.common.TargetControlledCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author LevelX2
 */
public final class RhoxWarchief extends CardImpl {

    private static final String EFFECT_TEXT = "another target creature you control gets +X/+X until end of turn, where X is {this}'s power";

    public RhoxWarchief(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}{G}");
        this.subtype.add(SubType.RHINO);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        this.addAbility(VigilanceAbility.getInstance());
        this.addAbility(TrampleAbility.getInstance());
        this.addAbility(HasteAbility.getInstance());

        // Whenever Wild Beastmaster attacks, each other creature you control gets +X/+X until end of turn, where X is Wild Beastmaster's power.
        SourcePermanentPowerCount creaturePower = new SourcePermanentPowerCount();
        BoostTargetEffect effect = new BoostTargetEffect(creaturePower, creaturePower, Duration.EndOfTurn);
        effect.setText(EFFECT_TEXT);
        Ability ability = new AttacksTriggeredAbility(effect, false);
        ability.addTarget(new TargetControlledCreaturePermanent(StaticFilters.FILTER_ANOTHER_CREATURE_YOU_CONTROL));
        this.addAbility(ability);
    }

    private RhoxWarchief(final RhoxWarchief card) {
        super(card);
    }

    @Override
    public RhoxWarchief copy() {
        return new RhoxWarchief(this);
    }
}
