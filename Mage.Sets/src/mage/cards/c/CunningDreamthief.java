package mage.cards.c;

import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.DealsCombatDamageToAPlayerTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.ExileFaceDownYouMayPlayAsLongAsExiledTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.*;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.game.Game;
import mage.players.Player;
import mage.target.TargetCard;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 * @author Ian
 */
public final class CunningDreamthief extends CardImpl {

    public CunningDreamthief(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{B}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ROGUE);
        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Whenever Thief of Sanity deals combat damage to a player, look at the top three cards of that player's library, exile one of them face down, then put the rest into their graveyard. For as long as that card remains exiled, you may look at it, you may cast it, and you may spend mana as though it were mana of any type to cast it.
        this.addAbility(new DealsCombatDamageToAPlayerTriggeredAbility(new CunningDreamthiefEffect(), false, true));
    }

    private CunningDreamthief(final CunningDreamthief card) {
        super(card);
    }

    @Override
    public CunningDreamthief copy() {
        return new CunningDreamthief(this);
    }
}

class CunningDreamthiefEffect extends OneShotEffect {

    CunningDreamthiefEffect() {
        super(Outcome.Benefit);
        this.staticText = "exile the top card of their library face down."
                + "You may cast that card for as long as it remains exiled, and mana of any type can be spent to cast it";
    }

    private CunningDreamthiefEffect(final CunningDreamthiefEffect effect) {
        super(effect);
    }

    @Override
    public CunningDreamthiefEffect copy() {
        return new CunningDreamthiefEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        Player damagedPlayer = game.getPlayer(getTargetPointer().getFirst(game, source));
        MageObject sourceObject = source.getSourceObject(game);
        if (controller == null || damagedPlayer == null || sourceObject == null) {
            return false;
        }
        Cards topCards = new CardsImpl();
        topCards.addAllCards(damagedPlayer.getLibrary().getTopCards(game, 1));
        TargetCard target = new TargetCard(Zone.LIBRARY, new FilterCard("card to exile face down"));
        controller.choose(outcome, topCards, target, source, game);
        Card card = game.getCard(target.getFirstTarget());
        if (card == null) {
            controller.moveCards(topCards, Zone.GRAVEYARD, source, game);
            return true;
        }
        new ExileFaceDownYouMayPlayAsLongAsExiledTargetEffect(true, CastManaAdjustment.AS_THOUGH_ANY_MANA_TYPE)
                .setTargetPointer(new FixedTarget(card, game))
                .apply(game, source);
        topCards.retainZone(Zone.LIBRARY, game);
        // put the rest into their graveyard
        controller.moveCards(topCards, Zone.GRAVEYARD, source, game);
        return true;
    }
}