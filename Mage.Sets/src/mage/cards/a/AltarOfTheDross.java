package mage.cards.a;

import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.dynamicvalue.common.CreaturesYouControlCount;
import mage.abilities.mana.AnyColorManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.Predicates;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class AltarOfTheDross extends CardImpl {

    public AltarOfTheDross(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{3}{B/P}");

        FilterControlledPermanent filter = new FilterControlledPermanent("artifact or creature");
        filter.add(Predicates.or(CardType.CREATURE.getPredicate(), CardType.ARTIFACT.getPredicate()));
        // Sacrifice a creature: Add one mana of any color.
        this.addAbility(new AnyColorManaAbility(
                new SacrificeTargetCost(filter)));
    }

    private AltarOfTheDross(final AltarOfTheDross card) {
        super(card);
    }

    @Override
    public AltarOfTheDross copy() {
        return new AltarOfTheDross(this);
    }
}
