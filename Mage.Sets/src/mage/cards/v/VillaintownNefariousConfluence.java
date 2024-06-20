
package mage.cards.v;

import mage.abilities.Ability;
import mage.abilities.common.AsEntersBattlefieldAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.ExileSourceFromGraveCost;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.TapSourceUnlessPaysEffect;
import mage.abilities.effects.common.discard.DiscardControllerEffect;
import mage.abilities.mana.RedManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SuperType;
import mage.constants.Zone;
import mage.game.permanent.token.TreasureToken;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class VillaintownNefariousConfluence extends CardImpl {

    public VillaintownNefariousConfluence(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, null);
        this.supertype.add(SuperType.LEGENDARY);

        this.addAbility(new AsEntersBattlefieldAbility(new TapSourceUnlessPaysEffect(new PayLifeCost(3)), "you may pay 3 life. If you don't, it enters the battlefield tapped"));
        this.addAbility(new RedManaAbility());

        Ability ability = new SimpleActivatedAbility(
                Zone.GRAVEYARD,
                new DiscardControllerEffect(1),
                new ManaCostsImpl<>("{R}")
        );
        ability.addEffect(new DrawCardSourceControllerEffect(1).concatBy(", then"));
        ability.addEffect(new CreateTokenEffect(new TreasureToken(), 1, true).concatBy(". "));
        ability.addCost(new ExileSourceFromGraveCost());

        this.addAbility(ability);
    }

    private VillaintownNefariousConfluence(final VillaintownNefariousConfluence card) {
        super(card);
    }

    @Override
    public VillaintownNefariousConfluence copy() {
        return new VillaintownNefariousConfluence(this);
    }

}
