package mage.cards.e;

import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.OnEventTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.SacrificeSourceEffect;
import mage.abilities.effects.common.continuous.LoseAllAbilitiesAllEffect;
import mage.abilities.keyword.FlashAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterNonlandPermanent;
import mage.filter.predicate.Predicates;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class ExaltedSolitude extends CardImpl {

    public ExaltedSolitude(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{1}{W}");

        // Flash
        this.addAbility(FlashAbility.getInstance());

        // When Dress Down enters the battlefield, draw a card.
        this.addAbility(new EntersBattlefieldTriggeredAbility(new DrawCardSourceControllerEffect(1)));

        // Creatures lose all abilities.
        this.addAbility(new SimpleStaticAbility(new SetSupertypeAllEffect()));

        // At the beginning of the end step, sacrifice Dress Down.
        this.addAbility(new OnEventTriggeredAbility(
                GameEvent.EventType.END_TURN_STEP_PRE, "beginning of the end step",
                true, new SacrificeSourceEffect()
        ));
    }

    private ExaltedSolitude(final ExaltedSolitude card) {
        super(card);
    }

    @Override
    public ExaltedSolitude copy() {
        return new ExaltedSolitude(this);
    }
}

class SetSupertypeAllEffect extends ContinuousEffectImpl {

    private static final FilterPermanent filter = new FilterPermanent();

    static {
        filter.add(Predicates.not(SuperType.BASIC.getPredicate()));
    }

    public SetSupertypeAllEffect() {
        super(Duration.WhileOnBattlefield, Layer.TypeChangingEffects_4, SubLayer.NA, Outcome.Detriment);
        this.staticText = "All nonbasic permanents are legendary";
    }

    private SetSupertypeAllEffect(final SetSupertypeAllEffect effect) {
        super(effect);
    }

    @Override
    public SetSupertypeAllEffect copy() {
        return new SetSupertypeAllEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        for (Permanent permanent : game.getBattlefield().getActivePermanents(filter, source.getControllerId(), source, game)) {
            permanent.addSuperType(game, SuperType.LEGENDARY);
        }
        return true;
    }
}
