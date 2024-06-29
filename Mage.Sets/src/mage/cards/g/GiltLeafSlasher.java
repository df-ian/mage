package mage.cards.g;

import mage.MageInt;
import mage.abilities.common.DiesSourceTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.delayed.ReflexiveTriggeredAbility;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.effects.common.DoWhenCostPaid;
import mage.abilities.effects.common.LoseLifeOpponentsEffect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class GiltLeafSlasher extends CardImpl {

    public GiltLeafSlasher(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{B}{G}");

        this.subtype.add(SubType.ELF);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        // When Ambulatory Edifice enters the battlefield, you may pay 2 life. When you do, target creature gets -1/-1 until end of turn.
        ReflexiveTriggeredAbility ability = new ReflexiveTriggeredAbility(
                new BoostTargetEffect(-2, -2), false
        );
        ability.addTarget(new TargetCreaturePermanent());
        this.addAbility(new EntersBattlefieldTriggeredAbility(
                new DoWhenCostPaid(ability, new PayLifeCost(2), "Pay 2 life?")
        ));

        this.addAbility(new DiesSourceTriggeredAbility(new LoseLifeOpponentsEffect(2)));
    }

    private GiltLeafSlasher(final GiltLeafSlasher card) {
        super(card);
    }

    @Override
    public GiltLeafSlasher copy() {
        return new GiltLeafSlasher(this);
    }
}
