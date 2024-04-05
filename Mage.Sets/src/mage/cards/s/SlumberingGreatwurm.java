package mage.cards.s;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldOrAttacksSourceTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.SacrificeOpponentsEffect;
import mage.abilities.effects.common.SacrificeTargetEffect;
import mage.abilities.effects.common.continuous.PlayAdditionalLandsControllerEffect;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.StaticFilters;
import mage.target.common.TargetOpponent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SlumberingGreatwurm extends CardImpl {

    public SlumberingGreatwurm(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{6}{G}{G}");

        this.subtype.add(SubType.DRAGON);
        this.subtype.add(SubType.WURM);
        this.power = new MageInt(6);
        this.toughness = new MageInt(6);

        this.addAbility(TrampleAbility.getInstance());
        this.addAbility(new WardAbility(new ManaCostsImpl<>("{3}")));

        Ability ability = new EntersBattlefieldOrAttacksSourceTriggeredAbility(new SacrificeOpponentsEffect(StaticFilters.FILTER_PERMANENT_ARTIFACT_OR_ENCHANTMENT));
        ability.addEffect(new SacrificeOpponentsEffect(StaticFilters.FILTER_LAND_A).setText("a land").concatBy("and"));
        ability.addTarget(new TargetOpponent());
        ability.addEffect(new DrawCardSourceControllerEffect(1).setText("You draw a card").concatBy("."));
        ability.addEffect(new GainLifeEffect(4).concatBy("and"));
        this.addAbility(ability);
    }

    private SlumberingGreatwurm(final SlumberingGreatwurm card) {
        super(card);
    }

    @Override
    public SlumberingGreatwurm copy() {
        return new SlumberingGreatwurm(this);
    }
}
