package mage.cards.e;

import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.combat.CantAttackYouUnlessPayAllEffect;
import mage.abilities.effects.common.cost.SpellsCostIncreasingAllEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.ProtectionAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 * @author Ian
 */
public final class EleshNornAnnexRegent extends CardImpl {

    public EleshNornAnnexRegent(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W}{W}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.PHYREXIAN);
        this.subtype.add(SubType.PRAETOR);
        this.power = new MageInt(2);
        this.toughness = new MageInt(4);

        // Flying
        this.addAbility(VigilanceAbility.getInstance());

        // Creatures can't attack you or a planeswalker you control unless their controller pays {1} for each of those creatures.
        this.addAbility(new SimpleStaticAbility(new CantAttackYouUnlessPayAllEffect(
            Duration.WhileOnBattlefield,
            new ManaCostsImpl<>("{W/P}"),
            CantAttackYouUnlessPayAllEffect.Scope.YOU_AND_CONTROLLED_PLANESWALKERS
        )));

        // Noncreature spells cost {1} more to cast.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new SpellsCostIncreasingAllEffect(new ManaCostsImpl<>("{W/P}"), StaticFilters.FILTER_CARD_NON_CREATURE, TargetController.OPPONENT)));
    }

    private EleshNornAnnexRegent(final EleshNornAnnexRegent card) {
        super(card);
    }

    @Override
    public EleshNornAnnexRegent copy() {
        return new EleshNornAnnexRegent(this);
    }
}
