package mage.cards.n;

import mage.MageInt;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.ExileTopXMayPlayUntilEffect;
import mage.abilities.effects.common.ReturnToHandSourceEffect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.CantBeBlockedSourceAbility;
import mage.cards.AdventureCard;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.counters.CounterType;
import mage.filter.FilterSpell;
import mage.filter.common.FilterCreatureSpell;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class NimbleOpportunist extends AdventureCard {




    public NimbleOpportunist(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, new CardType[]{CardType.INSTANT}, "{U}", "Lethal Opportunity", "{2}{B}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ROGUE);
        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        this.addAbility(new CantBeBlockedSourceAbility());
        this.addAbility(new DealsCombatDamageToAPlayerTriggeredAbility(new ReturnToHandSourceEffect(), true));

        this.getSpellCard().getSpellAbility().addEffect(new BoostTargetEffect(-5, -5, Duration.EndOfTurn));
        this.getSpellCard().getSpellAbility().addEffect(new DrawCardSourceControllerEffect(1));
        this.getSpellCard().getSpellAbility().addTarget(new TargetCreaturePermanent());

        this.finalizeAdventure();
    }

    private NimbleOpportunist(final NimbleOpportunist card) {
        super(card);
    }

    @Override
    public NimbleOpportunist copy() {
        return new NimbleOpportunist(this);
    }
}
