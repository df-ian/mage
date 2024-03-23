
package mage.cards.s;

import mage.ObjectColor;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.target.common.TargetCardInLibrary;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SpontaneousSuccession extends CardImpl {

    private static final FilterControlledCreaturePermanent filter = new FilterControlledCreaturePermanent("a green creature");
    private static final FilterCreatureCard filterCard = new FilterCreatureCard("creature card");

    static {
        filter.add(new ColorPredicate(ObjectColor.GREEN));
    }

    public SpontaneousSuccession(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{2}{G}{G}");


        // As an additional cost to cast Natural Order, sacrifice a green creature.
        this.getSpellAbility().addCost(new SacrificeTargetCost(filter));
        // Search your library for a green creature card and put it onto the battlefield. Then shuffle your library.
        this.getSpellAbility().addEffect(new SearchLibraryPutInPlayEffect(new TargetCardInLibrary(1 , filterCard), false));
    }

    private SpontaneousSuccession(final SpontaneousSuccession card) {
        super(card);
    }

    @Override
    public SpontaneousSuccession copy() {
        return new SpontaneousSuccession(this);
    }
}
