package mage.cards.b;

import mage.abilities.Ability;
import mage.abilities.MageSingleton;
import mage.abilities.Mode;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.DrawCardTargetEffect;
import mage.abilities.effects.common.ReturnToHandTargetEffect;
import mage.abilities.effects.common.discard.DiscardEachPlayerEffect;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterArtifactPermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.target.TargetPlayer;
import mage.target.TargetSpell;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetNonlandPermanent;
import mage.watchers.Watcher;

import java.io.ObjectStreamException;
import java.util.UUID;

/**
 * @author Ian
 */
public final class BloodknightsInvocation extends CardImpl {

    public BloodknightsInvocation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{0}");

        // Spree
        this.addAbility(new SpreeAbility(this));


        this.getSpellAbility().addWatcher(new TotalAmountOfDamageWatcher());

        // + {R} - 2 damage to any target
        this.getSpellAbility().addEffect(new DamageTargetEffect(2));
        this.getSpellAbility().addTarget(new TargetAnyTarget());
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{R}"));

        // + {1}{W}{W} -- Destroy each artifact, planeswalker, and enchantment with mana value X or less
        this.getSpellAbility().addMode(new Mode(new BloodknightsInvocationDestroyEffect()).withCost(new ManaCostsImpl<>("{U}")));

        // + {1}{B}{B} -- Each opponent discards X cards at random
        this.getSpellAbility().addMode(new Mode(new DiscardEachPlayerEffect(TotalAmountOfDamageDealtValue.instance, true, TargetController.NOT_YOU))
                .withCost(new ManaCostsImpl<>("{1}{B}{B}")));
    }

    private BloodknightsInvocation(final BloodknightsInvocation card) {
        super(card);
    }

    @Override
    public BloodknightsInvocation copy() {
        return new BloodknightsInvocation(this);
    }
}


enum TotalAmountOfDamageDealtValue implements DynamicValue, MageSingleton {

    instance;

    private Object readResolve() throws ObjectStreamException {
        return instance;
    }


    @Override
    public int calculate(Game game, Ability sourceAbility, Effect effect) {
        return this.calculate(game, sourceAbility.getControllerId());
    }

    public int calculate(Game game, UUID controllerId) {
        TotalAmountOfDamageWatcher watcher = game.getState().getWatcher(TotalAmountOfDamageWatcher.class);
        if (watcher != null) {
            return watcher.getTotalAmountOfDamage();
        }
        return 0;
    }

    @Override
    public DynamicValue copy() {
        return this;
    }

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public String getMessage() {
        return "the amount of damage dealt by sources this turn";
    }
}

class TotalAmountOfDamageWatcher extends Watcher {

    private int damageAmount = 0;

    public TotalAmountOfDamageWatcher() {
        super(WatcherScope.GAME);
    }

    @Override
    public void watch(GameEvent event, Game game) {
        switch(event.getType()) {
            case DAMAGED_PERMANENT:
            case DAMAGED_PLAYER:
                if (event.getAmount() > damageAmount) {
                    damageAmount += event.getAmount();
                }
        }
    }

    /**
     *
     * @return Returns the greatest amount of damage dealt to a player or permanent during the current turn.
     */
    public int getTotalAmountOfDamage() {
        return damageAmount;
    }

    @Override
    public void reset() {
        super.reset();
        damageAmount = 0;
    }
}

class BloodknightsInvocationDestroyEffect extends OneShotEffect {

    BloodknightsInvocationDestroyEffect() {
        super(Outcome.DestroyPermanent);
        staticText = "destroy each artifact, enchantment, and planeswalker with mana value X or less, where X is the total amount of damage sources dealt this turn";
    }

    private BloodknightsInvocationDestroyEffect(final BloodknightsInvocationDestroyEffect effect) {
        super(effect);
    }

    public BloodknightsInvocationDestroyEffect copy() {
        return new BloodknightsInvocationDestroyEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        FilterPermanent filter = new FilterPermanent();
        filter.add(Predicates.or(CardType.ARTIFACT.getPredicate(), CardType.ENCHANTMENT.getPredicate(), CardType.PLANESWALKER.getPredicate()));
        filter.add(new ManaValuePredicate(ComparisonType.OR_LESS, TotalAmountOfDamageDealtValue.instance.calculate(game, source.getControllerId())));

        boolean destroyed = false;
        for (Permanent permanent : game.getBattlefield().getActivePermanents(filter, source.getControllerId(), game)) {
            destroyed |= permanent.destroy(source, game);
        }
        return destroyed;
    }
}