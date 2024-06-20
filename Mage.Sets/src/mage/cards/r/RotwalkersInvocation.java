package mage.cards.r;

import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.effects.common.MillThenPutInHandEffect;
import mage.abilities.effects.common.SacrificeAllEffect;
import mage.abilities.effects.common.counter.ProliferateEffect;
import mage.abilities.keyword.DecayedAbility;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.*;
import mage.choices.Choice;
import mage.choices.ChoiceImpl;
import mage.constants.*;
import mage.counters.CounterType;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.common.FilterPermanentCard;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.game.permanent.token.Token;
import mage.players.Player;
import mage.target.TargetCard;
import mage.target.TargetPermanent;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCardInLibrary;
import mage.util.functions.CopyTokenFunction;

import java.util.*;

/**
 * @author Ian
 */
public final class RotwalkersInvocation extends CardImpl {


    public RotwalkersInvocation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{0}");

        // Spree
        this.addAbility(new SpreeAbility(this));


        // + {U} - single turn yarock
        this.getSpellAbility().addEffect(new SacrificeAllEffect(1, StaticFilters.FILTER_CREATURE_NON_TOKEN));
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{B}"));

        Mode mode = new Mode(new MillThenPutInHandEffect(4, StaticFilters.FILTER_CARD_A_PERMANENT));
        this.getSpellAbility().addMode(mode.withCost(new ManaCostsImpl<>("{1}{G}")));

        mode = new Mode(new RotwalkersInvocationEffect());
        mode.addTarget(new TargetCardInGraveyard(0, 1, StaticFilters.FILTER_CARD_PERMANENT, true));
        this.getSpellAbility().addMode(mode
                .withCost(new ManaCostsImpl<>("{2}{U}")));
    }

    private RotwalkersInvocation(final RotwalkersInvocation card) {
        super(card);
    }

    @Override
    public RotwalkersInvocation copy() {
        return new RotwalkersInvocation(this);
    }
}


class RotwalkersInvocationEffect extends OneShotEffect {

    RotwalkersInvocationEffect() {
        super(Outcome.PutCreatureInPlay);
        this.staticText = "Exile a permanent card from your graveyard, then create a token that's a copy of that card, " +
                "except it's a 2/2 black Zombie with decayed.";
    }

    private RotwalkersInvocationEffect(final RotwalkersInvocationEffect effect) {
        super(effect);
    }

    @Override
    public RotwalkersInvocationEffect copy() {
        return new RotwalkersInvocationEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            Set<Card> cardsToExile = new HashSet<>(this.getTargetPointer().getTargets(game, source).size());
            for (UUID targetId : this.getTargetPointer().getTargets(game, source)) {
                Card card = controller.getGraveyard().get(targetId, game);
                if (card != null) {
                    cardsToExile.add(card);
                }
            }
            controller.moveCardsToExile(cardsToExile, source, game, true, null, "");
            for (Card card : cardsToExile) {
                if (game.getState().getZone(card.getId()) == Zone.EXILED) {
                    // create token and modify all attributes permanently (without game usage)
                    Token token = CopyTokenFunction.createTokenCopy(card, game);
                    token.removePTCDA();
                    token.setPower(2);
                    token.setToughness(2);
                    token.setColor(ObjectColor.BLACK);
                    token.removeAllCardTypes();
                    token.addCardType(CardType.CREATURE);
                    token.addSubType(SubType.ZOMBIE);
                    token.addAbility(new DecayedAbility());
                    token.putOntoBattlefield(1, game, source, source.getControllerId());
                }
            }
            return true;
        }
        return false;
    }
}