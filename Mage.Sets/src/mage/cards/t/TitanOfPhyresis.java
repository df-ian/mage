package mage.cards.t;

import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.dynamicvalue.common.TotalPermanentsManaValue;
import mage.abilities.effects.common.ReturnSourceFromGraveyardToBattlefieldEffect;
import mage.abilities.effects.common.ReturnSourceFromGraveyardToHandEffect;
import mage.abilities.effects.common.cost.SpellCostReductionSourceEffect;
import mage.abilities.keyword.IndestructibleAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterControlledArtifactPermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.Predicates;

import java.util.UUID;

/**
 * @author Ian
 */
public final class TitanOfPhyresis extends CardImpl {

    private static final FilterControlledPermanent filter = new FilterControlledArtifactPermanent("artifacts");


    public TitanOfPhyresis(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{10}");
        this.subtype.add(SubType.PHYREXIAN);
        this.subtype.add(SubType.GOLEM);
        this.power = new MageInt(10);
        this.toughness = new MageInt(10);

        this.addAbility(TrampleAbility.getInstance());
        this.addAbility(IndestructibleAbility.getInstance());

        this.addAbility(new SimpleActivatedAbility(Zone.GRAVEYARD, new ReturnSourceFromGraveyardToBattlefieldEffect(true), new SacrificeTargetCost(4, filter)));
    }

    private TitanOfPhyresis(final TitanOfPhyresis card) {
        super(card);
    }

    @Override
    public TitanOfPhyresis copy() {
        return new TitanOfPhyresis(this);
    }
}
