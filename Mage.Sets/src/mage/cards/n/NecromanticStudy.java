
package mage.cards.n;

import mage.abilities.effects.common.ExileSpellEffect;
import mage.abilities.effects.common.ReturnFromGraveyardToHandTargetEffect;
import mage.abilities.keyword.DredgeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.target.common.TargetCardInYourGraveyard;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class NecromanticStudy extends CardImpl {

    public NecromanticStudy(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{2}{B/G}");



        // Return target card from your graveyard to your hand. Exile Treasured Find.
        this.getSpellAbility().addEffect(new ReturnFromGraveyardToHandTargetEffect());
        this.getSpellAbility().addTarget(new TargetCardInYourGraveyard());
        this.getSpellAbility().addEffect(new ExileSpellEffect());

        this.addAbility(new DredgeAbility(2));
    }

    private NecromanticStudy(final NecromanticStudy card) {
        super(card);
    }

    @Override
    public NecromanticStudy copy() {
        return new NecromanticStudy(this);
    }
}
