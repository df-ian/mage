
package mage.cards.d;

import mage.abilities.common.EntersBattlefieldTappedAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.filter.common.FilterLandCard;
import mage.target.common.TargetCardInLibrary;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class DivinersTerrarium extends CardImpl {

    public DivinersTerrarium(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{1}");

        this.addAbility(new EntersBattlefieldTappedAbility());

        // {2}, {tap}, Sacrifice Expedition Map: Search your library for a land card, reveal it, and put it into your hand. Then shuffle your library.
        TargetCardInLibrary target = new TargetCardInLibrary(new FilterLandCard());
        SimpleActivatedAbility ability = new SimpleActivatedAbility(Zone.BATTLEFIELD,
                new SearchLibraryPutInHandEffect(target, true),
                new GenericManaCost(1));
        ability.addCost(new TapSourceCost());
        ability.addCost(new SacrificeSourceCost());

        this.addAbility(ability);
    }

    private DivinersTerrarium(final DivinersTerrarium card) {
        super(card);
    }

    @Override
    public DivinersTerrarium copy() {
        return new DivinersTerrarium(this);
    }
}
