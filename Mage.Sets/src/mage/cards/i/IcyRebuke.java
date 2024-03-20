package mage.cards.i;

import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.ReturnToHandTargetEffect;
import mage.abilities.effects.keyword.SurveilEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.target.common.TargetNonlandPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class IcyRebuke extends CardImpl {

    public IcyRebuke(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{2}{U}");

        // Return target nonland permanent to its owner's hand. Surveil 1.
        this.getSpellAbility().addEffect(new ReturnToHandTargetEffect());
        this.getSpellAbility().addTarget(new TargetNonlandPermanent());
        this.getSpellAbility().addEffect(new SurveilEffect(1));
        this.getSpellAbility().addEffect(new DrawCardSourceControllerEffect(1));
    }

    private IcyRebuke(final IcyRebuke card) {
        super(card);
    }

    @Override
    public IcyRebuke copy() {
        return new IcyRebuke(this);
    }
}
