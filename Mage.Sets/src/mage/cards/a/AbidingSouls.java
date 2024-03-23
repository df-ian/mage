
package mage.cards.a;

import mage.abilities.Ability;
import mage.abilities.condition.common.RevoltCondition;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.game.permanent.token.SpiritToken;
import mage.players.Player;
import mage.target.common.TargetCreaturePermanent;
import mage.watchers.common.RevoltWatcher;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class AbidingSouls extends CardImpl {

    public AbidingSouls(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{2}{W}");

        // Destroy target creature if it has converted mana cost 2 or less.
        // <i>Revolt</i> &mdash; Destroy that creature if it has converted mana cost 4 or less instead if a permanent you controlled left the battlefield this turn.
        this.getSpellAbility().addEffect(new AbidingSoulsEffect());
        this.getSpellAbility().addWatcher(new RevoltWatcher());
    }

    private AbidingSouls(final AbidingSouls card) {
        super(card);
    }

    @Override
    public AbidingSouls copy() {
        return new AbidingSouls(this);
    }
}

class AbidingSoulsEffect extends OneShotEffect {

    AbidingSoulsEffect() {
        super(Outcome.PutCreatureInPlay);
        this.staticText = "Create two 1/1 white Spirit creature tokens with flying.<br><i>Revolt</i> &mdash; Create four of those tokens instead if a permanent you controlled left the battlefield this turn";
    }

    private AbidingSoulsEffect(final AbidingSoulsEffect effect) {
        super(effect);
    }

    @Override
    public AbidingSoulsEffect copy() {
        return new AbidingSoulsEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            new CreateTokenEffect(new SpiritToken(), RevoltCondition.instance.apply(game, source) ? 4 : 2).apply(game, source);
            return true;
        }
        return false;
    }
}
