package mage.cards.a;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldControlledTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.MetalcraftCondition;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.hint.common.MetalcraftHint;
import mage.abilities.keyword.EquipAbility;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterControlledPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class AzrilThreefoldStorm extends CardImpl {

    private static final FilterPermanent filter
            = new FilterControlledPermanent(SubType.EQUIPMENT, "an Equipment");

    public AzrilThreefoldStorm(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.FOX);
        this.subtype.add(SubType.WARRIOR);

        this.power = new MageInt(0);
        this.toughness = new MageInt(1);

        this.addAbility(FirstStrikeAbility.getInstance());

        // Whenever an Equipment enters the battlefield under your control, you may draw a card.
        this.addAbility(new EntersBattlefieldControlledTriggeredAbility(
                Zone.BATTLEFIELD, new DrawCardSourceControllerEffect(1), filter, true
        ));

        // <i>Metalcraft</i> &mdash; Equipment you control have equip {0} as long as you control three or more artifacts
        this.addAbility(new SimpleStaticAbility(new ConditionalContinuousEffect(
                new BoostSourceEffect(3, 0, Duration.WhileOnBattlefield), MetalcraftCondition.instance,
                "As long as you control three or more artifacts, {this} gets +3/+0"
        )).setAbilityWord(AbilityWord.METALCRAFT).addHint(MetalcraftHint.instance));
    }

    private AzrilThreefoldStorm(final AzrilThreefoldStorm card) {
        super(card);
    }

    @Override
    public AzrilThreefoldStorm copy() {
        return new AzrilThreefoldStorm(this);
    }
}
