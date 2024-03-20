package mage.cards.c;

import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.DealsDamageToAPlayerAttachedTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.continuous.BoostEquippedEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.keyword.*;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.AttachmentType;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 * @author Ian
 */
public final class CoilingWrathEngine extends CardImpl {

    public CoilingWrathEngine(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{7}");
        this.subtype.add(SubType.EQUIPMENT);

        this.addAbility(new LivingWeaponAbility());
        this.addAbility(IndestructibleAbility.getInstance());

        // Whenever equipped creature deals combat damage to a player, Sword of Fire
        // and Ice deals 2 damage to any target and you draw a card.
        Ability ability = new SimpleStaticAbility(new BoostEquippedEffect(6, 6));
        ability.addEffect(new GainAbilityAttachedEffect(TrampleAbility.getInstance(), AttachmentType.EQUIPMENT).setText("and has trample"));
        this.addAbility(ability);

        ability = new DealsDamageToAPlayerAttachedTriggeredAbility(
                new DamageTargetEffect(4), "equipped creature", false
        );
        ability.addTarget(new TargetAnyTarget());
        this.addAbility(ability);

        // Equip {2}
        this.addAbility(new EquipAbility(Outcome.Benefit, new GenericManaCost(5), false));
    }

    private CoilingWrathEngine(final CoilingWrathEngine card) {
        super(card);
    }

    @Override
    public CoilingWrathEngine copy() {
        return new CoilingWrathEngine(this);
    }

}
