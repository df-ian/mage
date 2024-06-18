package mage.cards.r;

import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayTargetControllerEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.filter.StaticFilters;
import mage.target.common.TargetCardInLibrary;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class RoadToExile extends CardImpl {

    public RoadToExile(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{W}");

        // Exile target creature. Its controller may search their library for a basic land card,
        // put that card onto the battlefield tapped, then shuffle their library.
        this.getSpellAbility().addTarget(new TargetCreaturePermanent());
        this.getSpellAbility().addEffect(new ExileTargetEffect());
        this.getSpellAbility().addEffect(new SearchLibraryPutInPlayTargetControllerEffect(new TargetCardInLibrary(StaticFilters.FILTER_CARD_LAND), true, Outcome.PutLandInPlay, "its controller").setText(
                "its controller may search their library for a land card, put that card onto the battlefield tapped, then shuffle"
        ));
    }

    private RoadToExile(final RoadToExile card) {
        super(card);
    }

    @Override
    public RoadToExile copy() {
        return new RoadToExile(this);
    }
}
