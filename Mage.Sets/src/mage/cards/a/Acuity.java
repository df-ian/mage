package mage.cards.a;

import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.costs.common.ExileFromHandCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.CounterUnlessPaysEffect;
import mage.abilities.effects.common.ExileAndGainLifeEqualPowerTargetEffect;
import mage.abilities.keyword.EvokeAbility;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.AnotherPredicate;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.target.TargetPermanent;
import mage.target.TargetSpell;
import mage.target.common.TargetCardInHand;

import java.util.UUID;

/**
 * @author Ian
 */
public final class Acuity extends CardImpl {

    private static final FilterCard filter2 = new FilterCard("a blue card from your hand");

    static {
        filter2.add(new ColorPredicate(ObjectColor.BLUE));
    }

    public Acuity(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{U}{U}");

        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.INCARNATION);
        this.power = new MageInt(3);
        this.toughness = new MageInt(1);

        // Flash
        this.addAbility(FlashAbility.getInstance());


        // When Solitude enters the battlefield, exile up to one other target creature. That creature's controller gains life equal to its power.
        Ability ability = new EntersBattlefieldTriggeredAbility(new CounterUnlessPaysEffect(new GenericManaCost(2)));
        ability.addTarget(new TargetSpell());
        this.addAbility(ability);

        // Evoke—Exile a white card from your hand.
        this.addAbility(new EvokeAbility(new ExileFromHandCost(new TargetCardInHand(filter2))));
    }

    private Acuity(final Acuity card) {
        super(card);
    }

    @Override
    public Acuity copy() {
        return new Acuity(this);
    }
}
