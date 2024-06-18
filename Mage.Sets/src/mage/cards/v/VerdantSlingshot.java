package mage.cards.v;

import mage.abilities.effects.common.DamageWithPowerFromOneToAnotherTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.target.common.TargetControlledCreaturePermanent;
import mage.target.common.TargetCreatureOrPlaneswalker;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class VerdantSlingshot extends CardImpl {

    public VerdantSlingshot(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{G}");

        // Target creature you control deals damage equal to its power to target creature you don't control.
        this.getSpellAbility().addEffect(new DamageWithPowerFromOneToAnotherTargetEffect());
        this.getSpellAbility().addTarget(new TargetControlledCreaturePermanent());
        this.getSpellAbility().addTarget(new TargetCreatureOrPlaneswalker(1, 1));
    }

    private VerdantSlingshot(final VerdantSlingshot card) {
        super(card);
    }

    @Override
    public VerdantSlingshot copy() {
        return new VerdantSlingshot(this);
    }
}
