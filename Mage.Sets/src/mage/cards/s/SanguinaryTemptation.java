
package mage.cards.s;

import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.LoseLifeSourceControllerEffect;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.common.FilterCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SanguinaryTemptation extends CardImpl {

    public SanguinaryTemptation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{B}");


        this.getSpellAbility().addEffect(new DrawCardSourceControllerEffect(1).setText("you draw a card"));
        this.getSpellAbility().addEffect(new LoseLifeSourceControllerEffect(1).concatBy("and"));

        this.addAbility(new FlashbackAbility(this, new SacrificeTargetCost(1, new FilterCreaturePermanent())));
    }

    private SanguinaryTemptation(final SanguinaryTemptation card) {
        super(card);
    }

    @Override
    public SanguinaryTemptation copy() {
        return new SanguinaryTemptation(this);
    }
}
