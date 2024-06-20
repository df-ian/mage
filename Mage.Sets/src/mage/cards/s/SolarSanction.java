package mage.cards.s;

import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.ExileReturnBattlefieldNextEndStepTargetEffect;
import mage.abilities.effects.common.ExileUntilSourceLeavesEffect;
import mage.abilities.keyword.ChannelAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.target.TargetPermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SolarSanction extends CardImpl {

    public SolarSanction(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{1}{W}");

        // When Touch the Spirit Realm enters the battlefield, exile up to one target artifact or creature until
        // Touch the Spirit Realm leaves the battlefield.
        Ability ability = new EntersBattlefieldTriggeredAbility(new ExileUntilSourceLeavesEffect());
        ability.addTarget(new TargetPermanent(0, 1, StaticFilters.FILTER_PERMANENT_NON_LAND));
        this.addAbility(ability);

        // Channel - {1}{W}, Discard Touch the Spirit Realm: Exile target artifact or creature.
        // Return it to the battlefield under its owner's control at the beginning of the next end step.
        Ability channelAbility = new ChannelAbility("{1}{W}",
                new ExileReturnBattlefieldNextEndStepTargetEffect().withTextThatCard(false));
        channelAbility.addTarget(new TargetPermanent(StaticFilters.FILTER_PERMANENT_NON_LAND));
        this.addAbility(channelAbility);
    }

    private SolarSanction(final SolarSanction card) {
        super(card);
    }

    @Override
    public SolarSanction copy() {
        return new SolarSanction(this);
    }
}
