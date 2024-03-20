
package mage.cards.s;

import mage.abilities.Ability;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.AlternativeCostSourceAbility;
import mage.abilities.costs.common.DiscardCardCost;
import mage.abilities.costs.common.DiscardTargetCost;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.target.TargetSpell;
import mage.target.common.TargetCardInHand;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SpellCompression extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent("an Island");
    static {
        filter.add(SubType.ISLAND.getPredicate());
    }

    public SpellCompression(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{1}{U}{U}");

        // You may discard an Island card and another card rather than pay Foil's mana cost.
        Ability ability = new AlternativeCostSourceAbility(new DiscardCardCost().setText(""), new PermanentsOnTheBattlefieldCondition(filter));
        ability.addCost(new DiscardCardCost().setText("two cards"));
        this.addAbility(ability);

        // Counter target spell.
        this.getSpellAbility().addEffect(new CounterTargetEffect());
        this.getSpellAbility().addTarget(new TargetSpell());
    }

    private SpellCompression(final SpellCompression card) {
        super(card);
    }

    @Override
    public SpellCompression copy() {
        return new SpellCompression(this);
    }
}
