
package mage.cards.c;

import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.game.Game;
import mage.game.permanent.Permanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class CrimsonFabrications extends CardImpl {

    public CrimsonFabrications(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{X}{R}");


        // Destroy each artifact with converted mana cost X or less.
        this.getSpellAbility().addEffect(new CrimsonFabricationsEffect());
    }

    private CrimsonFabrications(final CrimsonFabrications card) {
        super(card);
    }

    @Override
    public CrimsonFabrications copy() {
        return new CrimsonFabrications(this);
    }
}

class CrimsonFabricationsEffect extends OneShotEffect {
    
    CrimsonFabricationsEffect() {
        super(Outcome.DestroyPermanent);
        this.staticText = "Destroy each artifact with mana value greater than 0 and less than or equal to X";
    }
    
    private CrimsonFabricationsEffect(final CrimsonFabricationsEffect effect) {
        super(effect);
    }
    
    @Override
    public CrimsonFabricationsEffect copy() {
        return new CrimsonFabricationsEffect(this);
    }
    
    @Override
    public boolean apply(Game game, Ability source) {
        for (Permanent permanent : game.getBattlefield().getActivePermanents(source.getControllerId(), game)) {
            if (permanent != null && permanent.isArtifact(game) && permanent.getManaValue() > 0 && permanent.getManaValue() <= source.getManaCostsToPay().getX()) {
                permanent.destroy(source, game, false);
            }
        }
        return true;
    }
}
