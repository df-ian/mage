
package mage.cards.c;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.l.Lifelink;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class ContractedAetherborn extends CardImpl {

    public ContractedAetherborn(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{B}{B}");
        this.subtype.add(SubType.AETHERBORN);
        this.subtype.add(SubType.ASSASSIN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        // Menace
        this.addAbility(FlashAbility.getInstance());
        this.addAbility(LifelinkAbility.getInstance());

        Ability ability = new EntersBattlefieldTriggeredAbility(new ContractedAetherbornEffect());
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability);
    }

    private ContractedAetherborn(final ContractedAetherborn card) {
        super(card);
    }

    @Override
    public ContractedAetherborn copy() {
        return new ContractedAetherborn(this);
    }
}

class ContractedAetherbornEffect extends OneShotEffect {

    ContractedAetherbornEffect() {
        super(Outcome.DestroyPermanent);
        this.staticText = "destroy target creature. You lose life equal to its mana value";
    }

    private ContractedAetherbornEffect(final ContractedAetherbornEffect effect) {
        super(effect);
    }

    @Override
    public ContractedAetherbornEffect copy() {
        return new ContractedAetherbornEffect(this);
    }

    @Override
    public boolean apply(Game game, final Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        Permanent creatureToDestroy = game.getPermanent(getTargetPointer().getFirst(game, source));
        if (creatureToDestroy != null && player != null) {
            creatureToDestroy.destroy(source, game, false);
            player.loseLife(creatureToDestroy.getManaValue(), game, source, false);
        }
        return true;
    }
}
