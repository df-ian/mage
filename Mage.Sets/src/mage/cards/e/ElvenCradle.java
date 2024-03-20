
package mage.cards.e;

import mage.Mana;
import mage.abilities.common.EntersBattlefieldTappedAbility;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.hint.Hint;
import mage.abilities.hint.ValueHint;
import mage.abilities.mana.DynamicManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SuperType;
import mage.filter.StaticFilters;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class ElvenCradle extends CardImpl {

    private static final Hint hint = new ValueHint(
            "Number of creatures you control", new PermanentsOnBattlefieldCount(StaticFilters.FILTER_PERMANENT_CREATURE_CONTROLLED)
    );

    public ElvenCradle(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");
        this.supertype.add(SuperType.LEGENDARY);

        this.addAbility(new EntersBattlefieldTappedAbility());

        // {T}: Add {G} for each creature you control.
        DynamicManaAbility ability = new DynamicManaAbility(
                Mana.GreenMana(1),
                new PermanentsOnBattlefieldCount(StaticFilters.FILTER_PERMANENT_CREATURE_CONTROLLED)
        );
        this.addAbility(ability.addHint(hint));
    }

    private ElvenCradle(final ElvenCradle card) {
        super(card);
    }

    @Override
    public ElvenCradle copy() {
        return new ElvenCradle(this);
    }
}
