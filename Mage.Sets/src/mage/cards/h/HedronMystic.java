package mage.cards.h;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.LimitedDynamicValue;
import mage.abilities.dynamicvalue.common.CardsInControllerGraveyardCount;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.LoseLifeOpponentsEffect;
import mage.abilities.effects.mana.AddManaOfAnyColorEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.common.FilterLandCard;
import mage.filter.predicate.Predicates;
import mage.target.common.TargetCardInGraveyard;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class HedronMystic extends CardImpl {


    public HedronMystic(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{G}");
        this.subtype.add(SubType.KOR);
        this.subtype.add(SubType.DRUID);

        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        // {T}: Exile target land card from a graveyard. Add one mana of any color.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new ExileTargetEffect(), new TapSourceCost());
        // Because this is no mana ability, this mana will not be calculated during available mana calculation
        ability.addEffect(new AddManaOfAnyColorEffect(1, new LimitedDynamicValue(1,
                new CardsInControllerGraveyardCount(StaticFilters.FILTER_CARD)), false));
        ability.addTarget(new TargetCardInGraveyard(new FilterCard("card from a graveyard")));
        this.addAbility(ability);
    }

    private HedronMystic(final HedronMystic card) {
        super(card);
    }

    @Override
    public HedronMystic copy() {
        return new HedronMystic(this);
    }
}
