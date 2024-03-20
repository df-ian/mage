
package mage.cards.a;

import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.SacrificeEffect;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.target.TargetPlayer;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class AgonizingEdict extends CardImpl {

    public AgonizingEdict(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{1}{B}");

        // Target player sacrifices a creature.
        this.getSpellAbility().addEffect(new SacrificeEffect(StaticFilters.FILTER_PERMANENT_CREATURE, 1, "Target player"));
        this.getSpellAbility().addTarget(new TargetPlayer());

        // Flashback {5}{B}{B}
        this.addAbility(new FlashbackAbility(this, new ManaCostsImpl<>("{2}{B}{B}")));
    }

    private AgonizingEdict(final AgonizingEdict card) {
        super(card);
    }

    @Override
    public AgonizingEdict copy() {
        return new AgonizingEdict(this);
    }
}
