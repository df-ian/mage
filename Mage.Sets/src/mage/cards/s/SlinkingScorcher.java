
package mage.cards.s;

import mage.MageInt;
import mage.abilities.keyword.AffinityForArtifactsAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SlinkingScorcher extends CardImpl {

    public SlinkingScorcher(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT,CardType.CREATURE},"{3}{R}");
        this.subtype.add(SubType.BEAST);

        this.power = new MageInt(2);
        this.toughness = new MageInt(1);


        this.addAbility(new AffinityForArtifactsAbility());
        this.addAbility(HasteAbility.getInstance());
    }

    private SlinkingScorcher(final SlinkingScorcher card) {
        super(card);
    }

    @Override
    public SlinkingScorcher copy() {
        return new SlinkingScorcher(this);
    }
}
