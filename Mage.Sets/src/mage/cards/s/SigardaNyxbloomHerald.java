package mage.cards.s;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.target.common.TargetControlledCreaturePermanent;
import mage.target.common.TargetControlledPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SigardaNyxbloomHerald extends CardImpl {

    public SigardaNyxbloomHerald(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G}{W}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.ANGEL);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        this.addAbility(FlashAbility.getInstance());
        this.addAbility(FlyingAbility.getInstance());

        // When Angelheart Protector enters the battlefield, target creature you control gains indestructible until end of turn.
        Ability ability = new EntersBattlefieldTriggeredAbility(
                new GainAbilityTargetEffect(IndestructibleAbility.getInstance(), Duration.EndOfTurn)
        );
        ability.addTarget(new TargetControlledPermanent());
        this.addAbility(ability);

        this.addAbility(new SimpleStaticAbility(new GainAbilityControlledEffect(new WardAbility(new ManaCostsImpl<>("{2}")), Duration.WhileOnBattlefield, StaticFilters.FILTER_OTHER_CONTROLLED_CREATURES)));
    }

    private SigardaNyxbloomHerald(final SigardaNyxbloomHerald card) {
        super(card);
    }

    @Override
    public SigardaNyxbloomHerald copy() {
        return new SigardaNyxbloomHerald(this);
    }
}
