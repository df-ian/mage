package mage.cards.v;

import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.Mode;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.*;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.CardsImpl;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.FilterSpell;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.card.CastFromZonePredicate;
import mage.filter.predicate.mageobject.PowerPredicate;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.Target;
import mage.target.TargetPlayer;
import mage.target.TargetSpell;
import mage.target.common.TargetCardInHand;
import mage.target.targetpointer.FixedTarget;
import mage.util.CardUtil;

import java.util.UUID;

/**
 * @author Ian
 */
public final class VoidcallersInvocation extends CardImpl {

    private static final FilterSpell filter = new FilterSpell("spell that wasn't cast from its owner's hand");

    static {
        filter.add(Predicates.not(new CastFromZonePredicate(Zone.HAND)));
    }


    public VoidcallersInvocation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{0}");

        // Spree
        this.addAbility(new SpreeAbility(this));


        this.getSpellAbility().addEffect(new ExileTargetEffect());
        this.getSpellAbility().addTarget(new TargetSpell(filter));
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{U}"));

        Mode mode = new Mode(new ExileGraveyardAllTargetPlayerEffect());
        mode.addTarget(new TargetPlayer());
        mode.withCost(new ManaCostsImpl<>("{B}"));
        this.getSpellAbility().addMode(mode);

        mode = new Mode(new ExileTopXMayPlayUntilEffect(3, Duration.EndOfTurn));
        mode.withCost(new ManaCostsImpl<>("{R}{R}"));
        this.getSpellAbility().addMode(mode);
    }

    private VoidcallersInvocation(final VoidcallersInvocation card) {
        super(card);
    }

    @Override
    public VoidcallersInvocation copy() {
        return new VoidcallersInvocation(this);
    }
}