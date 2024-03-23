package mage.cards.f;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.SquadAbility;
import mage.abilities.keyword.StormAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.counters.CounterType;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.mageobject.AnotherPredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.game.permanent.PermanentToken;

import java.util.UUID;

/**
 * @author Ian
 */
public final class FblthpLostLeader extends CardImpl {

    public FblthpLostLeader(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{U}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HOMUNCULUS);
        this.power = new MageInt(0);
        this.toughness = new MageInt(1);

        // Storm
        this.addAbility(new SquadAbility(new ManaCostsImpl<>("{2}")));

        this.addAbility(new EntersBattlefieldTriggeredAbility(new DrawCardSourceControllerEffect(1)));

        // Aeve, Progenitor Ooze isn't legendary as long as it's a token.
        this.addAbility(new SimpleStaticAbility(new FblthpLostLeaderNonLegendaryEffect()));
    }

    private FblthpLostLeader(final FblthpLostLeader card) {
        super(card);
    }

    @Override
    public FblthpLostLeader copy() {
        return new FblthpLostLeader(this);
    }
}

class FblthpLostLeaderNonLegendaryEffect extends ContinuousEffectImpl {

    FblthpLostLeaderNonLegendaryEffect() {
        super(Duration.WhileOnBattlefield, Layer.TypeChangingEffects_4, SubLayer.NA, Outcome.Benefit);
        this.staticText = "{this} isn't legendary if it's a token";
    }

    private FblthpLostLeaderNonLegendaryEffect(final FblthpLostLeaderNonLegendaryEffect effect) {
        super(effect);
    }

    @Override
    public FblthpLostLeaderNonLegendaryEffect copy() {
        return new FblthpLostLeaderNonLegendaryEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanent(source.getSourceId());
        if (permanent instanceof PermanentToken) {
            permanent.removeSuperType(game, SuperType.LEGENDARY);
            return true;
        }
        return false;
    }
}
