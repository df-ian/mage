
package mage.cards.c;

import mage.abilities.common.LeavesBattlefieldTriggeredAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.mana.ActivatedManaAbilityImpl;
import mage.abilities.mana.AnyColorManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class ChromaticLotus extends CardImpl {

    public ChromaticLotus(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{1}");

        // {1}, {T}, Sacrifice Chromatic Sphere: Add one mana of any color. Draw a card.
        ActivatedManaAbilityImpl ability = new AnyColorManaAbility(new GenericManaCost(1));

        ability.addCost(new ManaCostsImpl<>("{1}"));
        ability.addCost(new TapSourceCost());
        ability.addCost(new SacrificeSourceCost());
        this.addAbility(ability);

        this.addAbility(new LeavesBattlefieldTriggeredAbility(new DrawCardSourceControllerEffect(1), false));
    }

    private ChromaticLotus(final ChromaticLotus card) {
        super(card);
    }

    @Override
    public ChromaticLotus copy() {
        return new ChromaticLotus(this);
    }
}
