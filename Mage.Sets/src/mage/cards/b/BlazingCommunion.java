package mage.cards.b;

import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DamageControllerEffect;
import mage.abilities.effects.common.ExileTopXMayPlayUntilEffect;
import mage.abilities.keyword.SpectacleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;

import java.util.UUID;

/**
 * @author Ian
 */
public final class BlazingCommunion extends CardImpl {

    public BlazingCommunion(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{1}{R}");

        // Exile the top two cards of your library. Until the end of your next turn, you may play those cards.
        this.getSpellAbility().addEffect(new ExileTopXMayPlayUntilEffect(
                3, Duration.UntilEndOfYourNextTurn
        ));

        this.getSpellAbility().addEffect(new DamageControllerEffect(3));
    }

    private BlazingCommunion(final BlazingCommunion card) {
        super(card);
    }

    @Override
    public BlazingCommunion copy() {
        return new BlazingCommunion(this);
    }
}
