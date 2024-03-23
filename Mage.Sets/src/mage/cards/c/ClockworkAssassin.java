package mage.cards.c;

import mage.MageInt;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.delayed.ReflexiveTriggeredAbility;
import mage.abilities.costs.common.DiscardCardCost;
import mage.abilities.dynamicvalue.AdditiveDynamicValue;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.CardsInControllerGraveyardCount;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.dynamicvalue.common.SignInversionDynamicValue;
import mage.abilities.effects.common.DoWhenCostPaid;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.abilities.hint.Hint;
import mage.abilities.hint.ValueHint;
import mage.abilities.keyword.EvokeAbility;
import mage.abilities.keyword.PartnerAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.DefendingPlayerControlsSourceAttackingPredicate;
import mage.target.TargetPermanent;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ClockworkAssassin extends CardImpl {


    private static final DynamicValue xValue = new SignInversionDynamicValue(new AdditiveDynamicValue(
            new PermanentsOnBattlefieldCount(StaticFilters.FILTER_CONTROLLED_PERMANENT_ARTIFACTS),
            new CardsInControllerGraveyardCount(StaticFilters.FILTER_CARD_ARTIFACT)
    ));

    private static final Hint hint = new ValueHint(
            "Artifacts under your control and in your graveyard", new AdditiveDynamicValue(
            new PermanentsOnBattlefieldCount(StaticFilters.FILTER_CONTROLLED_PERMANENT_ARTIFACTS),
            new CardsInControllerGraveyardCount(StaticFilters.FILTER_CARD_ARTIFACT)
    ));

    public ClockworkAssassin(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{1}{B}");

        this.subtype.add(SubType.CONSTRUCT);
        this.subtype.add(SubType.ASSASSIN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        // Whenever Armix, Filigree Thrasher attacks, you may discard a card. When you do, target creature defending player controls gets -X/-X until end of turn, where X is the number of artifacts you control plus the number of artifact cards in your graveyard.
        EntersBattlefieldTriggeredAbility ability = new EntersBattlefieldTriggeredAbility(
                new BoostTargetEffect(xValue, xValue, Duration.EndOfTurn).setText(
                        "target creature gets -X/-X until end of turn, " +
                                "where X is the number of artifacts you control plus the number of artifact cards in your graveyard"), false);
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(ability.addHint(hint));

        this.addAbility(new EvokeAbility("{B}"));
    }

    private ClockworkAssassin(final ClockworkAssassin card) {
        super(card);
    }

    @Override
    public ClockworkAssassin copy() {
        return new ClockworkAssassin(this);
    }
}
