package mage.cards.a;

import mage.abilities.Ability;
import mage.abilities.LoyaltyAbility;
import mage.abilities.dynamicvalue.common.ControllerLifeCount;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.GetEmblemEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.keyword.DoubleStrikeAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.counters.CounterType;
import mage.game.command.emblems.AjaniAvengerOfThePrideEmblem;
import mage.game.permanent.token.CatToken;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class AjaniAvengerOfThePride extends CardImpl {

    public AjaniAvengerOfThePride(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.PLANESWALKER},"{W}{W}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.AJANI);

        this.setStartingLoyalty(2);
        // +1: Put a +1/+1 counter on up to one target creature.
        Effect effect = new AddCountersTargetEffect(CounterType.P1P1.createInstance());
        effect.setText("Put a +1/+1 counter on up to one target creature");
        Ability ability = new LoyaltyAbility(effect, 1);
        ability.addEffect(new GainAbilityTargetEffect(LifelinkAbility.getInstance(), Duration.EndOfTurn).setText("It gains lifelink until end of turn."));
        ability.addTarget(new TargetCreaturePermanent(0, 1));
        this.addAbility(ability);

        this.addAbility(new LoyaltyAbility(new CreateTokenEffect(new CatToken(), 1), 0));
        this.addAbility(new LoyaltyAbility(new GetEmblemEffect(new AjaniAvengerOfThePrideEmblem()), -5));
    }

    private AjaniAvengerOfThePride(final AjaniAvengerOfThePride card) {
        super(card);
    }

    @Override
    public AjaniAvengerOfThePride copy() {
        return new AjaniAvengerOfThePride(this);
    }
}
