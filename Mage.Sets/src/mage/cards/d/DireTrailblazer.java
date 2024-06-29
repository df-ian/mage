
package mage.cards.d;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.BeginningOfCombatTriggeredAbility;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.keyword.ExploreSourceEffect;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.*;
import mage.constants.*;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class DireTrailblazer extends CardImpl {

    public DireTrailblazer(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{B}");
        this.subtype.add(SubType.ORC);
        this.subtype.add(SubType.SCOUT);

        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        this.addAbility(new MenaceAbility());

        this.addAbility(new BeginningOfCombatTriggeredAbility(new ExploreSourceEffect(), TargetController.YOU, false));

        // At the beginning of your upkeep, reveal the top card of your library and put that card into your hand. You lose life equal to its converted mana cost.
        this.addAbility(new AttacksTriggeredAbility(new DireTrailblazerEffect()));
    }

    private DireTrailblazer(final DireTrailblazer card) {
        super(card);
    }

    @Override
    public DireTrailblazer copy() {
        return new DireTrailblazer(this);
    }
}

class DireTrailblazerEffect extends OneShotEffect {

    DireTrailblazerEffect() {
        super(Outcome.DrawCard);
        this.staticText = "reveal the top card of your library and put that card into your hand. You lose life equal to its mana value";
    }

    private DireTrailblazerEffect(final DireTrailblazerEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Permanent sourcePermanent = game.getPermanentOrLKIBattlefield(source.getSourceId());
        if (controller != null && sourcePermanent != null) {
            if (controller.getLibrary().hasCards()) {
                Card card = controller.getLibrary().getFromTop(game);
                if (card != null) {
                    Cards cards = new CardsImpl(card);
                    controller.revealCards(sourcePermanent.getIdName(), cards, game);
                    controller.moveCards(card, Zone.HAND, source, game);
                    controller.loseLife(card.getManaValue(), game, source, false);

                }
                return true;
            }
        }
        return false;
    }

    @Override
    public DireTrailblazerEffect copy() {
        return new DireTrailblazerEffect(this);
    }
}
