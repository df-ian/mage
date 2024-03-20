package mage.cards.c;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.predicate.permanent.TokenPredicate;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class CornNibbler extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent();

    static {
        filter.add(TokenPredicate.TRUE);
    }

    private static final Condition condition = new PermanentsOnTheBattlefieldCondition(filter);

    public CornNibbler(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}");

        this.subtype.add(SubType.MOUSE);
        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        this.addAbility(new SimpleStaticAbility(
                Zone.BATTLEFIELD,
                new ConditionalContinuousEffect(
                        new BoostSourceEffect(2, 0, Duration.WhileOnBattlefield),
                        new PermanentsOnTheBattlefieldCondition(
                                StaticFilters.FILTER_CONTROLLED_PERMANENT_ARTIFACT
                        ),
                        "As long as you control an artifact, {this} gets +2/+0"
                )
        ));

        this.addAbility(new SimpleStaticAbility(
                Zone.BATTLEFIELD,
                new ConditionalContinuousEffect(
                        new GainAbilitySourceEffect(FirstStrikeAbility.getInstance(), Duration.WhileOnBattlefield),
                        condition, "as long as you control a token, {this} has first strike"
                )
        ));
    }

    private CornNibbler(final CornNibbler card) {
        super(card);
    }

    @Override
    public CornNibbler copy() {
        return new CornNibbler(this);
    }
}
