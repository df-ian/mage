package mage.cards.o;

import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.LookLibraryAndPickControllerEffect;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.PutCards;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class OtherworldlyAffinity extends CardImpl {

    public OtherworldlyAffinity(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{U}{B}");

        // Look at the top four cards of your library. Put one of them into your hand and the rest on the bottom of your library in any order.
        this.getSpellAbility().addEffect(new LookLibraryAndPickControllerEffect(6, 1, PutCards.HAND, PutCards.GRAVEYARD));

        this.addAbility(new FlashbackAbility(this, new ManaCostsImpl<>("{4}{U}{B}")));
    }

    private OtherworldlyAffinity(final OtherworldlyAffinity card) {
        super(card);
    }

    @Override
    public OtherworldlyAffinity copy() {
        return new OtherworldlyAffinity(this);
    }
}
