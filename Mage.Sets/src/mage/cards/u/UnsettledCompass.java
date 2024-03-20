package mage.cards.u;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.condition.common.CastFromEverywhereSourceCondition;
import mage.abilities.decorator.ConditionalInterveningIfTriggeredAbility;
import mage.abilities.effects.keyword.DiscoverEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class UnsettledCompass extends CardImpl {

    public UnsettledCompass(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{1}{U}");

        // When Geological Appraiser enters the battlefield, if you cast it, discover 3.
        this.addAbility(new ConditionalInterveningIfTriggeredAbility(
                new EntersBattlefieldTriggeredAbility(new DiscoverEffect(2)),
                CastFromEverywhereSourceCondition.instance,
                "When {this} enters the battlefield, if you cast it, discover 2."
        ));
    }

    private UnsettledCompass(final UnsettledCompass card) {
        super(card);
    }

    @Override
    public UnsettledCompass copy() {
        return new UnsettledCompass(this);
    }
}
