package mage.cards.t;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.CardsInControllerGraveyardCount;
import mage.abilities.effects.common.ReturnFromGraveyardToBattlefieldTargetEffect;
import mage.abilities.effects.common.cost.SpellCostReductionForEachSourceEffect;
import mage.abilities.hint.ValueHint;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.AbilityWord;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.common.FilterLandCard;
import mage.target.common.TargetCardInYourGraveyard;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ThousandLeggedDeathcoil extends CardImpl {


    public ThousandLeggedDeathcoil(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{5}{B}{G}");

        this.subtype.add(SubType.INSECT);
        this.power = new MageInt(7);
        this.toughness = new MageInt(5);

        // Undergrowth — This spell costs {1} less to cast for each creature card in your graveyard.
        DynamicValue xValue = new CardsInControllerGraveyardCount(StaticFilters.FILTER_CARD);
        Ability ability = new SimpleStaticAbility(Zone.ALL, new SpellCostReductionForEachSourceEffect(1, xValue));
        ability.setRuleAtTheTop(true);
        ability.addHint(new ValueHint("Cards in your graveyard", xValue));
        this.addAbility(ability);
    }

    private ThousandLeggedDeathcoil(final ThousandLeggedDeathcoil card) {
        super(card);
    }

    @Override
    public ThousandLeggedDeathcoil copy() {
        return new ThousandLeggedDeathcoil(this);
    }
}
