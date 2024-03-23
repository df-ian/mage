package mage.game.command.emblems;

import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.continuous.GainAbilityAllEffect;
import mage.abilities.keyword.HexproofAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Zone;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.Predicates;
import mage.game.command.Emblem;

/**
 * @author spjspj
 */
public final class AjaniAvengerOfThePrideEmblem extends Emblem {

    public AjaniAvengerOfThePrideEmblem() {
        super("Emblem Elspeth");
        FilterControlledPermanent filter = new FilterControlledPermanent("Permanents you control");
        Effect effect = new GainAbilityAllEffect(IndestructibleAbility.getInstance(), Duration.WhileOnBattlefield, filter, false);
        effect.setText("Permanents you control have indestructible");
        SimpleStaticAbility a = new SimpleStaticAbility(Zone.COMMAND, effect);
        a.addEffect(new GainAbilityAllEffect(HexproofAbility.getInstance(), Duration.WhileOnBattlefield, filter, false).setText("hexproof").concatBy("and"));
        this.getAbilities().add(a);
    }

    private AjaniAvengerOfThePrideEmblem(final AjaniAvengerOfThePrideEmblem card) {
        super(card);
    }

    @Override
    public AjaniAvengerOfThePrideEmblem copy() {
        return new AjaniAvengerOfThePrideEmblem(this);
    }
}
