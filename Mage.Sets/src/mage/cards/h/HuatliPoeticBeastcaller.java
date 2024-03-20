package mage.cards.h;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.EntersBattlefieldControlledTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.effects.common.cost.SpellsCostReductionControllerEffect;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.common.FilterCreatureCard;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.PowerPredicate;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class HuatliPoeticBeastcaller extends CardImpl {

    private static final FilterCard filter = new FilterCreatureCard();
    private static final FilterCreaturePermanent filter2 = new FilterCreaturePermanent();

    static {
        filter.add(new PowerPredicate(ComparisonType.MORE_THAN, 2));
        filter2.add(new PowerPredicate(ComparisonType.MORE_THAN, 2));
    }

    public HuatliPoeticBeastcaller(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{G}{W}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WARRIOR);
        this.subtype.add(SubType.BARD);
        this.power = new MageInt(3);
        this.toughness = new MageInt(4);

        // Creature spells you cast with power 4 or greater cost {2} less to cast.
        this.addAbility(new SimpleStaticAbility(
                Zone.BATTLEFIELD,
                new SpellsCostReductionControllerEffect(filter, 2)
                        .setText("Creature spells you cast with power 3 or greater cost {2} less to cast")
        ));



        // Whenever a creature with power 4 or greater enters the battlefield under your control, draw a card.
        this.addAbility(new EntersBattlefieldControlledTriggeredAbility(
                Zone.BATTLEFIELD, new DrawCardSourceControllerEffect(1), filter2, false
        ));
    }

    private HuatliPoeticBeastcaller(final HuatliPoeticBeastcaller card) {
        super(card);
    }

    @Override
    public HuatliPoeticBeastcaller copy() {
        return new HuatliPoeticBeastcaller(this);
    }
}
