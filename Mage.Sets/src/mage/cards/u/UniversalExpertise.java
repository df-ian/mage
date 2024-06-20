package mage.cards.u;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfCombatTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.condition.common.FullPartyCondition;
import mage.abilities.decorator.ConditionalInterveningIfTriggeredAbility;
import mage.abilities.dynamicvalue.common.PartyCount;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.effects.common.continuous.GainAbilityAllEffect;
import mage.abilities.hint.common.PartyCountHint;
import mage.abilities.keyword.ChannelAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.TargetController;
import mage.filter.StaticFilters;
import mage.game.permanent.token.KorWarriorToken;
import mage.game.permanent.token.PartyToken;

import java.util.UUID;

/**
 * @author Ian
 */
public final class UniversalExpertise extends CardImpl {

    public UniversalExpertise(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{W}");

        // At the beginning of combat on your turn, if you have a full party, creatures you control get +1/+0 and gain indestructible until end of turn.
        Ability ability = new ConditionalInterveningIfTriggeredAbility(
                new BeginningOfCombatTriggeredAbility(
                        new BoostControlledEffect(
                                1, 0, Duration.EndOfTurn
                        ), TargetController.ANY, false
                ), FullPartyCondition.instance, "At the beginning of each combat, " +
                "if you have a full party, creatures you control get +1/+0 and gain indestructible until end of turn."
        );
        ability.addEffect(new GainAbilityAllEffect(
                IndestructibleAbility.getInstance(), Duration.EndOfTurn,
                StaticFilters.FILTER_CONTROLLED_CREATURE
        ));
        this.addAbility(ability);

        this.addAbility(new ChannelAbility("{1}{W}", new CreateTokenEffect(new PartyToken(), 2)));
    }

    private UniversalExpertise(final UniversalExpertise card) {
        super(card);
    }

    @Override
    public UniversalExpertise copy() {
        return new UniversalExpertise(this);
    }
}
