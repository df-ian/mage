package mage.cards.c;

import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.ruleModifying.PlayLandsFromGraveyardControllerEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;
import mage.filter.common.FilterLandPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class CrucibleOfLoams extends CardImpl {

    public CrucibleOfLoams(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");

        // You may play lands from your graveyard.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new PlayLandsFromGraveyardControllerEffect()));

        this.addAbility(new SimpleActivatedAbility(new DrawCardSourceControllerEffect(1), new SacrificeTargetCost(2, new FilterLandPermanent("lands"))));
    }

    private CrucibleOfLoams(final CrucibleOfLoams card) {
        super(card);
    }

    @Override
    public CrucibleOfLoams copy() {
        return new CrucibleOfLoams(this);
    }
}
