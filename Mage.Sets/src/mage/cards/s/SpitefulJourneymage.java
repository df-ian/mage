package mage.cards.s;

import mage.MageInt;
import mage.abilities.common.FirstSpellOpponentsTurnTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.NotMyTurnCondition;
import mage.abilities.decorator.ConditionalCostModificationEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.cost.SpellsCostReductionControllerEffect;
import mage.abilities.hint.common.MyTurnHint;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.ProwessAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.StaticFilters;
import mage.game.permanent.token.FaerieRogueToken;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SpitefulJourneymage extends CardImpl {

    public SpitefulJourneymage(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{U}{R}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);
        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        this.addAbility(FlashAbility.getInstance());
        this.addAbility(new ProwessAbility());

        this.addAbility(new FirstSpellOpponentsTurnTriggeredAbility(
                new DrawCardSourceControllerEffect(1), false
        ));
    }

    private SpitefulJourneymage(final SpitefulJourneymage card) {
        super(card);
    }

    @Override
    public SpitefulJourneymage copy() {
        return new SpitefulJourneymage(this);
    }
}
