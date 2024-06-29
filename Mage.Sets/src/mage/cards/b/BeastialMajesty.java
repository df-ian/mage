package mage.cards.b;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.costs.common.ExileFromGraveCost;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.FightTargetSourceEffect;
import mage.abilities.effects.common.FightTargetsEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.TargetController;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;
import mage.target.TargetPermanent;
import mage.target.common.TargetCardInYourGraveyard;
import mage.target.common.TargetControlledCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class BeastialMajesty extends CardImpl {
    private static final FilterPermanent filter = new FilterCreaturePermanent("creature you don't control");

    static {
        filter.add(TargetController.NOT_YOU.getControllerPredicate());
    }

    public BeastialMajesty(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{2}{G}");

        // Whenever you cast a creature spell, draw a card.
        this.addAbility(new SpellCastControllerTriggeredAbility(
                new DrawCardSourceControllerEffect(1),
                StaticFilters.FILTER_SPELL_A_CREATURE, false
        ));

        Ability a = new SimpleActivatedAbility(new FightTargetsEffect(), new ExileFromGraveCost(new TargetCardInYourGraveyard(2, StaticFilters.FILTER_CARD_CREATURE)));
        a.addTarget(new TargetControlledCreaturePermanent());
        a.addTarget(new TargetPermanent(0, 1, filter, false));
        this.addAbility(a);
    }

    private BeastialMajesty(final BeastialMajesty card) {
        super(card);
    }

    @Override
    public BeastialMajesty copy() {
        return new BeastialMajesty(this);
    }
}
