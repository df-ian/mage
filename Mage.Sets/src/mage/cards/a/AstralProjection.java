package mage.cards.a;

import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateDelayedTriggeredAbilityEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.Predicates;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class AstralProjection extends CardImpl {

    public AstralProjection(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{U}");
        

        //Whenever a creature you control deals combat damage to a player this turn, draw two cards. This ability triggers only once.
        this.getSpellAbility().addEffect(new CreateDelayedTriggeredAbilityEffect(new AstralProjectionTriggeredAbility()));

        // Flashback {2}{G}{W}
        this.addAbility(new FlashbackAbility(this, new ManaCostsImpl<>("{2}{U}")));

    }

    private AstralProjection(final AstralProjection card) {
        super(card);
    }

    @Override
    public AstralProjection copy() {
        return new AstralProjection(this);
    }
}

class AstralProjectionTriggeredAbility extends DelayedTriggeredAbility {

    private static final FilterControlledPermanent filter = new FilterControlledCreaturePermanent();

    public AstralProjectionTriggeredAbility() {
        super(new DrawCardSourceControllerEffect(2), Duration.EndOfTurn, true);
        optional = false;
    }

    private AstralProjectionTriggeredAbility(final AstralProjectionTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.DAMAGED_PLAYER && event.getFlag() == true;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        UUID sourceId = event.getSourceId();
        Permanent permanent = game.getPermanent(sourceId);
        game.debugMessage("Checking trigger");
        return filter.match(permanent, getControllerId(), this, game) && game.isOpponent(game.getPlayer(permanent.getControllerId()), event.getTargetId());
    }

    @Override
    public AstralProjectionTriggeredAbility copy() {
        return new AstralProjectionTriggeredAbility(this);
    }

    @Override
    public String getRule() {
        return "Whenever a creature deals combat damage to a player this turn, draw two cards. This ability triggers only once.";
    }
}