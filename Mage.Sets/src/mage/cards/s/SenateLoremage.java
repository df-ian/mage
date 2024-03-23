package mage.cards.s;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DrawNthCardTriggeredAbility;
import mage.abilities.common.EntersBattlefieldOrAttacksSourceTriggeredAbility;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.PutCardFromHandOntoBattlefieldEffect;
import mage.abilities.effects.common.TapTargetEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.effects.keyword.InvestigateEffect;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.counters.CounterType;
import mage.filter.StaticFilters;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SenateLoremage extends CardImpl {

    public SenateLoremage(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W}{U}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);
        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        this.addAbility(VigilanceAbility.getInstance());

        Ability ability = new EntersBattlefieldOrAttacksSourceTriggeredAbility(new InvestigateEffect());
        this.addAbility(ability);

        ability = new DrawNthCardTriggeredAbility(new TapTargetEffect(), true, 2);
        ability.addTarget(new TargetCreaturePermanent());
        ability.addEffect(new AddCountersTargetEffect(CounterType.STUN.createInstance(1)).concatBy("and").setText("put a stun counter on it"));
        this.addAbility(ability);
    }

    private SenateLoremage(final SenateLoremage card) {
        super(card);
    }

    @Override
    public SenateLoremage copy() {
        return new SenateLoremage(this);
    }
}
