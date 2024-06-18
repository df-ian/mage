
package mage.cards.l;

import mage.abilities.Ability;
import mage.abilities.common.AsEntersBattlefieldAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.ExileFromGraveCost;
import mage.abilities.costs.common.ExileSourceFromGraveCost;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.TapSourceUnlessPaysEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.abilities.mana.BlackManaAbility;
import mage.abilities.mana.RedManaAbility;
import mage.abilities.mana.WhiteManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.constants.Zone;
import mage.game.permanent.token.RedElementalToken;
import mage.target.common.TargetCardInLibrary;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class LochthwainCompleat extends CardImpl {

    public LochthwainCompleat(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, null);
        this.supertype.add(SuperType.LEGENDARY);

        this.addAbility(new AsEntersBattlefieldAbility(new TapSourceUnlessPaysEffect(new PayLifeCost(3)), "you may pay 3 life. If you don't, it enters the battlefield tapped"));
        this.addAbility(new BlackManaAbility());

        Ability ability = new SimpleActivatedAbility(
                Zone.GRAVEYARD,
                new SearchLibraryPutInHandEffect(new TargetCardInLibrary(), false),
                new ManaCostsImpl<>("{1}{B}")
        );
        ability.addCost(new ExileSourceFromGraveCost());

        this.addAbility(ability);
    }

    private LochthwainCompleat(final LochthwainCompleat card) {
        super(card);
    }

    @Override
    public LochthwainCompleat copy() {
        return new LochthwainCompleat(this);
    }

}
