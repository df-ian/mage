package mage.cards.t;

import mage.MageInt;
import mage.abilities.common.FirstSpellOpponentsTurnTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.ExileSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.turn.AddExtraTurnControllerEffect;
import mage.abilities.keyword.FlashAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class TeferiAkosa extends CardImpl {

    public TeferiAkosa(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{U}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        this.addAbility(FlashAbility.getInstance());

        // Whenever you cast your first spell during each opponent's turn, draw a card.
        this.addAbility(new FirstSpellOpponentsTurnTriggeredAbility(
                new DrawCardSourceControllerEffect(1), false
        ));
        SimpleActivatedAbility ability = new SimpleActivatedAbility(new AddExtraTurnControllerEffect(false), new ManaCostsImpl<>("{5}{U}{U}"));
        ability.addCost(new ExileSourceCost());
        this.addAbility(ability);
    }

    private TeferiAkosa(final TeferiAkosa card) {
        super(card);
    }

    @Override
    public TeferiAkosa copy() {
        return new TeferiAkosa(this);
    }
}
