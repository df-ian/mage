

package mage.cards.t;

import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.keyword.MadnessAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class TibaltsWager extends CardImpl {

    public TibaltsWager(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{R}");

        this.getSpellAbility().addEffect(new DamageTargetEffect(2));
        this.getSpellAbility().addTarget(new TargetAnyTarget());

        this.addAbility(new MadnessAbility(new ManaCostsImpl<>("{0}")));
    }

    private TibaltsWager(final TibaltsWager card) {
        super(card);
    }

    @Override
    public TibaltsWager copy() {
        return new TibaltsWager(this);
    }

}
