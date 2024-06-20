package mage.cards.p;

import mage.abilities.Ability;
import mage.abilities.SpellAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.cost.CostModificationEffectImpl;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.common.FilterNonlandCard;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetCard;
import mage.util.CardUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author Ian
 */
public final class PortalToAdarkar extends CardImpl {

    public PortalToAdarkar(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{1}{G}{G}");
        this.addSuperType(SuperType.SNOW);
        // Imprint - When Portal to Adarkar enters the battlefield, you may exile a nonland card from your hand.
        this.addAbility(new EntersBattlefieldTriggeredAbility(new PortalToAdarkarEffect(), true).setAbilityWord(AbilityWord.IMPRINT));

        // Spells you cast that share a card type with the exiled card cost {2} less to cast.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new PortalToAdarkarCostReductionEffect()));
    }

    private PortalToAdarkar(final PortalToAdarkar card) {
        super(card);
    }

    @Override
    public PortalToAdarkar copy() {
        return new PortalToAdarkar(this);
    }
}

class PortalToAdarkarEffect extends OneShotEffect {

    private static FilterCard filter = new FilterNonlandCard();

    public PortalToAdarkarEffect() {
        super(Outcome.Benefit);
        staticText = "exile a nonland card from your hand";
    }

    private PortalToAdarkarEffect(final PortalToAdarkarEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        if (player != null && !player.getHand().isEmpty()) {
            TargetCard target = new TargetCard(Zone.HAND, filter);
            player.choose(Outcome.Benefit, player.getHand(), target, source, game);
            Card card = player.getHand().get(target.getFirstTarget(), game);
            if (card != null) {
                card.moveToExile(getId(), "Portal to Adarkar (Imprint)", source, game);
                Permanent permanent = game.getPermanent(source.getSourceId());
                if (permanent != null) {
                    permanent.imprint(card.getId(), game);
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public PortalToAdarkarEffect copy() {
        return new PortalToAdarkarEffect(this);
    }

}

class PortalToAdarkarCostReductionEffect extends CostModificationEffectImpl {

    private static final String effectText = "Spells you cast that share a card type with the exiled card cost {G}{U} less to cast";

    PortalToAdarkarCostReductionEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Benefit, CostModificationType.REDUCE_COST);
        staticText = effectText;
    }

    private PortalToAdarkarCostReductionEffect(final PortalToAdarkarCostReductionEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source, Ability abilityToModify) {
        SpellAbility spellAbility = (SpellAbility) abilityToModify;
        CardUtil.reduceCost(spellAbility, new ManaCostsImpl<>("{G}{U}"));
        return true;
    }

    @Override
    public boolean applies(Ability abilityToModify, Ability source, Game game) {
        if (abilityToModify instanceof SpellAbility
                && abilityToModify.isControlledBy(source.getControllerId())) {
            Card spellCard = ((SpellAbility) abilityToModify).getCharacteristics(game);
            if (spellCard != null) {
                Permanent permanent = game.getPermanent(source.getSourceId());
                if (permanent != null) {
                    List<UUID> imprinted = permanent.getImprinted();
                    if (imprinted != null && !imprinted.isEmpty()) {
                        Card imprintedCard = game.getCard(imprinted.get(0));
                        return imprintedCard != null && imprintedCard.shareTypes(spellCard, game);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public PortalToAdarkarCostReductionEffect copy() {
        return new PortalToAdarkarCostReductionEffect(this);
    }

}
