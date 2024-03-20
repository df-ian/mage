
package mage.cards.p;

import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.counters.CounterType;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCardInHand;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class PillarsOfElysium extends CardImpl {

    public PillarsOfElysium(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{2}");

        this.addSuperType(SuperType.LEGENDARY);

        // At the beginning of your upkeep, you may put a charge counter on Aether Vial.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(new AddCountersSourceEffect(CounterType.CHARGE.createInstance(), true), TargetController.YOU, true));
        // {tap}: You may put a creature card with converted mana cost equal to the number of charge counters on Aether Vial from your hand onto the battlefield.
        this.addAbility(new SimpleActivatedAbility(Zone.BATTLEFIELD, new PillarsOfElysiumEffect(), new TapSourceCost()));
    }

    private PillarsOfElysium(final PillarsOfElysium card) {
        super(card);
    }

    @Override
    public PillarsOfElysium copy() {
        return new PillarsOfElysium(this);
    }
}

class PillarsOfElysiumEffect extends OneShotEffect {

    PillarsOfElysiumEffect() {
        super(Outcome.PutCreatureInPlay);
        this.staticText = "You may put a creature card with mana value equal to the number of charge counters on {this} from your graveyard onto the battlefield";
    }

    private PillarsOfElysiumEffect(final PillarsOfElysiumEffect effect) {
        super(effect);
    }

    @Override
    public PillarsOfElysiumEffect copy() {
        return new PillarsOfElysiumEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getBattlefield().getPermanent(source.getSourceId());
        if (permanent == null) {
            permanent = (Permanent) game.getLastKnownInformation(source.getSourceId(), Zone.BATTLEFIELD);
            if (permanent == null) {
                return false;
            }
        }
        int count = permanent.getCounters(game).getCount(CounterType.CHARGE);

        FilterCreatureCard filter = new FilterCreatureCard("creature card with mana value equal to " + count);
        filter.add(new ManaValuePredicate(ComparisonType.EQUAL_TO, count));
        String choiceText = "Put a " + filter.getMessage() + " from your graveyard onto the battlefield?";

        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null) {
            return false;
        }
        if (controller.getGraveyard().count(filter, game) == 0
                || !controller.chooseUse(this.outcome, choiceText, source, game)) {
            return true;
        }

        TargetCardInGraveyard target = new TargetCardInGraveyard(filter);
        if (controller.choose(this.outcome, target, source, game)) {
            Card card = game.getCard(target.getFirstTarget());
            if (card != null) {
                return controller.moveCards(card, Zone.BATTLEFIELD, source, game);
            }
        }
        return false;
    }
}
