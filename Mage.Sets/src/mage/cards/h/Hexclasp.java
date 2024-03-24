package mage.cards.h;

import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.keyword.HexproofAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.abilities.keyword.SplitSecondAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.target.common.TargetControlledPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class Hexclasp extends CardImpl {

    public Hexclasp(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{G}");

        this.addAbility(new SplitSecondAbility());

        // Target permanent you control gains hexproof and indestructible until end of turn. You gain 2 life.
        this.getSpellAbility().addEffect(new GainAbilityTargetEffect(HexproofAbility.getInstance())
                .setText("target permanent you control gains hexproof"));
        this.getSpellAbility().addEffect(new GainAbilityTargetEffect(IndestructibleAbility.getInstance())
                .setText("and indestructible until end of turn"));
        this.getSpellAbility().addTarget(new TargetControlledPermanent());
    }

    private Hexclasp(final Hexclasp card) {
        super(card);
    }

    @Override
    public Hexclasp copy() {
        return new Hexclasp(this);
    }
}
