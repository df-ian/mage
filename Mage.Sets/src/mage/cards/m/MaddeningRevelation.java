package mage.cards.m;

import mage.abilities.Ability;
import mage.abilities.condition.common.KickedCondition;
import mage.abilities.costs.common.DiscardCardCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CounterUnlessPaysEffect;
import mage.abilities.keyword.KickerAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.game.Game;
import mage.game.stack.Spell;
import mage.players.Player;
import mage.target.TargetSpell;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class MaddeningRevelation extends CardImpl {

    public MaddeningRevelation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{U}");

        // Kicker {2}
        this.addAbility(new KickerAbility(new DiscardCardCost()));

        // Counter target spell if its converted mana cost is 2 or less. 
        // If MaddeningRevelation was kicked, counter that spell if its 
        // converted mana cost is 4 or less instead.
        this.getSpellAbility().addEffect(new MaddeningRevelationEffect());
        this.getSpellAbility().addTarget(new TargetSpell());
    }

    private MaddeningRevelation(final MaddeningRevelation card) {
        super(card);
    }

    @Override
    public MaddeningRevelation copy() {
        return new MaddeningRevelation(this);
    }
}

class MaddeningRevelationEffect extends OneShotEffect {

    MaddeningRevelationEffect() {
        super(Outcome.DestroyPermanent);
        this.staticText = "Counter target spell unless its controller pays {1}. If {this} was kicked, counter it unless its controller pays {3} instead.";
    }

    private MaddeningRevelationEffect(final MaddeningRevelationEffect effect) {
        super(effect);
    }

    @Override
    public MaddeningRevelationEffect copy() {
        return new MaddeningRevelationEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            Spell targetSpell = game.getSpell(this.getTargetPointer().getFirst(game, source));
            if (targetSpell != null) {
                if (KickedCondition.ONCE.apply(game, source)) {
                    new CounterUnlessPaysEffect(new ManaCostsImpl<>("{3}")).apply(game, source);
                } else {
                    new CounterUnlessPaysEffect(new ManaCostsImpl<>("{1}")).apply(game, source);
                }
            }
            return true;
        }
        return false;
    }
}
