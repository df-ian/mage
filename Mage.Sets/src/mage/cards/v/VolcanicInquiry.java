
package mage.cards.v;

import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DrawDiscardControllerEffect;
import mage.abilities.keyword.DelveAbility;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class VolcanicInquiry extends CardImpl {

    public VolcanicInquiry(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{5}{R}");


        this.addAbility(new DelveAbility());
        this.getSpellAbility().addEffect(new DrawDiscardControllerEffect(4,2));

    }

    private VolcanicInquiry(final VolcanicInquiry card) {
        super(card);
    }

    @Override
    public VolcanicInquiry copy() {
        return new VolcanicInquiry(this);
    }
}
