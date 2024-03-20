
package mage.cards.c;

import mage.MageInt;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.keyword.SquadAbility;
import mage.abilities.mana.AnyColorManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 *
 * @author North, Loki
 */
public final class CompositeMyr extends CardImpl {

    public CompositeMyr(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT,CardType.CREATURE},"{2}");
        this.subtype.add(SubType.MYR);

        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.addAbility(new SquadAbility(new ManaCostsImpl<>("{2}")));

        this.addAbility(new AnyColorManaAbility());
    }

    private CompositeMyr(final CompositeMyr card) {
        super(card);
    }

    @Override
    public CompositeMyr copy() {
        return new CompositeMyr(this);
    }
}
