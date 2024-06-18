

package mage.cards.v;

import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.LoseLifeSourceControllerEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class VoidShard extends CardImpl {

    public VoidShard(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{B}");

        this.getSpellAbility().addEffect(new DamageTargetEffect(2));
        this.getSpellAbility().addTarget(new TargetAnyTarget());

        this.getSpellAbility().addEffect(new LoseLifeSourceControllerEffect(2));
    }

    private VoidShard(final VoidShard card) {
        super(card);
    }

    @Override
    public VoidShard copy() {
        return new VoidShard(this);
    }

}
