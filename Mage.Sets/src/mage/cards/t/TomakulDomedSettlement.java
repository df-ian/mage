
package mage.cards.t;

import mage.ConditionalMana;
import mage.abilities.mana.ColorlessManaAbility;
import mage.abilities.mana.ConditionalAnyColorManaAbility;
import mage.abilities.mana.builder.ConditionalManaBuilder;
import mage.abilities.mana.conditional.CreatureCastConditionalMana;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class TomakulDomedSettlement extends CardImpl {

    public TomakulDomedSettlement(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.LAND},"");

        this.addAbility(new ColorlessManaAbility());

        // {tap}: Add one mana of any color. Spend this mana only to cast a creature spell.
        this.addAbility(new ConditionalAnyColorManaAbility(1, new TomakulDomedSettlementManaBuilder()));
    }

    private TomakulDomedSettlement(final TomakulDomedSettlement card) {
        super(card);
    }

    @Override
    public TomakulDomedSettlement copy() {
        return new TomakulDomedSettlement(this);
    }
}


class TomakulDomedSettlementManaBuilder extends ConditionalManaBuilder {
    @Override
    public ConditionalMana build(Object... options) {
        return new CreatureCastConditionalMana(this.mana);
    }

    @Override
    public String getRule() {
        return "Spend this mana only to cast a creature spell";
    }
}


