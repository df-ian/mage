package mage.cards.m;

import mage.MageObjectReference;
import mage.abilities.Ability;
import mage.abilities.SpellAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.AsThoughEffectImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.ExileThenReturnTargetEffect;
import mage.abilities.effects.common.cost.CostModificationEffectImpl;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.players.Player;
import mage.target.TargetCard;
import mage.target.common.TargetCardInHand;
import mage.target.common.TargetControlledCreaturePermanent;
import mage.target.common.TargetOpponent;
import mage.util.CardUtil;

import java.util.UUID;

/**
 * @author Ian
 */
public final class MomentaryEvanescence extends CardImpl {

    public MomentaryEvanescence(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{W}");

        this.getSpellAbility().addEffect(new MomentaryEvanescenceEffect());
        this.getSpellAbility().addTarget(new TargetOpponent());
    }

    private MomentaryEvanescence(final MomentaryEvanescence card) {
        super(card);
    }

    @Override
    public MomentaryEvanescence copy() {
        return new MomentaryEvanescence(this);
    }
}

class MomentaryEvanescenceEffect extends OneShotEffect {

    MomentaryEvanescenceEffect() {
        super(Outcome.Benefit);
        staticText = "look at target opponent's hand. You may exile a nonland card from it. " +
                "For as long as that card remains exiled, its owner may play it. " +
                "A spell cast this way costs {2} more to cast";
    }

    private MomentaryEvanescenceEffect(final MomentaryEvanescenceEffect effect) {
        super(effect);
    }

    @Override
    public MomentaryEvanescenceEffect copy() {
        return new MomentaryEvanescenceEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Player opponent = game.getPlayer(source.getFirstTarget());
        if (controller == null || opponent == null || opponent.getHand().isEmpty()) {
            return false;
        }
        TargetCard target = new TargetCardInHand(
                0, 1, StaticFilters.FILTER_CARD_A_NON_LAND
        );
        controller.choose(outcome, opponent.getHand(), target, source, game);
        Card card = opponent.getHand().get(target.getFirstTarget(), game);
        if (card == null) {
            return false;
        }
        controller.moveCards(card, Zone.EXILED, source, game);
        game.addEffect(new MomentaryEvanescenceCastEffect(card, game), source);
        game.addEffect(new MomentaryEvanescenceCostEffect(card, game), source);
        return true;
    }
}


class MomentaryEvanescenceCostEffect extends CostModificationEffectImpl {

    private final MageObjectReference mor;

    MomentaryEvanescenceCostEffect(Card card, Game game) {
        super(Duration.Custom, Outcome.Benefit, CostModificationType.INCREASE_COST);
        mor = new MageObjectReference(card, game, 1);
    }

    private MomentaryEvanescenceCostEffect(MomentaryEvanescenceCostEffect effect) {
        super(effect);
        this.mor = effect.mor;
    }

    @Override
    public boolean apply(Game game, Ability source, Ability abilityToModify) {
        CardUtil.increaseCost(abilityToModify, 2);
        return true;
    }

    @Override
    public boolean applies(Ability abilityToModify, Ability source, Game game) {
        if (!(abilityToModify instanceof SpellAbility)) {
            return false;
        }
        if (game.inCheckPlayableState()) { // during playable check, the card is still in exile zone, the zcc is one less
            UUID cardtoCheckId = CardUtil.getMainCardId(game, abilityToModify.getSourceId());
            return mor.getSourceId().equals(cardtoCheckId)
                    && mor.getZoneChangeCounter() == game.getState().getZoneChangeCounter(cardtoCheckId) + 1;
        } else {
            return mor.refersTo(CardUtil.getMainCardId(game, abilityToModify.getSourceId()), game);
        }
    }

    @Override
    public MomentaryEvanescenceCostEffect copy() {
        return new MomentaryEvanescenceCostEffect(this);
    }
}

class MomentaryEvanescenceCastEffect extends AsThoughEffectImpl {

    private final MageObjectReference mor;

    public MomentaryEvanescenceCastEffect(Card card, Game game) {
        super(AsThoughEffectType.PLAY_FROM_NOT_OWN_HAND_ZONE, Duration.Custom, Outcome.Benefit);
        this.mor = new MageObjectReference(card, game);
    }

    private MomentaryEvanescenceCastEffect(final MomentaryEvanescenceCastEffect effect) {
        super(effect);
        this.mor = effect.mor;
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public MomentaryEvanescenceCastEffect copy() {
        return new MomentaryEvanescenceCastEffect(this);
    }

    @Override
    public boolean applies(UUID sourceId, Ability source, UUID affectedControllerId, Game game) {
        Card card = mor.getCard(game);
        if (card == null) {
            discard();
            return false;
        }
        return mor.refersTo(CardUtil.getMainCardId(game, sourceId), game)
                && card.isOwnedBy(affectedControllerId);
    }
}