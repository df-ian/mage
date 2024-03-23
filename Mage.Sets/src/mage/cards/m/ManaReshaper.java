package mage.cards.m;

import mage.Mana;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ManaEvent;
import mage.game.events.TappedForManaEvent;
import mage.game.permanent.Permanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ManaReshaper extends CardImpl {

    public ManaReshaper(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{7}");

        this.addAbility(FlyingAbility.getInstance());

        this.addAbility(new WardAbility(new SacrificeTargetCost(3, StaticFilters.FILTER_LAND)));

        this.addAbility(new SimpleStaticAbility(new ManaReshaperReplacementEffect()));
    }

    private ManaReshaper(final ManaReshaper card) {
        super(card);
    }

    @Override
    public ManaReshaper copy() {
        return new ManaReshaper(this);
    }
}

class ManaReshaperReplacementEffect extends ReplacementEffectImpl {

    ManaReshaperReplacementEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Benefit);
        staticText = "If a player taps a nonbasic land for mana, it produces colorless mana instead of any other type";
    }

    private ManaReshaperReplacementEffect(final ManaReshaperReplacementEffect effect) {
        super(effect);
    }

    @Override
    public ManaReshaperReplacementEffect copy() {
        return new ManaReshaperReplacementEffect(this);
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        ManaEvent manaEvent = (ManaEvent) event;
        Mana mana = manaEvent.getMana();
        mana.setToMana(Mana.ColorlessMana(mana.count()));
        return false;
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.TAPPED_FOR_MANA;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        Permanent permanent = ((TappedForManaEvent) event).getPermanent();
        return permanent != null && permanent.isLand(game) && !permanent.isBasic(game);
    }
}
