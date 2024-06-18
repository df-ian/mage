package mage.cards.n;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldControlledTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.MetalcraftCondition;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.hint.common.MetalcraftHint;
import mage.abilities.keyword.EquipAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.EquippedPredicate;

import java.util.UUID;

/**
 * @author Ian
 */
public final class NahiriStoneforgeArtisan extends CardImpl {

    private static final FilterPermanent filter
            = new FilterControlledPermanent(SubType.EQUIPMENT, "an Equipment");
    private static FilterPermanent filter2 = new FilterCreaturePermanent("equipped creatures");

    static {
        filter2.add(EquippedPredicate.instance);
    }

    public NahiriStoneforgeArtisan(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{R}{W}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.KOR);
        this.subtype.add(SubType.ARTIFICER);

        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        Ability ability = new SimpleStaticAbility(new GainAbilityControlledEffect(
                HasteAbility.getInstance(), Duration.WhileOnBattlefield, filter
        ));

        this.addAbility(ability);

        // <i>Metalcraft</i> &mdash; Equipment you control have equip {0} as long as you control three or more artifacts
        this.addAbility(new SimpleStaticAbility(new ConditionalContinuousEffect(
                new GainAbilityControlledEffect(new EquipAbility(
                        Outcome.AddAbility, new GenericManaCost(0)
                ), Duration.WhileOnBattlefield, filter), MetalcraftCondition.instance,
                "equipment you control have equip {0} as long as you control three or more artifacts"
        )).setAbilityWord(AbilityWord.METALCRAFT).addHint(MetalcraftHint.instance));
    }

    private NahiriStoneforgeArtisan(final NahiriStoneforgeArtisan card) {
        super(card);
    }

    @Override
    public NahiriStoneforgeArtisan copy() {
        return new NahiriStoneforgeArtisan(this);
    }
}
