package mage.cards.l;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldOrAttacksSourceTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.abilities.effects.ContinuousRuleModifyingEffectImpl;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.LoseLifeTargetEffect;
import mage.abilities.effects.common.MillCardsTargetEffect;
import mage.abilities.hint.Hint;
import mage.abilities.hint.ValueHint;
import mage.abilities.keyword.FlashbackAbility;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterControlledPermanent;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.token.ZombieWizardToken;
import mage.players.Player;
import mage.target.TargetPlayer;

import java.util.UUID;

/**
 * @author Ian
 */
public final class LoxodonNaturalist extends CardImpl {

    public LoxodonNaturalist(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{G}");
        this.subtype.add(SubType.ELEPHANT);
        this.subtype.add(SubType.CLERIC);

        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        this.addAbility(VigilanceAbility.getInstance());
        this.addAbility(TrampleAbility.getInstance());
        
        this.addAbility(new SimpleStaticAbility(new LoxodonNaturalistCounteredEffect()));
        this.addAbility(new EntersBattlefieldOrAttacksSourceTriggeredAbility(new GainLifeEffect(3)));
    }

    private LoxodonNaturalist(final LoxodonNaturalist card) {
        super(card);
    }

    @Override
    public LoxodonNaturalist copy() {
        return new LoxodonNaturalist(this);
    }
}


class LoxodonNaturalistCounteredEffect extends ContinuousRuleModifyingEffectImpl {

    LoxodonNaturalistCounteredEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Benefit);
        staticText = "spells can't be countered";
    }

    private LoxodonNaturalistCounteredEffect(final LoxodonNaturalistCounteredEffect effect) {
        super(effect);
    }

    @Override
    public LoxodonNaturalistCounteredEffect copy() {
        return new LoxodonNaturalistCounteredEffect(this);
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.COUNTER;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        return game.getSpell(event.getTargetId()) != null;
    }
}