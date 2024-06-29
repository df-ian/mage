
package mage.cards.v;

import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.LoseLifeOpponentsEffect;
import mage.abilities.effects.common.SacrificeEffect;
import mage.abilities.keyword.DelveAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.target.TargetPlayer;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class VitalityCleave extends CardImpl {

    public VitalityCleave(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{4}{B}");

        // Delve
        this.addAbility(new DelveAbility());
        // Target player sacrifices two creatures
        this.getSpellAbility().addEffect(new LoseLifeOpponentsEffect(4));
        this.getSpellAbility().addEffect(new GainLifeEffect(4).concatBy(" and "));
    }

    private VitalityCleave(final VitalityCleave card) {
        super(card);
    }

    @Override
    public VitalityCleave copy() {
        return new VitalityCleave(this);
    }
}
