package mage.cards.c;

import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.costs.common.ExileFromHandCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.CounterUnlessPaysEffect;
import mage.abilities.effects.common.SacrificeAllEffect;
import mage.abilities.effects.common.SacrificeOpponentsEffect;
import mage.abilities.keyword.EvokeAbility;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.target.TargetSpell;
import mage.target.common.TargetCardInHand;

import java.util.UUID;

/**
 * @author Ian
 */
public final class Cruelty extends CardImpl {

    private static final FilterCard filter2 = new FilterCard("a black card from your hand");

    static {
        filter2.add(new ColorPredicate(ObjectColor.BLACK));
    }

    public Cruelty(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{B}{B}");

        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.INCARNATION);
        this.power = new MageInt(4);
        this.toughness = new MageInt(2);

        this.addAbility(new MenaceAbility());


        // When Solitude enters the battlefield, exile up to one other target creature. That creature's controller gains life equal to its power.
        Ability ability = new EntersBattlefieldTriggeredAbility(new SacrificeOpponentsEffect(StaticFilters.FILTER_PERMANENT_CREATURE));
        this.addAbility(ability);

        // Evoke—Exile a white card from your hand.
        this.addAbility(new EvokeAbility(new ExileFromHandCost(new TargetCardInHand(filter2))));
    }

    private Cruelty(final Cruelty card) {
        super(card);
    }

    @Override
    public Cruelty copy() {
        return new Cruelty(this);
    }
}
