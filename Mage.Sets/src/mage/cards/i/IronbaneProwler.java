
package mage.cards.i;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.SacrificeSourceUnlessPaysEffect;
import mage.abilities.effects.common.continuous.GainAbilityAllEffect;
import mage.abilities.keyword.EscapeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.predicate.Predicates;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class IronbaneProwler extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent("artifacts and enchantments");

    static {
        filter.add(Predicates.or(CardType.ARTIFACT.getPredicate(), CardType.ENCHANTMENT.getPredicate()));
    }

    public IronbaneProwler(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{G}{G}");
        this.subtype.add(SubType.BEAST);

        this.power = new MageInt(2);
        this.toughness = new MageInt(2);
        // All artifacts have "At the beginning of your upkeep, sacrifice this artifact unless you pay {1}."
        Ability gainedAbility = new BeginningOfUpkeepTriggeredAbility(new SacrificeSourceUnlessPaysEffect(new GenericManaCost(1)), TargetController.YOU, false);
        Effect effect = new GainAbilityAllEffect(gainedAbility, Duration.WhileOnBattlefield, filter, false);
        effect.setText("All artifacts and enchantments have \"At the beginning of your upkeep, sacrifice this permanent unless you pay {1}.\"");
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, effect));

        this.addAbility(new EscapeAbility(this, "{2}{G}{G}", 4));
    }

    private IronbaneProwler(final IronbaneProwler card) {
        super(card);
    }

    @Override
    public IronbaneProwler copy() {
        return new IronbaneProwler(this);
    }
}
