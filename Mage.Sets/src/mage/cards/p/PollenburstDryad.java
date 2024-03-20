package mage.cards.p;



import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.StaticFilters;
import mage.target.common.TargetCardInLibrary;

import java.util.UUID;

/**
 * @author Ian
 */
public final class PollenburstDryad extends CardImpl {

    public PollenburstDryad(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{G}");
        this.subtype.add(SubType.DRYAD);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);
        TargetCardInLibrary target = new TargetCardInLibrary(StaticFilters.FILTER_CARD_LAND);
        SimpleActivatedAbility ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new SearchLibraryPutInPlayEffect(target, false, true), new SacrificeSourceCost());
        ability.addCost(new SacrificeTargetCost(StaticFilters.FILTER_LAND_A));
        this.addAbility(ability);
    }

    private PollenburstDryad(final PollenburstDryad card) {
        super(card);
    }

    @Override
    public PollenburstDryad copy() {
        return new PollenburstDryad(this);
    }

}
