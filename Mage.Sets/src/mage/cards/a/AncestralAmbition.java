
package mage.cards.a;

import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DrawDiscardControllerEffect;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class AncestralAmbition extends CardImpl {

    public AncestralAmbition(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{3}{U}");


        // Draw two cards, then discard two cards.
        this.getSpellAbility().addEffect(new DrawDiscardControllerEffect(3,3));

        this.addAbility(new FlashbackAbility(this, new ManaCostsImpl<>("{U}")));
    }

    private AncestralAmbition(final AncestralAmbition card) {
        super(card);
    }

    @Override
    public AncestralAmbition copy() {
        return new AncestralAmbition(this);
    }
}
