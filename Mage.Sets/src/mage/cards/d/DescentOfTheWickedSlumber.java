
package mage.cards.d;

import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.discard.DiscardEachPlayerEffect;
import mage.abilities.keyword.DelveAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.TargetController;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class DescentOfTheWickedSlumber extends CardImpl {

    public DescentOfTheWickedSlumber(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{7}{U}{B}");


        // Delve
        this.addAbility(new DelveAbility());
        // Draw 3 Cards
        this.getSpellAbility().addEffect(new DrawCardSourceControllerEffect(3));
        this.getSpellAbility().addEffect(new DiscardEachPlayerEffect(
                StaticValue.get(1), false, TargetController.OPPONENT
        ).concatBy("and"));
    }

    private DescentOfTheWickedSlumber(final DescentOfTheWickedSlumber card) {
        super(card);
    }

    @Override
    public DescentOfTheWickedSlumber copy() {
        return new DescentOfTheWickedSlumber(this);
    }
}
