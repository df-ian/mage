
package mage.cards.s;

import mage.abilities.Mode;
import mage.abilities.effects.common.ExileFromZoneTargetEffect;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.ReturnToHandTargetEffect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.abilities.keyword.HexproofAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Zone;
import mage.filter.StaticFilters;
import mage.target.TargetPermanent;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCardInLibrary;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author Plopman
 */
public final class SylvanCharm extends CardImpl {

    public SylvanCharm(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{1}{G}");

        //Choose one - Target creature gets +3/+3 until end of turn
        this.getSpellAbility().addEffect(new SearchLibraryPutInHandEffect(new TargetCardInLibrary(StaticFilters.FILTER_CARD_LAND_A), true));
        //permanents you control gain hexproof until end of turn
        Mode mode = new Mode(new ExileFromZoneTargetEffect(Zone.GRAVEYARD, false));
        mode.addTarget(new TargetCardInGraveyard());
        this.getSpellAbility().addMode(mode);
        //return target creature to its owner's hand.
        Mode mode2 = new Mode(new ExileTargetEffect());
        mode2.addTarget(new TargetPermanent(StaticFilters.FILTER_PERMANENT_ARTIFACT_OR_ENCHANTMENT));
        this.getSpellAbility().addMode(mode2);

    }

    private SylvanCharm(final SylvanCharm card) {
        super(card);
    }

    @Override
    public SylvanCharm copy() {
        return new SylvanCharm(this);
    }
}
