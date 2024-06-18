package mage.cards.a;

import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.LookLibraryAndPickControllerEffect;
import mage.abilities.effects.common.LookLibraryControllerEffect;
import mage.abilities.effects.common.ShuffleLibrarySourceEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.PutCards;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 * @author Ian
 */
public final class AdventurousInstinct extends CardImpl {

    public AdventurousInstinct(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{G}");

        // Look at the top three cards of your library, then put them back in any order.
        this.getSpellAbility().addEffect(new LookLibraryControllerEffect(3));
        // You may shuffle.
        this.getSpellAbility().addEffect(new ShuffleLibrarySourceEffect(true));
        // Draw a card.
        this.getSpellAbility().addEffect(new LookLibraryAndPickControllerEffect(
                3, 1, StaticFilters.FILTER_CARD_CREATURE_OR_LAND, PutCards.HAND, PutCards.BOTTOM_ANY).concatBy("<br>"));
    }

    private AdventurousInstinct(final AdventurousInstinct card) {
        super(card);
    }

    @Override
    public AdventurousInstinct copy() {
        return new AdventurousInstinct(this);
    }
}
