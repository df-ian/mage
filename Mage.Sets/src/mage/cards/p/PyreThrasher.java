package mage.cards.p;

import mage.MageInt;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.effects.common.ExileTopXMayPlayUntilEffect;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class PyreThrasher extends CardImpl {

    public PyreThrasher(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");

        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        this.addAbility(HasteAbility.getInstance());

        // Whenever Grotag Night-Runner deals combat damage to a player, exile the top card of your library. You may play that card this turn.
        this.addAbility(new DealsCombatDamageToAPlayerTriggeredAbility(new ExileTopXMayPlayUntilEffect(2, Duration.UntilEndOfYourNextTurn), false));
    }

    private PyreThrasher(final PyreThrasher card) {
        super(card);
    }

    @Override
    public PyreThrasher copy() {
        return new PyreThrasher(this);
    }
}
