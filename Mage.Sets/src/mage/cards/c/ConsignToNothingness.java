package mage.cards.c;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.common.CantBeCounteredSourceAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.continuous.GainAbilityAllEffect;
import mage.abilities.keyword.DeathtouchAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.common.FilterEnchantmentPermanent;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetPermanent;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ConsignToNothingness extends CardImpl {

    public ConsignToNothingness(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{B}{B}");
        
        this.addAbility(new CantBeCounteredSourceAbility());
        
        this.getSpellAbility().addEffect(new ConsignToNothingnessEffect());
        this.getSpellAbility().addTarget(new TargetCreaturePermanent());
    }

    private ConsignToNothingness(final ConsignToNothingness card) {
        super(card);
    }

    @Override
    public ConsignToNothingness copy() {
        return new ConsignToNothingness(this);
    }
}

class ConsignToNothingnessEffect extends OneShotEffect {

    ConsignToNothingnessEffect() {
        super(Outcome.Benefit);
        staticText = "destroy target enchantment an opponent controls. You lose life equal to its mana value";
    }

    private ConsignToNothingnessEffect(final ConsignToNothingnessEffect effect) {
        super(effect);
    }

    @Override
    public ConsignToNothingnessEffect copy() {
        return new ConsignToNothingnessEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        Permanent permanent = game.getPermanent(getTargetPointer().getFirst(game, source));
        if (player == null || permanent == null) {
            return false;
        }
        permanent.destroy(source, game);
        player.loseLife(permanent.getManaValue(), game, source, false);
        return true;
    }
}
