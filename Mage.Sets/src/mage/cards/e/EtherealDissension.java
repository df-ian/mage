package mage.cards.e;

import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.GainLifeTargetEffect;
import mage.abilities.effects.keyword.InvestigateTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.target.common.TargetCreatureOrPlaneswalker;
import mage.target.common.TargetCreaturePermanent;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class EtherealDissension extends CardImpl {

    public EtherealDissension(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{1}{W}");

        // Destroy target creature or planeswalker. Its controller investigates.
        this.getSpellAbility().addEffect(new EtherealDissensionEffect());
        this.getSpellAbility().addTarget(new TargetCreaturePermanent());
    }

    private EtherealDissension(final EtherealDissension card) {
        super(card);
    }

    @Override
    public EtherealDissension copy() {
        return new EtherealDissension(this);
    }
}

class EtherealDissensionEffect extends OneShotEffect {

    EtherealDissensionEffect() {
        super(Outcome.DestroyPermanent);
        staticText = "Destroy target creature. Its controller gains 4 life.";
    }

    private EtherealDissensionEffect(final EtherealDissensionEffect effect) {
        super(effect);
    }

    @Override
    public EtherealDissensionEffect copy() {
        return new EtherealDissensionEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(source.getFirstTarget());
        if (permanent == null) {
            return false;
        }
        UUID controllerId = permanent.getControllerId();
        permanent.destroy(source, game, false);
        new GainLifeTargetEffect(4).setTargetPointer(new FixedTarget(controllerId)).apply(game, source);
        return true;
    }
}
