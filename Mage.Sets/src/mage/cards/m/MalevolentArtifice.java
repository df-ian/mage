
package mage.cards.m;

import mage.abilities.Mode;
import mage.abilities.effects.common.DestroyAllEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.common.FilterArtifactCreaturePermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;

import java.util.UUID;

/**
 * @author Ian
 */
public final class MalevolentArtifice extends CardImpl {

    private static final FilterCreaturePermanent filterNonDragon = new FilterCreaturePermanent("non-artifact creatures");

    static {
        filterNonDragon.add(Predicates.not(CardType.ARTIFACT.getPredicate()));
    }

    public MalevolentArtifice(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{3}{B}{B}");

        // Choose one -
        // * Destroy all Dragon creatures.
        this.getSpellAbility().addEffect(new DestroyAllEffect(new FilterArtifactCreaturePermanent()));
        // * Destroy all non-Dragon creatures.
        Mode mode = new Mode(new DestroyAllEffect(filterNonDragon));
        this.getSpellAbility().addMode(mode);
    }

    private MalevolentArtifice(final MalevolentArtifice card) {
        super(card);
    }

    @Override
    public MalevolentArtifice copy() {
        return new MalevolentArtifice(this);
    }
}
