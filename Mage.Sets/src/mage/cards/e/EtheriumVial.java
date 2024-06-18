
package mage.cards.e;

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
import mage.filter.common.FilterArtifactCard;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCardInHand;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class EtheriumVial extends CardImpl {

    public EtheriumVial(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{1}");

        // At the beginning of your upkeep, you may put a charge counter on Etherium Vial.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(new AddCountersSourceEffect(CounterType.CHARGE.createInstance(), true), TargetController.YOU, true));
        // {tap}: You may put a creature card with converted mana cost equal to the number of charge counters on Etherium Vial from your hand onto the battlefield.
        this.addAbility(new SimpleActivatedAbility(Zone.BATTLEFIELD, new EtheriumVialEffect(), new TapSourceCost()));
    }

    private EtheriumVial(final EtheriumVial card) {
        super(card);
    }

    @Override
    public EtheriumVial copy() {
        return new EtheriumVial(this);
    }
}

class EtheriumVialEffect extends OneShotEffect {

    EtheriumVialEffect() {
        super(Outcome.PutCardInPlay);
        this.staticText = "You may put an artifact card with mana value equal to the number of charge counters on {this} from your hand onto the battlefield";
    }

    private EtheriumVialEffect(final EtheriumVialEffect effect) {
        super(effect);
    }

    @Override
    public EtheriumVialEffect copy() {
        return new EtheriumVialEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(source.getSourceId());
        if (permanent == null) {
            permanent = (Permanent) game.getLastKnownInformation(source.getSourceId(), Zone.BATTLEFIELD);
            if (permanent == null) {
                return false;
            }
        }
        int count = permanent.getCounters(game).getCount(CounterType.CHARGE);

        FilterArtifactCard filter = new FilterArtifactCard("artifact card with mana value equal to " + count);
        filter.add(new ManaValuePredicate(ComparisonType.EQUAL_TO, count));
        String choiceText = "Put an " + filter.getMessage() + " from your hand onto the battlefield?";

        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null) {
            return false;
        }
        if (controller.getHand().count(filter, game) == 0
                || !controller.chooseUse(this.outcome, choiceText, source, game)) {
            return true;
        }

        TargetCardInHand target = new TargetCardInHand(filter);
        if (controller.choose(this.outcome, target, source, game)) {
            Card card = game.getCard(target.getFirstTarget());
            if (card != null) {
                return controller.moveCards(card, Zone.BATTLEFIELD, source, game);
            }
        }
        return false;
    }
}
