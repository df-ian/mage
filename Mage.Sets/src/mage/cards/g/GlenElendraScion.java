
package mage.cards.g;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.PersistAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.StaticFilters;
import mage.target.TargetSpell;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class GlenElendraScion extends CardImpl {

    public GlenElendraScion(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{U}{U}");
        this.subtype.add(SubType.FAERIE);
        this.subtype.add(SubType.WIZARD);

        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Flying
        this.addAbility(FlyingAbility.getInstance());
        // {U}, Sacrifice Glen Elendra Archmage: Counter target noncreature spell.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new CounterTargetEffect(), new ManaCostsImpl<>("{2}"));
        ability.addCost(new SacrificeSourceCost());
        ability.addTarget(new TargetSpell(StaticFilters.FILTER_SPELL));
        this.addAbility(ability);

        // Persist
        this.addAbility(new PersistAbility());
    }

    private GlenElendraScion(final GlenElendraScion card) {
        super(card);
    }

    @Override
    public GlenElendraScion copy() {
        return new GlenElendraScion(this);
    }
}
