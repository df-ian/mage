package mage.cards.u;

import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.constants.TargetController;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetSacrifice;

import java.util.UUID;

/**
 * @author Ian
 */
public final class UniversalPredation extends CardImpl {

    public UniversalPredation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{1}{G}");

        // Creatures you control get +1/+0 and have haste.
        Ability ability = new SimpleStaticAbility(new BoostControlledEffect(
                1, 0, Duration.WhileOnBattlefield,
                StaticFilters.FILTER_PERMANENT_CREATURES, false
        ));
        ability.addEffect(new GainAbilityControlledEffect(
                HasteAbility.getInstance(), Duration.WhileOnBattlefield,
                StaticFilters.FILTER_PERMANENT_CREATURES
        ).setText("and have haste"));
        this.addAbility(ability);

        // At the beginning of your upkeep, sacrifice a creature. If you can't, sacrifice Eldrazi Monument.
        this.addAbility(new BeginningOfUpkeepTriggeredAbility(new UniversalPredationEffect(), TargetController.YOU, false));
    }

    private UniversalPredation(final UniversalPredation card) {
        super(card);
    }

    @Override
    public UniversalPredation copy() {
        return new UniversalPredation(this);
    }
}

class UniversalPredationEffect extends OneShotEffect {

    UniversalPredationEffect() {
        super(Outcome.Sacrifice);
        staticText = "sacrifice a creature. If you can't, sacrifice {this}";
    }

    private UniversalPredationEffect(final UniversalPredationEffect effect) {
        super(effect);
    }

    @Override
    public UniversalPredationEffect copy() {
        return new UniversalPredationEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null) {
            return false;
        }
        // Sacrifice a creature
        TargetSacrifice target = new TargetSacrifice(StaticFilters.FILTER_PERMANENT_CREATURE);
        if (target.canChoose(controller.getId(), source, game)) {
            controller.choose(Outcome.Sacrifice, target, source, game);
            Permanent permanent = game.getPermanent(target.getFirstTarget());
            if (permanent != null) {
                return permanent.sacrifice(source, game);
            }
        }
        // If you can't, sacrifice Eldrazi Monument
        Permanent permanent = game.getPermanent(source.getSourceId());
        if (permanent != null) {
            return permanent.sacrifice(source, game);
        }
        return false;
    }

}
