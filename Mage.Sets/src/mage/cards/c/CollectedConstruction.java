package mage.cards.c;

import mage.abilities.effects.common.LookLibraryAndPickControllerEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ComparisonType;
import mage.constants.PutCards;
import mage.filter.FilterCard;
import mage.filter.common.FilterArtifactCard;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.mageobject.ManaValuePredicate;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class CollectedConstruction extends CardImpl {

    private static final FilterCard filter = new FilterArtifactCard("artifact cards with mana value 3 or less");

    static {
        filter.add(new ManaValuePredicate(ComparisonType.FEWER_THAN, 4));
    }

    public CollectedConstruction(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{3}{R}");

        // Look at the top six cards of your library. Put up to two creature cards with mana value 3 or less from among them onto the battlefield.
        // Put the rest on the bottom of your library in any order.
        this.getSpellAbility().addEffect(new LookLibraryAndPickControllerEffect(6, 2, filter, PutCards.BATTLEFIELD, PutCards.BOTTOM_ANY, false));
    }

    private CollectedConstruction(final CollectedConstruction card) {
        super(card);
    }

    @Override
    public CollectedConstruction copy() {
        return new CollectedConstruction(this);
    }
}
