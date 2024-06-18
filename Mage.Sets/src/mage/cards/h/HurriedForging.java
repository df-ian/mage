package mage.cards.h;

import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.delayed.AtTheBeginOfNextEndStepDelayedTriggeredAbility;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.Effect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.SacrificeTargetEffect;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.SurgeAbility;
import mage.cards.*;
import mage.constants.*;
import mage.filter.Filter;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.Target;
import mage.target.TargetCard;
import mage.target.TargetPermanent;
import mage.target.common.TargetControlledCreaturePermanent;
import mage.target.targetpointer.FixedTargets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author Ian
 */
public final class HurriedForging extends CardImpl {

    public HurriedForging(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{R}");

        this.addAbility(new SurgeAbility(this, "{R}"));
        // When Storm Herald enters the battlefield, return any number of Aura cards from your graveyard to the battlefield attached to creatures you control. Exile those Auras at the beginning of your next end step. If those Auras would leave the battlefield, exile them instead of putting them anywhere else.
        this.getSpellAbility().addEffect(new HurriedForgingEffect());
        this.getSpellAbility().addTarget(new TargetControlledCreaturePermanent());
    }

    private HurriedForging(final HurriedForging card) {
        super(card);
    }

    @Override
    public HurriedForging copy() {
        return new HurriedForging(this);
    }
}

class HurriedForgingEffect extends OneShotEffect {

    HurriedForgingEffect() {
        super(Outcome.Benefit);
        this.staticText = "return an Aura or Equipment the battlefield attached to target creature you control. "
                + "Sacrifice that permanent at the beginning of the next end step.";
    }

    private HurriedForgingEffect(final HurriedForgingEffect effect) {
        super(effect);
    }

    @Override
    public HurriedForgingEffect copy() {
        return new HurriedForgingEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            FilterCard filter = new FilterCard("aura or equipment cards to attach to target creature");
            filter.add(Predicates.or(SubType.EQUIPMENT.getPredicate(), SubType.AURA.getPredicate()));
            Cards possibleTargets = new CardsImpl();
            possibleTargets.addAllCards(controller.getHand().getCards(filter, source.getControllerId(), source, game));
            possibleTargets.addAllCards(controller.getGraveyard().getCards(filter, source.getControllerId(), source, game));
            if (possibleTargets.isEmpty()) {
                return true;
            }
            if (!possibleTargets.isEmpty()) {
                TargetCard targetAuras = new TargetCard(0, 1, Zone.ALL, filter);
                targetAuras.withNotTarget(true);
                controller.choose(outcome, possibleTargets, targetAuras, source, game);

                // Move the cards to the battlefield to a creature you control
                List<Permanent> toExile = new ArrayList<>();
                for (UUID auraId : targetAuras.getTargets()) {
                    Card auraCard = game.getCard(auraId);
                    if (auraCard != null) {
                        Permanent targetPermanent = game.getPermanent(this.getTargetPointer().getFirst(game, source));
                        if (!targetPermanent.cantBeAttachedBy(auraCard, source, game, true)) {
                            game.getState().setValue("attachTo:" + auraCard.getId(), targetPermanent);
                            controller.moveCards(auraCard, Zone.BATTLEFIELD, source, game);
                            targetPermanent.addAttachment(auraCard.getId(), source, game);
                            Permanent permanent = game.getPermanent(auraId);
                            if (permanent != null) {
                                toExile.add(permanent);
                            }
                        }
                    }
                }


                Effect exileEffect = new SacrificeTargetEffect("Sacrifice those permanents");
                exileEffect.setTargetPointer(new FixedTargets(toExile, game));
                game.addDelayedTriggeredAbility(
                        new AtTheBeginOfNextEndStepDelayedTriggeredAbility(exileEffect), source);
            }
            return true;
        }
        return false;
    }
}