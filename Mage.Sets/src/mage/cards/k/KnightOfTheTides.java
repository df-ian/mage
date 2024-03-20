
package mage.cards.k;

import mage.MageInt;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.effects.keyword.SurveilEffect;
import mage.abilities.keyword.CantBeBlockedSourceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class KnightOfTheTides extends CardImpl {

    public KnightOfTheTides(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{U}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.KNIGHT);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Covert Operative can't be blocked.
        this.addAbility(new CantBeBlockedSourceAbility());

        this.addAbility(new DealsCombatDamageToAPlayerTriggeredAbility(new SurveilEffect(2), false));
    }

    private KnightOfTheTides(final KnightOfTheTides card) {
        super(card);
    }

    @Override
    public KnightOfTheTides copy() {
        return new KnightOfTheTides(this);
    }
}
