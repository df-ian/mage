

package mage.cards.s;

import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.mana.BlackManaAbility;
import mage.abilities.mana.BlueManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SteelscourgeSummit extends CardImpl {

    public SteelscourgeSummit(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT,CardType.LAND},null);
        this.addAbility(new BlackManaAbility());

        Ability a = new SimpleActivatedAbility(new DrawCardSourceControllerEffect(1), new GenericManaCost(4));
        a.addCost(new TapSourceCost());
        a.addCost(new SacrificeSourceCost());
        this.addAbility(a);
    }

    private SteelscourgeSummit(final SteelscourgeSummit card) {
        super(card);
    }

    @Override
    public SteelscourgeSummit copy() {
        return new SteelscourgeSummit(this);
    }

}
