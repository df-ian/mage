package mage.cards.s;

import mage.abilities.Ability;
import mage.abilities.costs.Cost;
import mage.abilities.costs.CostImpl;
import mage.abilities.effects.OneShotEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.CardsImpl;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.filter.FilterCard;
import mage.filter.common.FilterControlledPermanent;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCardInHand;
import mage.target.common.TargetControlledPermanent;
import mage.target.common.TargetCreatureOrPlaneswalker;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SarkhansAbreaction extends CardImpl {

    public SarkhansAbreaction(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{1}{R}");

        // As an additional cost to cast this spell, you may reveal a Dragon card from your hand or choose a Dragon you control.
        this.getSpellAbility().addCost(new SarkhansAbreactionCost());

        // Dragon's Fire deals 3 damage to target creature or planeswalker.
        // If you revealed a Dragon card or chose a Dragon as you cast this spell, Dragon's Fire deals damage equal to the power of that card or creature instead.
        this.getSpellAbility().addTarget(new TargetAnyTarget());
        this.getSpellAbility().addEffect(new SarkhansAbreactionEffect());
    }

    private SarkhansAbreaction(final SarkhansAbreaction card) {
        super(card);
    }

    @Override
    public SarkhansAbreaction copy() {
        return new SarkhansAbreaction(this);
    }
}

class SarkhansAbreactionCost extends CostImpl {

    public enum DragonZone {
        HAND,
        BATTLEFIELD,
        NONE
    }

    private DragonZone dragonZone = DragonZone.NONE;
    private UUID selectedCardId = null;

    private static final FilterCard handFilter = new FilterCard("Dragon card from your hand");
    private static final FilterControlledPermanent battlefieldFilter = new FilterControlledPermanent(SubType.DRAGON);

    static {
        handFilter.add(SubType.DRAGON.getPredicate());
    }

    public SarkhansAbreactionCost() {
        this.text = "you may reveal a Dragon card from your hand or choose a Dragon you control";
    }

    private SarkhansAbreactionCost(final SarkhansAbreactionCost cost) {
        super(cost);
        this.dragonZone = cost.dragonZone;
        this.selectedCardId = cost.selectedCardId;
    }

    @Override
    public SarkhansAbreactionCost copy() {
        return new SarkhansAbreactionCost(this);
    }

    @Override
    public boolean canPay(Ability ability, Ability source, UUID controllerId, Game game) {
        return true;
    }

    @Override
    public boolean pay(Ability ability, Game game, Ability source, UUID controllerId, boolean noMana, Cost costToPay) {
        this.getTargets().clear();
        dragonZone = DragonZone.NONE;
        selectedCardId = null;
        Player controller = game.getPlayer(controllerId);
        if (controller != null) {
            boolean dragonInHand = false;
            boolean dragonOnBattlefield = false;
            DragonZone chosenZone = DragonZone.NONE;
            for (UUID cardId : controller.getHand()) {
                Card card = game.getCard(cardId);
                if (card != null && card.hasSubtype(SubType.DRAGON, game)) {
                    dragonInHand = true;
                    break;
                }
            }
            for (Permanent permanent : game.getBattlefield().getAllActivePermanents(controllerId)) {
                if (permanent != null && permanent.hasSubtype(SubType.DRAGON, game)) {
                    dragonOnBattlefield = true;
                    break;
                }
            }
            if (dragonInHand && dragonOnBattlefield) {
                if (controller.chooseUse(Outcome.Benefit, "Reveal a Dragon card from your hand or choose a Dragon you control?", source, game)) {
                    if (controller.chooseUse(Outcome.Benefit, "Choose Dragon from hand or Dragon on battlefield", null, "Hand", "Battlefield", source, game)) {
                        chosenZone = DragonZone.HAND;
                    } else {
                        chosenZone = DragonZone.BATTLEFIELD;
                    }
                }
            }
            else if (dragonInHand) {
                if (controller.chooseUse(Outcome.Benefit, "Reveal a Dragon card from your hand?", source, game)) {
                    chosenZone = DragonZone.HAND;
                }
            }
            else if (dragonOnBattlefield) {
                if (controller.chooseUse(Outcome.Benefit, "Choose a dragon on the battlefield?", source, game)) {
                    chosenZone = DragonZone.BATTLEFIELD;
                }
            }
            switch (chosenZone) {
                case HAND:
                    this.getTargets().add(new TargetCardInHand(handFilter));
                    if (this.getTargets().choose(Outcome.Benefit, controllerId, source.getSourceId(), source, game)) {
                        Card card = game.getCard(this.getTargets().getFirstTarget());
                        if (card != null) {
                            dragonZone = DragonZone.HAND;
                            selectedCardId = this.getTargets().getFirstTarget();
                            controller.revealCards(source, new CardsImpl(card), game);
                        }
                    }
                    break;
                case BATTLEFIELD:
                    this.getTargets().add(new TargetControlledPermanent(battlefieldFilter));
                    if (this.getTargets().choose(Outcome.Benefit, controllerId, source.getSourceId(), source, game)) {
                        Permanent permanent = game.getPermanent(this.getTargets().getFirstTarget());
                        if (permanent != null) {
                            dragonZone = DragonZone.BATTLEFIELD;
                            selectedCardId = this.getTargets().getFirstTarget();
                            game.informPlayers(controller.getLogName() + " chooses " + permanent.getLogName());
                        }
                    }
                    break;
            }
        }
        return paid = true;
    }

    public DragonZone getDragonZone() {
        return dragonZone;
    }

    public UUID getSelectedCardId() {
        return selectedCardId;
    }
}

class SarkhansAbreactionEffect extends OneShotEffect {

    SarkhansAbreactionEffect() {
        super(Outcome.Damage);
        this.staticText = "{this} deals 2 damage to any target. "
                + "If you revealed a Dragon card or chose a Dragon as you cast this spell, {this} deals damage equal to the power of that card or creature instead";
    }

    private SarkhansAbreactionEffect(final SarkhansAbreactionEffect effect) {
        super(effect);
    }

    @Override
    public SarkhansAbreactionEffect copy() {
        return new SarkhansAbreactionEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent targetedPermanent = game.getPermanent(getTargetPointer().getFirst(game, source));
        if (targetedPermanent == null) {
            return false;
        }
        SarkhansAbreactionCost.DragonZone dragonZone = SarkhansAbreactionCost.DragonZone.NONE;
        UUID selectedCardId = null;
        int damage = 2;
        for (Cost cost : source.getCosts()) {
            if (cost instanceof SarkhansAbreactionCost) {
                SarkhansAbreactionCost SarkhansAbreactionCost = (SarkhansAbreactionCost) cost;
                dragonZone = SarkhansAbreactionCost.getDragonZone();
                selectedCardId = SarkhansAbreactionCost.getSelectedCardId();
                break;
            }
        }
        switch (dragonZone) {
            case HAND:
                Card card = game.getCard(selectedCardId);
                if (card != null) {
                    damage = card.getPower().getValue();
                }
                break;
            case BATTLEFIELD:
                Permanent dragon = game.getPermanentOrLKIBattlefield(selectedCardId);
                if (dragon != null) {
                    damage = dragon.getPower().getValue();
                }
                break;
        }
        targetedPermanent.damage(damage, source.getSourceId(), source, game);
        return true;
    }
}
