package mage.cards.c;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.effects.common.CounterUnlessPaysEffect;
import mage.abilities.keyword.FlashAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.target.TargetSpell;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ChromestrokeSavant extends CardImpl {

    public ChromestrokeSavant(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}{U}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.MONK);
        this.power = new MageInt(0);
        this.toughness = new MageInt(2);

        // Flash
        this.addAbility(FlashAbility.getInstance());

        // When Frilled Mystic enters the battlefield, you may counter target spell.
        Ability ability = new EntersBattlefieldTriggeredAbility(new CounterUnlessPaysEffect(new ManaCostsImpl<>("{2}"), false));
        ability.addTarget(new TargetSpell());
        this.addAbility(ability);
    }

    private ChromestrokeSavant(final ChromestrokeSavant card) {
        super(card);
    }

    @Override
    public ChromestrokeSavant copy() {
        return new ChromestrokeSavant(this);
    }
}
// nonagon infinity opens the door