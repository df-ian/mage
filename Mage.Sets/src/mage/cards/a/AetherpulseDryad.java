
package mage.cards.a;

import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.cost.SpellsCostReductionControllerEffect;
import mage.abilities.keyword.ShroudAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.FilterCard;
import mage.filter.common.FilterCreatureCard;
import mage.filter.common.FilterInstantOrSorceryCard;

import java.util.UUID;

/**
 * @author Ian
 */
public final class AetherpulseDryad extends CardImpl {

    private static final FilterCard filter = new FilterCreatureCard("Creature spells");

    public AetherpulseDryad(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{G}");
        this.subtype.add(SubType.DRYAD);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        this.addAbility(ShroudAbility.getInstance());

        this.addAbility(new SimpleStaticAbility(new SpellsCostReductionControllerEffect(filter, 1)));
    }

    private AetherpulseDryad(final AetherpulseDryad card) {
        super(card);
    }

    @Override
    public AetherpulseDryad copy() {
        return new AetherpulseDryad(this);
    }
}
