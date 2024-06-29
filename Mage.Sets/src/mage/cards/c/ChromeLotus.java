
package mage.cards.c;

import mage.abilities.Ability;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.mana.AddManaOfAnyColorEffect;
import mage.abilities.keyword.AffinityForArtifactsAbility;
import mage.abilities.keyword.MadnessAbility;
import mage.abilities.mana.SimpleManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class ChromeLotus extends CardImpl {

    public ChromeLotus(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{7}");

        this.addAbility(new AffinityForArtifactsAbility());

        // {tap}, Sacrifice Black Lotus: Add three mana of any one color.
        Ability ability = new SimpleManaAbility(Zone.BATTLEFIELD, new AddManaOfAnyColorEffect(3), new TapSourceCost());
        ability.addCost(new SacrificeSourceCost());
        this.addAbility(ability);
    }

    private ChromeLotus(final ChromeLotus card) {
        super(card);
    }

    @Override
    public ChromeLotus copy() {
        return new ChromeLotus(this);
    }
}
