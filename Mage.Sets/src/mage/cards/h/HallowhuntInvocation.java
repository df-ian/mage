package mage.cards.h;

import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.Mode;
import mage.abilities.common.delayed.WhenTargetDiesDelayedTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.*;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.effects.common.counter.DistributeCountersEffect;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.CardsImpl;
import mage.constants.*;
import mage.counters.CounterType;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.PowerPredicate;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.Target;
import mage.target.TargetPermanent;
import mage.target.common.TargetCardInHand;
import mage.target.common.TargetControlledCreaturePermanent;
import mage.target.common.TargetCreaturePermanentAmount;
import mage.target.common.TargetPermanentAmount;
import mage.target.targetpointer.FixedTarget;
import mage.util.CardUtil;

import java.util.UUID;

/**
 * @author Ian
 */
public final class HallowhuntInvocation extends CardImpl {


    public HallowhuntInvocation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{0}");

        // Spree
        this.addAbility(new SpreeAbility(this));

        FilterCard filter = new FilterCard("creature or planeswalker");
        filter.add(Predicates.or(CardType.CREATURE.getPredicate(), CardType.PLANESWALKER.getPredicate()));

        // + {3}{R}{R} - Put a creature or planeswalker card from your hand onto the battlefield. It gains haste and sac
        this.getSpellAbility().addEffect(new PutCardIntoPlayWithHasteAndSacrificeEffect(
                filter, Duration.Custom, "That permanent", "that permanent"
        ));
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{3}{R}{R}"));

        Mode mode = new Mode(new DistributeCountersEffect(CounterType.SHIELD, 2, "one or two target permanents you control"));
        mode.addTarget(new TargetPermanentAmount(2, StaticFilters.FILTER_CONTROLLED_PERMANENT));
        // + {1}{G} - Distribute two shield counters among one or two target permanents you control
        this.getSpellAbility().addMode(mode.withCost(new ManaCostsImpl<>("{1}{G}")));


        // + {1}{W} - saffi effect
        mode = new Mode(new CreateDelayedTriggeredAbilityEffect(new HallowhuntInvocationDelayedTriggeredAbility()));
        mode.addTarget(new TargetPermanent());
        this.getSpellAbility().addMode(mode
                .withCost(new ManaCostsImpl<>("{1}{W}")));
    }

    private HallowhuntInvocation(final HallowhuntInvocation card) {
        super(card);
    }

    @Override
    public HallowhuntInvocation copy() {
        return new HallowhuntInvocation(this);
    }
}

class HallowhuntInvocationDelayedTriggeredAbility extends WhenTargetDiesDelayedTriggeredAbility {

    public HallowhuntInvocationDelayedTriggeredAbility() {
        super(new ReturnFromGraveyardToBattlefieldTargetEffect(true).setText("return that card to the battlefield tapped"), SetTargetPointer.CARD);
        setTriggerPhrase("When target permanent is put into your graveyard from the battlefield this turn, ");
    }

    private HallowhuntInvocationDelayedTriggeredAbility(final HallowhuntInvocationDelayedTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public HallowhuntInvocationDelayedTriggeredAbility copy() {
        return new HallowhuntInvocationDelayedTriggeredAbility(this);
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        return this.isControlledBy(game.getOwnerId(event.getTargetId())) && super.checkTrigger(event, game);
    }
}