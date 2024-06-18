package mage.cards.a;

import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.costs.common.ExileFromHandCost;
import mage.abilities.effects.common.ExileAndCreateTokenEffect;
import mage.abilities.effects.common.ExileAndGainLifeEqualPowerTargetEffect;
import mage.abilities.keyword.EvokeAbility;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.common.FilterNonlandPermanent;
import mage.filter.predicate.mageobject.AnotherPredicate;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.game.permanent.token.PhyrexianMiteToken;
import mage.target.TargetPermanent;
import mage.target.common.TargetCardInHand;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class AtraxasAssimilator extends CardImpl {

    private static final FilterPermanent filter = new FilterNonlandPermanent("other nonland permanent");

    static {
        filter.add(AnotherPredicate.instance);
    }

    public AtraxasAssimilator(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{3}{W}");

        this.subtype.add(SubType.PHYREXIAN);
        this.subtype.add(SubType.HORROR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(4);

        // Flash
        this.addAbility(FlyingAbility.getInstance());


        // When Solitude enters the battlefield, exile up to one other target creature. That creature's controller gains life equal to its power.
        Ability ability = new EntersBattlefieldTriggeredAbility(new ExileAndCreateTokenEffect(new PhyrexianMiteToken())
                .setText("exile one other target nonland permanent. That permanent's controller creates a 1/1 colorless Phyrexian Mite creature token with toxic 1 and 'This creature can't block.'"));
        ability.addTarget(new TargetPermanent(0, 1, filter));
        this.addAbility(ability);
    }

    private AtraxasAssimilator(final AtraxasAssimilator card) {
        super(card);
    }

    @Override
    public AtraxasAssimilator copy() {
        return new AtraxasAssimilator(this);
    }
}
