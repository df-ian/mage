package mage.cards.h;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.SpellAbility;
import mage.abilities.common.EntersBattlefieldControlledTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.PartyCount;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.cost.CostModificationEffectImpl;
import mage.abilities.keyword.DeathtouchAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.game.stack.Spell;
import mage.target.common.TargetAnyTarget;
import mage.util.CardUtil;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * @author Ian
 */
public final class HarbingerOfTithes extends CardImpl {

    public HarbingerOfTithes(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}{B}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.CLERIC);
        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        // Flying
        this.addAbility(DeathtouchAbility.getInstance());

        // Spells your opponents cast cost an additional X life to cast, where X is th enumber of creatures in your party
        this.addAbility(new SimpleStaticAbility(new HarbingerOfTithesCostIncreaseEffect()));
    }

    private HarbingerOfTithes(final HarbingerOfTithes card) {
        super(card);
    }

    @Override
    public HarbingerOfTithes copy() {
        return new HarbingerOfTithes(this);
    }
}

class HarbingerOfTithesCostIncreaseEffect extends CostModificationEffectImpl {

    HarbingerOfTithesCostIncreaseEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Benefit, CostModificationType.INCREASE_COST);
        this.staticText = "Spells your opponents cast cost an additional X life to cast, where X is the number of creatures in your party";
    }

    private HarbingerOfTithesCostIncreaseEffect(HarbingerOfTithesCostIncreaseEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source, Ability abilityToModify) {
        abilityToModify.addCost(new PayLifeCost(PartyCount.instance.calculate(game, source, this)));
        return true;
    }

    @Override
    public boolean applies(Ability abilityToModify, Ability source, Game game) {
        if (!(abilityToModify instanceof SpellAbility)) {
            return false;
        }

        return game.getOpponents(source.getControllerId()).contains(abilityToModify.getControllerId());
    }

    @Override
    public HarbingerOfTithesCostIncreaseEffect copy() {
        return new HarbingerOfTithesCostIncreaseEffect(this);
    }
}

