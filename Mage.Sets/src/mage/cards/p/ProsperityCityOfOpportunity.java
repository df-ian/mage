
package mage.cards.p;

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
import mage.abilities.mana.BlueManaAbility;
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
public final class ProsperityCityOfOpportunity extends CardImpl {

    public ProsperityCityOfOpportunity(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, null);
        this.supertype.add(SuperType.LEGENDARY);

        this.addAbility(new AsEntersBattlefieldAbility(new TapSourceUnlessPaysEffect(new PayLifeCost(3)), "you may pay 3 life. If you don't, it enters the battlefield tapped"));
        this.addAbility(new BlueManaAbility());

        Ability ability = new SimpleActivatedAbility(
                Zone.GRAVEYARD,
                new DrawCardSourceControllerEffect(2),
                new ManaCostsImpl<>("{3}{U}}")
        );
        ability.addCost(new ExileSourceFromGraveCost());

        this.addAbility(ability);
    }

    private ProsperityCityOfOpportunity(final ProsperityCityOfOpportunity card) {
        super(card);
    }

    @Override
    public ProsperityCityOfOpportunity copy() {
        return new ProsperityCityOfOpportunity(this);
    }

}
