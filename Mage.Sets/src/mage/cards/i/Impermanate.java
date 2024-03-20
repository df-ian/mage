package mage.cards.i;

import mage.abilities.effects.common.ExileThenReturnTargetEffect;
import mage.abilities.keyword.ReboundAbility;
import mage.abilities.keyword.RetraceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.target.common.TargetControlledCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class Impermanate extends CardImpl {

    public Impermanate(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{1}{W}");

        // Exile target creature you control, then return it to the battlefield under its owner's control.
        this.getSpellAbility().addTarget(new TargetControlledCreaturePermanent());
        this.getSpellAbility().addEffect(new ExileThenReturnTargetEffect(false, false));

        // Rebound
        this.addAbility(new RetraceAbility(this));
    }

    private Impermanate(final Impermanate card) {
        super(card);
    }

    @Override
    public Impermanate copy() {
        return new Impermanate(this);
    }
}
