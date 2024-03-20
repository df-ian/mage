package mage.cards.a;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.DiscardCardCost;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.effects.AsThoughEffectImpl;
import mage.abilities.keyword.BlitzAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.game.Game;

import java.util.UUID;

/**
 * @author Ian
 */
public final class AshenPhoenix extends CardImpl {

    public AshenPhoenix(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{R}");

        this.subtype.add(SubType.PHOENIX);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        this.addAbility(FlyingAbility.getInstance());

        // Blitz—{2}{B}{B}, Pay 2 life.
        Ability ability = new BlitzAbility(this, "{1}{R}{R}");
        ability.addCost(new DiscardCardCost());
        this.addAbility(ability);

        // You may cast Tenacious Underdog from your graveyard using its blitz ability.
        this.addAbility(new SimpleStaticAbility(Zone.ALL, new AshenPhoenixEffect()));
    }

    private AshenPhoenix(final AshenPhoenix card) {
        super(card);
    }

    @Override
    public AshenPhoenix copy() {
        return new AshenPhoenix(this);
    }
}

class AshenPhoenixEffect extends AsThoughEffectImpl {

    AshenPhoenixEffect() {
        super(AsThoughEffectType.PLAY_FROM_NOT_OWN_HAND_ZONE, Duration.EndOfGame, Outcome.PutCreatureInPlay);
        staticText = "You may cast {this} from your graveyard using its blitz ability";
    }

    private AshenPhoenixEffect(final AshenPhoenixEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public AshenPhoenixEffect copy() {
        return new AshenPhoenixEffect(this);
    }

    @Override
    public boolean applies(UUID objectId, Ability affectedAbility, Ability source, Game game, UUID playerId) {
        return objectId.equals(source.getSourceId())
                && source.isControlledBy(playerId)
                && affectedAbility instanceof BlitzAbility
                && game.getState().getZone(source.getSourceId()) == Zone.GRAVEYARD
                && game.getCard(source.getSourceId()) != null;
    }

    @Override
    public boolean applies(UUID sourceId, Ability source, UUID affectedControllerId, Game game) {
        return false;
    }
}
