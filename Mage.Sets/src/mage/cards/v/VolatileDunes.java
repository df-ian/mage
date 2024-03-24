package mage.cards.v;

import mage.abilities.Ability;
import mage.abilities.common.ActivateIfConditionActivatedAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.DrawDiscardControllerEffect;
import mage.abilities.mana.ColorlessManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ComparisonType;
import mage.constants.Zone;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterControlledLandPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class VolatileDunes extends CardImpl {
    public VolatileDunes(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");

        // {T}: Add {C}.
        this.addAbility(new ColorlessManaAbility());

        // {1}, {T}, Sacrifice Cryptic Caves: Draw a card. Activate this ability only if you control five or more lands.
        Ability ability = new SimpleActivatedAbility(
                Zone.BATTLEFIELD, new DrawDiscardControllerEffect(2, 1),
                new TapSourceCost()
        );
        ability.addCost(new SacrificeSourceCost());
        this.addAbility(ability);
    }

    private VolatileDunes(final VolatileDunes card) {
        super(card);
    }

    @Override
    public VolatileDunes copy() {
        return new VolatileDunes(this);
    }
}
