package mage.cards.o;

import mage.abilities.Ability;
import mage.abilities.SpellAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.cost.CostModificationEffectImpl;
import mage.abilities.effects.keyword.ScryEffect;
import mage.abilities.keyword.FlashAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.game.Game;
import mage.util.CardUtil;
import mage.watchers.common.CastSpellLastTurnWatcher;

import java.util.UUID;

/**
 * @author Ian
 */
public final class OtherworldlyContrition extends CardImpl {

    public OtherworldlyContrition(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.KINDRED, CardType.ENCHANTMENT}, "{2}");

        this.addSubType(SubType.ELDRAZI);

        // When Omen of the Sea enters the battlefield, scry 2, then draw a card.
        Ability ability = new EntersBattlefieldTriggeredAbility(new DrawCardSourceControllerEffect(1));
        this.addAbility(ability);

        ability = new SimpleActivatedAbility(new OtherworldlyContritionEffect(), new SacrificeSourceCost());
        this.addAbility(ability);
    }

    private OtherworldlyContrition(final OtherworldlyContrition card) {
        super(card);
    }

    @Override
    public OtherworldlyContrition copy() {
        return new OtherworldlyContrition(this);
    }
}


class OtherworldlyContritionEffect extends CostModificationEffectImpl {

    int spellsCast;

    public OtherworldlyContritionEffect() {
        super(Duration.EndOfTurn, Outcome.Benefit, CostModificationType.REDUCE_COST);
        staticText = "the next spell you cast this turn costs {1} less to cast";
    }

    protected OtherworldlyContritionEffect(final OtherworldlyContritionEffect effect) {
        super(effect);
        this.spellsCast = effect.spellsCast;
    }

    @Override
    public void init(Ability source, Game game) {
        super.init(source, game);
        CastSpellLastTurnWatcher watcher = game.getState().getWatcher(CastSpellLastTurnWatcher.class);
        if (watcher != null) {
            spellsCast = watcher.getAmountOfSpellsPlayerCastOnCurrentTurn(source.getControllerId());
        }
    }

    @Override
    public boolean apply(Game game, Ability source, Ability abilityToModify) {
        CardUtil.reduceCost(abilityToModify, 1);
        return true;
    }

    @Override
    public boolean applies(Ability abilityToModify, Ability source, Game game) {
        CastSpellLastTurnWatcher watcher = game.getState().getWatcher(CastSpellLastTurnWatcher.class);
        if (watcher != null) {
            if (watcher.getAmountOfSpellsPlayerCastOnCurrentTurn(source.getControllerId()) > spellsCast) {
                discard(); // only one use 
                return false;
            }
        }
        if (abilityToModify instanceof SpellAbility) {
            return abilityToModify.isControlledBy(source.getControllerId());
        }
        return false;
    }

    @Override
    public OtherworldlyContritionEffect copy() {
        return new OtherworldlyContritionEffect(this);
    }
}
