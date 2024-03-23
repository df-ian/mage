package mage.cards.m;

import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.CastOnlyIfConditionIsTrueAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.InvertCondition;
import mage.abilities.condition.common.PlayLandCondition;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.ContinuousRuleModifyingEffectImpl;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.effects.mana.AddManaOfAnyColorEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.watchers.common.AbilityResolvedWatcher;
import mage.watchers.common.PlayLandWatcher;
import mage.watchers.common.SpellsCastWatcher;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class MoxBismuth extends CardImpl {

    public MoxBismuth(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{0}");

        this.supertype.add(SuperType.LEGENDARY);

        Ability subability = new SimpleActivatedAbility(new AddManaOfAnyColorEffect(), new TapSourceCost());
        subability.addEffect(new MoxBismuthEffect());
        Ability a = new SimpleStaticAbility(new ConditionalContinuousEffect(
                new GainAbilitySourceEffect(subability), new InvertCondition(PlayLandCondition.instance),
                "As long as you haven't played a land this turn, {this} has \"{T}: Add one mana of any color. You can't play lands this turn\""));
        a.addWatcher(new PlayLandWatcher());
        this.addAbility(a);
    }

    private MoxBismuth(final MoxBismuth card) {
        super(card);
    }

    @Override
    public MoxBismuth copy() {
        return new MoxBismuth(this);
    }
}

class MoxBismuthEffect extends ContinuousRuleModifyingEffectImpl {

    MoxBismuthEffect() {
        super(Duration.EndOfTurn, Outcome.Detriment);
        staticText = "You can't play lands this turn.";
    }

    private MoxBismuthEffect(final MoxBismuthEffect effect) {
        super(effect);
    }

    @Override
    public MoxBismuthEffect copy() {
        return new MoxBismuthEffect(this);
    }

    @Override
    public String getInfoMessage(Ability source, GameEvent event, Game game) {
        MageObject mageObject = game.getObject(source);
        if (mageObject != null) {
            return "You can't play lands this turn (" + mageObject.getIdName() + ").";
        }
        return null;
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.PLAY_LAND;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        return event.getPlayerId().equals(source.getControllerId());
    }
}
