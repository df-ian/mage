package mage.cards.s;

import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.DealsDamageToAPlayerAttachedTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.continuous.BoostEquippedEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.keyword.EquipAbility;
import mage.abilities.keyword.ProtectionAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.AttachmentType;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.counters.Counter;
import mage.counters.CounterType;
import mage.game.permanent.token.GoblinToken;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SwordOfMightAndMuster extends CardImpl {

    public SwordOfMightAndMuster(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");
        this.subtype.add(SubType.EQUIPMENT);

        // Equipped creature gets +2/+2 and has protection from red and from blue.
        Ability ability = new SimpleStaticAbility(new BoostEquippedEffect(2, 2));
        ability.addEffect(new GainAbilityAttachedEffect(
                ProtectionAbility.from(ObjectColor.RED, ObjectColor.GREEN), AttachmentType.EQUIPMENT
        ).setText("and has protection from red and from green"));
        this.addAbility(ability);

        // Whenever equipped creature deals combat damage to a player, Sword of Fire
        // and Ice deals 2 damage to any target and you draw a card.
        ability = new DealsDamageToAPlayerAttachedTriggeredAbility(
                new CreateTokenEffect(new GoblinToken()), "equipped creature", false
        );
        ability.addEffect(new AddCountersTargetEffect(CounterType.P1P1.createInstance(1)).concatBy("and"));
        ability.addTarget(new TargetAnyTarget());
        this.addAbility(ability);

        this.addAbility(new EquipAbility(Outcome.Benefit, new GenericManaCost(1), false));
    }

    private SwordOfMightAndMuster(final SwordOfMightAndMuster card) {
        super(card);
    }

    @Override
    public SwordOfMightAndMuster copy() {
        return new SwordOfMightAndMuster(this);
    }

}
