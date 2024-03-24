
package mage.cards.d;

import mage.ObjectColor;
import mage.abilities.Mode;
import mage.abilities.dynamicvalue.common.ManacostVariableValue;
import mage.abilities.effects.common.ShuffleSpellEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.effects.common.search.SearchLibraryWithLessCMCPutInPlayEffect;
import mage.abilities.keyword.EntwineAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.filter.FilterCard;
import mage.filter.predicate.mageobject.ColorPredicate;

import java.util.UUID;

/**
 * @author Ian
 */
public final class DevastatingZenith extends CardImpl {

    private static final FilterCard filter = new FilterCard("creature card");

    static {
        filter.add(CardType.CREATURE.getPredicate());
    }

    public DevastatingZenith(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{X}{G}{G}");

        // Search your library for a green creature card with converted mana cost X or less, put it onto the battlefield, then shuffle your library.
        this.getSpellAbility().addEffect(new SearchLibraryWithLessCMCPutInPlayEffect(filter));
        Mode mode = new Mode(new BoostControlledEffect(ManacostVariableValue.REGULAR, ManacostVariableValue.REGULAR, Duration.EndOfTurn).setText("Creatures you control get +X/+X"));
        mode.addEffect(new GainAbilityControlledEffect(HasteAbility.getInstance(), Duration.EndOfTurn).setText("gain haste until end of turn").concatBy("and"));
        this.getSpellAbility().getModes().addMode(mode);

        this.addAbility(new EntwineAbility("{1}{G}"));
    }

    private DevastatingZenith(final DevastatingZenith card) {
        super(card);
    }

    @Override
    public DevastatingZenith copy() {
        return new DevastatingZenith(this);
    }

}
