package mage.cards.a;

import mage.abilities.effects.common.LookLibraryAndPickControllerEffect;
import mage.abilities.keyword.AffinityForArtifactsAbility;
import mage.abilities.keyword.DelveAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.PutCards;

import java.util.UUID;

/**
 * @author Ian
 */
public final class AeonRitual extends CardImpl {

    public AeonRitual(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{5}{U}{U}");

        // Delve
        this.addAbility(new AffinityForArtifactsAbility());

        // Look at the top seven cards of your library. Put two of them into your hand and the rest on the bottom of your library in any order.
        this.getSpellAbility().addEffect(new LookLibraryAndPickControllerEffect(7, 2, PutCards.HAND, PutCards.BOTTOM_ANY));
    }

    private AeonRitual(final AeonRitual card) {
        super(card);
    }

    @Override
    public AeonRitual copy() {
        return new AeonRitual(this);
    }
}
