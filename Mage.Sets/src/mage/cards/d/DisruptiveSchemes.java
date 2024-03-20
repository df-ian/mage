package mage.cards.d;

import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.keyword.SurveilEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.target.TargetSpell;

import java.util.UUID;

/**
 * @author Ian
 */
public final class DisruptiveSchemes extends CardImpl {

    public DisruptiveSchemes(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{U}{U}{B}");

        // Counter target spell. You gain 3 life.
        this.getSpellAbility().addEffect(new CounterTargetEffect());
        this.getSpellAbility().addEffect(new SurveilEffect(3));
        this.getSpellAbility().addTarget(new TargetSpell());
    }

    private DisruptiveSchemes(final DisruptiveSchemes card) {
        super(card);
    }

    @Override
    public DisruptiveSchemes copy() {
        return new DisruptiveSchemes(this);
    }
}
