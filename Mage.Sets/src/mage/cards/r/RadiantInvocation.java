package mage.cards.r;

import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.Mode;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.effects.common.CreateDelayedTriggeredAbilityEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.ExileReturnBattlefieldNextEndStepTargetEffect;
import mage.abilities.effects.common.counter.ProliferateEffect;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.*;
import mage.choices.Choice;
import mage.choices.ChoiceImpl;
import mage.constants.*;
import mage.counters.CounterType;
import mage.filter.FilterCard;
import mage.filter.common.FilterPermanentCard;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.events.NumberOfTriggersEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetCard;
import mage.target.TargetPermanent;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCardInLibrary;
import mage.target.common.TargetCreaturePermanent;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

/**
 * @author Ian
 */
public final class RadiantInvocation extends CardImpl {


    public RadiantInvocation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{0}");

        // Spree
        this.addAbility(new SpreeAbility(this));


        // + {U} - single turn yarock
        this.getSpellAbility().addEffect(new RadiantInvocationHardenedEffect());
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{1}{G}"));

        Mode mode = new Mode(new RadiantInvocationCounterEffect());
        mode.addTarget(new TargetPermanent());
        this.getSpellAbility().addMode(mode.withCost(new ManaCostsImpl<>("{W}")));


        // + {U}{U} - proliferate twice
        mode = new Mode(new ProliferateEffect().setText("Proliferate twice"));
        mode.addEffect(new ProliferateEffect().setText(""));
        this.getSpellAbility().addMode(mode
                .withCost(new ManaCostsImpl<>("{U}{U}")));
    }

    private RadiantInvocation(final RadiantInvocation card) {
        super(card);
    }

    @Override
    public RadiantInvocation copy() {
        return new RadiantInvocation(this);
    }
}

class RadiantInvocationHardenedEffect extends ReplacementEffectImpl {
    public RadiantInvocationHardenedEffect() {
        super(Duration.EndOfTurn, Outcome.Benefit);
        staticText = "If one or more counters would be put on a permanent this turn, put that many plus two on it instead";
    }

    private RadiantInvocationHardenedEffect(final RadiantInvocationHardenedEffect effect) {
        super(effect);
    }

    public RadiantInvocationHardenedEffect copy() {
        return new RadiantInvocationHardenedEffect(this);
    }

    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ADD_COUNTERS;
    }

    public boolean applies(GameEvent event, Ability source, Game game) {
        return true;
    }

    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        event.setAmountForCounters(event.getAmount() + 2, true);
        return false;
    }
}

class RadiantInvocationCounterEffect extends OneShotEffect {

    private static final Set<String> choices = new LinkedHashSet<>();

    static {
        choices.add("+1/+1");
        choices.add("Loyalty");
        choices.add("Charge");
        choices.add("Lore");
        choices.add("Stun");
        choices.add("Finality");
    }

    RadiantInvocationCounterEffect() {
        super(Outcome.Benefit);
        staticText = "choose up to one target permanent. Put a counter " +
                "from among +1/+1, loyalty, charge, lore, stun, and finality on it";
    }

    private RadiantInvocationCounterEffect(final RadiantInvocationCounterEffect effect) {
        super(effect);
    }

    @Override
    public RadiantInvocationCounterEffect copy() {
        return new RadiantInvocationCounterEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        Permanent permanent = game.getPermanent(getTargetPointer().getFirst(game, source));
        if (player == null || permanent == null) {
            return false;
        }
//        permanent.addCounters(CounterType.P1P1.createInstance(), source, game);
        Choice choice = new ChoiceImpl(true);
        choice.setMessage("Choose an ability counter to add");
        choice.setChoices(choices);
        player.choose(outcome, choice, game);
        CounterType counterType = CounterType.findByName(choice.getChoice().toLowerCase(Locale.ROOT));
        permanent.addCounters(counterType.createInstance(), source, game);
        return true;
    }
}