
package mage.cards.m;

import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CounterUnlessPaysEffect;
import mage.abilities.keyword.CyclingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.target.TargetSpell;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class Mismatch extends CardImpl {

    public Mismatch(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{1}{U}");


        // Counter target spell unless its controller pays {2}.
        this.getSpellAbility().addTarget(new TargetSpell());
        this.getSpellAbility().addEffect(new CounterUnlessPaysEffect(new GenericManaCost(2)));
        // Cycling {2}
        this.addAbility(new CyclingAbility(new ManaCostsImpl<>("{1}")));
    }

    private Mismatch(final Mismatch card) {
        super(card);
    }

    @Override
    public Mismatch copy() {
        return new Mismatch(this);
    }
}
