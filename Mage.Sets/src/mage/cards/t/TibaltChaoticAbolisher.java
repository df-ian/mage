package mage.cards.t;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.costs.common.DiscardCardCost;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.MyriadAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 * @author Ian
 */
public final class TibaltChaoticAbolisher extends CardImpl {

    public TibaltChaoticAbolisher(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");

        this.supertype.add(SuperType.LEGENDARY);

        this.subtype.add(SubType.DEVIL);
        this.subtype.add(SubType.HUMAN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        this.addAbility(FirstStrikeAbility.getInstance());

        Ability a = new EntersBattlefieldTriggeredAbility(new DoIfCostPaid(new DamageTargetEffect(2), new DiscardCardCost()));
        a.addTarget(new TargetAnyTarget());
    }

    private TibaltChaoticAbolisher(final TibaltChaoticAbolisher card) {
        super(card);
    }

    @Override
    public TibaltChaoticAbolisher copy() {
        return new TibaltChaoticAbolisher(this);
    }
}
