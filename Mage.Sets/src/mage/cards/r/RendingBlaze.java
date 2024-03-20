package mage.cards.r;

import mage.abilities.effects.common.DamageTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SuperType;
import mage.constants.TargetController;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreatureOrPlaneswalkerPermanent;
import mage.target.TargetPermanent;
import mage.target.common.TargetCreatureOrPlaneswalker;

import java.util.UUID;

/**
 * @author Ian
 */
public final class RendingBlaze extends CardImpl {
    private static final FilterPermanent filter2
            = new FilterCreatureOrPlaneswalkerPermanent("creature or planeswalker you don't control");
    static {
        filter2.add(TargetController.NOT_YOU.getControllerPredicate());
    }
    public RendingBlaze(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{R}");

        // Strangle deals 3 damage to target creature or planeswalker.
        this.getSpellAbility().addEffect(new DamageTargetEffect(3));
        this.getSpellAbility().addTarget(new TargetPermanent(filter2));
    }

    private RendingBlaze(final RendingBlaze card) {
        super(card);
    }

    @Override
    public RendingBlaze copy() {
        return new RendingBlaze(this);
    }
}
