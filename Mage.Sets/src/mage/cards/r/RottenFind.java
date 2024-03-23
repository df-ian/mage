package mage.cards.r;

import mage.abilities.effects.common.search.SearchLibraryPutInGraveyardEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 * @author Ian
 */
public final class RottenFind extends CardImpl {

    public RottenFind(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{B}");

        // Search your library for a card and put that card into your graveyard. Then shuffle your library.
        this.getSpellAbility().addEffect(new SearchLibraryPutInGraveyardEffect(StaticFilters.FILTER_CARD_NON_CREATURE));
    }

    private RottenFind(final RottenFind card) {
        super(card);
    }

    @Override
    public RottenFind copy() {
        return new RottenFind(this);
    }
}
