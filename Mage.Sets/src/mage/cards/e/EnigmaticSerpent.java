
package mage.cards.e;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.keyword.CyclingAbility;
import mage.abilities.keyword.DelveAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class EnigmaticSerpent extends CardImpl {

    public EnigmaticSerpent(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{6}{U}");
        this.subtype.add(SubType.SERPENT);

        this.power = new MageInt(5);
        this.toughness = new MageInt(5);
        // Delve
        Ability ability = new DelveAbility();
        ability.setRuleAtTheTop(false);
        this.addAbility(ability);

        this.addAbility(new CyclingAbility(new ManaCostsImpl<>("{U}")));
    }

    private EnigmaticSerpent(final EnigmaticSerpent card) {
        super(card);
    }

    @Override
    public EnigmaticSerpent copy() {
        return new EnigmaticSerpent(this);
    }
}
