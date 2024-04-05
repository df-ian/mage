
package mage.cards.s;

import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.ReturnFromGraveyardToBattlefieldTargetEffect;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.target.common.TargetCardInYourGraveyard;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SeethingReturn extends CardImpl {

    public SeethingReturn(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{3}{B}");

        // Return target creature card from your graveyard to the battlefield.
        this.getSpellAbility().addEffect(new ReturnFromGraveyardToBattlefieldTargetEffect());
        this.getSpellAbility().addTarget(new TargetCardInYourGraveyard(StaticFilters.FILTER_CARD_CREATURE_YOUR_GRAVEYARD));

        FlashbackAbility ability = new FlashbackAbility(this, new ManaCostsImpl<>("{1}{B}"));
        ability.addCost(new SacrificeTargetCost(1, new FilterControlledCreaturePermanent()));
        this.addAbility(ability);
    }

    private SeethingReturn(final SeethingReturn card) {
        super(card);
    }

    @Override
    public SeethingReturn copy() {
        return new SeethingReturn(this);
    }
}
