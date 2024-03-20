
package mage.cards.a;

import mage.abilities.Mode;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.DamagePlayersEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.PutCardFromHandOntoBattlefieldEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.effects.common.continuous.CantGainLifeAllEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.keyword.ReachAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.TargetController;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class AbundantConnection extends CardImpl {

    public AbundantConnection(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{G}");

        this.getSpellAbility().addEffect(new GainLifeEffect(3));

        Mode mode = new Mode(new PutCardFromHandOntoBattlefieldEffect(StaticFilters.FILTER_CARD_LAND_A, false, true));
        this.getSpellAbility().addMode(mode);

        mode = new Mode(new DrawCardSourceControllerEffect(1));
        this.getSpellAbility().addMode(mode);

    }

    private AbundantConnection(final AbundantConnection card) {
        super(card);
    }

    @Override
    public AbundantConnection copy() {
        return new AbundantConnection(this);
    }
}
