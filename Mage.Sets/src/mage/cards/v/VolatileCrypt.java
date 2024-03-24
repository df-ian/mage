
package mage.cards.v;

import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.ExileGraveyardAllTargetPlayerEffect;
import mage.abilities.keyword.UnearthAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.target.TargetPlayer;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class VolatileCrypt extends CardImpl {

    public VolatileCrypt(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{0}");

        // {tap}, Sacrifice Tormod's Crypt: Exile all cards from target player's graveyard.
        SimpleActivatedAbility ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new ExileGraveyardAllTargetPlayerEffect(), new TapSourceCost());
        ability.addCost(new SacrificeSourceCost());
        ability.addTarget(new TargetPlayer());
        this.addAbility(ability);

        this.addAbility(new UnearthAbility(new ManaCostsImpl<>("{2}")));
    }

    private VolatileCrypt(final VolatileCrypt card) {
        super(card);
    }

    @Override
    public VolatileCrypt copy() {
        return new VolatileCrypt(this);
    }
}
