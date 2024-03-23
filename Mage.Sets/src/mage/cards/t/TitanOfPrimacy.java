package mage.cards.t;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.cost.CostModificationEffectImpl;
import mage.abilities.effects.common.cost.SpellsCostIncreasingAllEffect;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.Predicates;
import mage.game.Game;

import java.util.UUID;

/**
 * @author Ian
 */
public final class TitanOfPrimacy extends CardImpl {

    private static final FilterCard filter = new FilterCard("Noncreature spells");

    static {
        filter.add(Predicates.not(CardType.CREATURE.getPredicate()));
    }

    public TitanOfPrimacy(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{5}{G}{G}{G}");
        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.GIANT);

        this.power = new MageInt(8);
        this.toughness = new MageInt(8);

        this.addAbility(TrampleAbility.getInstance());

        // Noncreature spells cost {1} more to cast.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new TitanOfPrimacyAdditionalCostEffect(true)));

    }

    private TitanOfPrimacy(final TitanOfPrimacy card) {
        super(card);
    }

    @Override
    public TitanOfPrimacy copy() {
        return new TitanOfPrimacy(this);
    }
}



class TitanOfPrimacyAdditionalCostEffect extends CostModificationEffectImpl {

    private final boolean appliesToSpells;
    private static final FilterControlledPermanent filter = new FilterControlledPermanent("noncreature permanent");

    static {
        filter.add(Predicates.not(CardType.CREATURE.getPredicate()));
    }

    TitanOfPrimacyAdditionalCostEffect(boolean appliesToSpells) {
        super(Duration.WhileOnBattlefield, Outcome.Benefit, CostModificationType.INCREASE_COST);
        this.staticText = "Noncreature spells cost an additional \"Sacrifice a noncreature permanent\" to cast.";
        this.appliesToSpells = appliesToSpells;
    }

    private TitanOfPrimacyAdditionalCostEffect(final TitanOfPrimacyAdditionalCostEffect effect) {
        super(effect);
        appliesToSpells = effect.appliesToSpells;
    }

    @Override
    public boolean apply(Game game, Ability source, Ability abilityToModify) {
        abilityToModify.addCost(new SacrificeTargetCost(1, filter));
        return true;
    }

    @Override
    public boolean applies(Ability abilityToModify, Ability source, Game game) {
        return (appliesToSpells && abilityToModify.getAbilityType() == AbilityType.SPELL && !abilityToModify.getSourceObject(game).getCardType().contains(CardType.CREATURE));
    }

    @Override
    public TitanOfPrimacyAdditionalCostEffect copy() {
        return new TitanOfPrimacyAdditionalCostEffect(this);
    }
}
