package mage.cards.f;

import mage.MageInt;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.common.EntersBattlefieldOrAttacksSourceTriggeredAbility;
import mage.abilities.effects.common.ExileTopXMayPlayUntilEffect;
import mage.abilities.keyword.FlyingAbility;
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
public final class ForgebornHellkite extends CardImpl {

    public ForgebornHellkite(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{R}");

        this.subtype.add(SubType.DRAGON);
        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        this.addAbility(FlyingAbility.getInstance());

        // Whenever Grotag Night-Runner deals combat damage to a player, exile the top card of your library. You may play that card this turn.
        this.addAbility(new EntersBattlefieldOrAttacksSourceTriggeredAbility(new ExileTopXMayPlayUntilEffect(1, Duration.UntilEndOfYourNextTurn), false));
    }

    private ForgebornHellkite(final ForgebornHellkite card) {
        super(card);
    }

    @Override
    public ForgebornHellkite copy() {
        return new ForgebornHellkite(this);
    }
}
