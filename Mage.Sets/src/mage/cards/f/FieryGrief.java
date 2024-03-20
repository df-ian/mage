
package mage.cards.f;

import mage.abilities.Ability;
import mage.abilities.condition.common.RevoltCondition;
import mage.abilities.effects.OneShotEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCreaturePermanent;
import mage.watchers.common.RevoltWatcher;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class FieryGrief extends CardImpl {

    public FieryGrief(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{R}");

        this.getSpellAbility().addEffect(new FieryGriefEffect());
        this.getSpellAbility().addWatcher(new RevoltWatcher());
        this.getSpellAbility().addTarget(new TargetAnyTarget());
    }

    private FieryGrief(final FieryGrief card) {
        super(card);
    }

    @Override
    public FieryGrief copy() {
        return new FieryGrief(this);
    }
}

class FieryGriefEffect extends OneShotEffect {

    FieryGriefEffect() {
        super(Outcome.Damage);
        this.staticText = "{this} deals 2 damage to any target.<br><i>Revolt</i> &mdash; {this} deals 3 damage to any target instead if a permanent you controlled left the battlefield this turn";
    }

    private FieryGriefEffect(final FieryGriefEffect effect) {
        super(effect);
    }

    @Override
    public FieryGriefEffect copy() {
        return new FieryGriefEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            Permanent targetCreature = game.getPermanent(this.getTargetPointer().getFirst(game, source));
            game.damagePlayerOrPermanent(this.getTargetPointer().getFirst(game, source), RevoltCondition.instance.apply(game, source) ? 3 : 2, source.getSourceId(), source, game, false, true);
            return true;
        }
        return false;
    }
}
