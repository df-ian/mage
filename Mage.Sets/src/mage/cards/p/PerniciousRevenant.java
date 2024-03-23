package mage.cards.p;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfEndStepTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.condition.common.OpponentsLostLifeCondition;
import mage.abilities.condition.common.PlayersLostLifeCondition;
import mage.abilities.decorator.ConditionalInterveningIfTriggeredAbility;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.LoseLifeOpponentsEffect;
import mage.abilities.effects.common.ReturnSourceFromGraveyardToBattlefieldEffect;
import mage.abilities.hint.common.OpponentsLostLifeHint;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.TargetController;

import java.util.UUID;

/**
 * @author Ian
 */
public final class PerniciousRevenant extends CardImpl {

    public PerniciousRevenant(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{B}");

        this.subtype.add(SubType.ZOMBIE);
        this.subtype.add(SubType.ASSASSIN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        // When Arrogant Outlaw enters the battlefield, if an opponent lost life this turn, each opponent loses 2 life and you gain 2 life.
        Ability ability = new ConditionalInterveningIfTriggeredAbility(
                new BeginningOfEndStepTriggeredAbility(
                        new ReturnSourceFromGraveyardToBattlefieldEffect(true), TargetController.SOURCE_CONTROLLER, false
                ), PlayersLostLifeCondition.revenant, "At the beginning of your end, " +
                "if a player lost 2 or more life this turn, return {this} from your graveyard to the battlefield tapped."
        );

        this.addAbility(ability);
    }

    private PerniciousRevenant(final PerniciousRevenant card) {
        super(card);
    }

    @Override
    public PerniciousRevenant copy() {
        return new PerniciousRevenant(this);
    }
}
