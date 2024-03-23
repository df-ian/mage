
package mage.cards.d;

import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.DiscardCardCost;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.continuous.PlayAdditionalLandsControllerEffect;
import mage.abilities.effects.keyword.ScryEffect;
import mage.abilities.keyword.DeathtouchAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class DinaVitalityAlchemist extends CardImpl {

    public DinaVitalityAlchemist(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{B}{G}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.DRYAD);
        this.subtype.add(SubType.DRUID);

        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        this.addAbility(DeathtouchAbility.getInstance());
        this.addAbility(LifelinkAbility.getInstance());

        SimpleActivatedAbility ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new DrawCardSourceControllerEffect(1),
                new SacrificeTargetCost(StaticFilters.FILTER_PERMANENT_A));
        ability.setMaxActivationsPerTurn(1);
        ability.addCost(new PayLifeCost(1));
        this.addAbility(ability);

        ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new PlayAdditionalLandsControllerEffect(1, Duration.EndOfTurn), new DiscardCardCost(false));
        ability.addCost(new PayLifeCost(1));
        this.addAbility(ability);
    }

    private DinaVitalityAlchemist(final DinaVitalityAlchemist card) {
        super(card);
    }

    @Override
    public DinaVitalityAlchemist copy() {
        return new DinaVitalityAlchemist(this);
    }

}
