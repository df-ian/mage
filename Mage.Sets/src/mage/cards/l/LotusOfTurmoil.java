
package mage.cards.l;

import mage.abilities.Ability;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.mana.AddManaOfAnyColorEffect;
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
public final class LotusOfTurmoil extends CardImpl {

    public LotusOfTurmoil(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{5}");

        // {tap}, Sacrifice Black Lotus: Add three mana of any one color.
        Ability ability = new SimpleManaAbility(Zone.BATTLEFIELD, new AddManaOfAnyColorEffect(3), new TapSourceCost());
        ability.addCost(new SacrificeSourceCost());
        this.addAbility(ability);

        this.addAbility(new MadnessAbility(new ManaCostsImpl<>("{1}")));
    }

    private LotusOfTurmoil(final LotusOfTurmoil card) {
        super(card);
    }

    @Override
    public LotusOfTurmoil copy() {
        return new LotusOfTurmoil(this);
    }
}
