package mage.cards.f;

import mage.MageInt;
import mage.Mana;
import mage.abilities.costs.common.ExileSourceFromHandCost;
import mage.abilities.mana.SimpleManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;

import java.util.UUID;

/**
 * @author Ian
 */
public final class FelineSpiritGuide extends CardImpl {

    public FelineSpiritGuide(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W}");
        this.subtype.add(SubType.CAT);
        this.subtype.add(SubType.SPIRIT);

        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Exile Simian Spirit Guide from your hand: Add {R}.
        this.addAbility(new SimpleManaAbility(Zone.HAND, Mana.WhiteMana(1), new ExileSourceFromHandCost()));
    }

    private FelineSpiritGuide(final FelineSpiritGuide card) {
        super(card);
    }

    @Override
    public FelineSpiritGuide copy() {
        return new FelineSpiritGuide(this);
    }
}
