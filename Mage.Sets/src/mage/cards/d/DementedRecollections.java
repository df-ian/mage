

package mage.cards.d;

import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.DrawCardTargetEffect;
import mage.abilities.effects.common.LoseLifeTargetEffect;
import mage.abilities.keyword.MadnessAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.target.TargetPlayer;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class DementedRecollections extends CardImpl {

    public DementedRecollections(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{2}{B}");


        // Target player draws two cards and loses 2 life.
        this.getSpellAbility().addTarget(new TargetPlayer());
        this.getSpellAbility().addEffect(new DrawCardTargetEffect(2));
        Effect effect = new LoseLifeTargetEffect(2);
        effect.setText("and loses 2 life");
        this.getSpellAbility().addEffect(effect);

        this.addAbility(new MadnessAbility(new ManaCostsImpl<>("{B}")));
    }

    private DementedRecollections(final DementedRecollections card) {
        super(card);
    }

    @Override
    public DementedRecollections copy() {
        return new DementedRecollections(this);
    }
}
