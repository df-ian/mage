
package mage.cards.b;

import mage.MageInt;
import mage.abilities.common.BeginningOfEndStepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.LoseLifeSourceControllerEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.keyword.DeathtouchAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.TargetController;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class BloodlineSkystalker extends CardImpl {

    public BloodlineSkystalker(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{B}{B}");
        this.subtype.add(SubType.VAMPIRE);
        this.subtype.add(SubType.CLERIC);

        this.power = new MageInt(3);
        this.toughness = new MageInt(1);

        this.addAbility(FlyingAbility.getInstance());
        this.addAbility(LifelinkAbility.getInstance());

        BeginningOfEndStepTriggeredAbility ability = new BeginningOfEndStepTriggeredAbility(new DrawCardSourceControllerEffect(1), TargetController.SOURCE_CONTROLLER, false);
        ability.addEffect(new LoseLifeSourceControllerEffect(3).concatBy("and"));
        ability.setTriggerPhrase("At the beginning of your end step, ");
        this.addAbility(ability);

        this.addAbility(new SimpleActivatedAbility(new GainAbilitySourceEffect(HasteAbility.getInstance(), Duration.EndOfTurn), new SacrificeTargetCost(StaticFilters.FILTER_PERMANENT_CREATURE)));
    }

    private BloodlineSkystalker(final BloodlineSkystalker card) {
        super(card);
    }

    @Override
    public BloodlineSkystalker copy() {
        return new BloodlineSkystalker(this);
    }
}
