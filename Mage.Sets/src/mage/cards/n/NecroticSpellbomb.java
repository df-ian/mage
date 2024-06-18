
package mage.cards.n;

import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.mana.ColoredManaCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.ReturnFromGraveyardToHandTargetEffect;
import mage.abilities.effects.common.discard.DiscardEachPlayerEffect;
import mage.abilities.effects.common.discard.DiscardTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ColoredManaSymbol;
import mage.constants.TargetController;
import mage.constants.Zone;
import mage.filter.StaticFilters;
import mage.target.TargetPlayer;
import mage.target.common.TargetCardInGraveyard;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class NecroticSpellbomb extends CardImpl {

    public NecroticSpellbomb(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{0}");
        this.addAbility(new EntersBattlefieldTriggeredAbility(new DoIfCostPaid(new DiscardEachPlayerEffect(TargetController.EACH_PLAYER), new ManaCostsImpl<>("{B}"))));
        Ability firstAbility = new SimpleActivatedAbility(Zone.BATTLEFIELD, new ReturnFromGraveyardToHandTargetEffect(), new ColoredManaCost(ColoredManaSymbol.B));
        firstAbility.addCost(new SacrificeSourceCost());
        firstAbility.addTarget(new TargetCardInGraveyard(0, 1, StaticFilters.FILTER_CARD_CREATURE));
        this.addAbility(firstAbility);
    }

    private NecroticSpellbomb(final NecroticSpellbomb card) {
        super(card);
    }

    @Override
    public NecroticSpellbomb copy() {
        return new NecroticSpellbomb(this);
    }
}
