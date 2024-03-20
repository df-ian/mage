package mage.cards.m;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldControlledTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.effects.common.cost.SpellsCostReductionControllerEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.Cards;
import mage.cards.CardsImpl;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreatureCard;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;

import java.util.UUID;

/**
 * @author Ian
 */
public final class MidnightBeastcaller extends CardImpl {

    private static final FilterCard filter = new FilterCreatureCard("creature spells");

    public MidnightBeastcaller(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{G}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SHAMAN);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        this.addAbility(new EntersBattlefieldTriggeredAbility(new MidnightBeastcallerEffect()));
        
        // Creature spells you cast cost {1} less to cast.
        this.addAbility(new SimpleStaticAbility(new SpellsCostReductionControllerEffect(filter, 1)));
    }

    private MidnightBeastcaller(final MidnightBeastcaller card) {
        super(card);
    }

    @Override
    public MidnightBeastcaller copy() {
        return new MidnightBeastcaller(this);
    }
}


class MidnightBeastcallerEffect extends OneShotEffect {

    MidnightBeastcallerEffect() {
        super(Outcome.Benefit);
        staticText = "reveal the top three cards of your library. Put all creature cards revealled this way into your hand and the rest into your graveyard.";
    }

    private MidnightBeastcallerEffect(final MidnightBeastcallerEffect effect) {
        super(effect);
    }

    @Override
    public MidnightBeastcallerEffect copy() {
        return new MidnightBeastcallerEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        if (player == null) {
            return false;
        }
        FilterCard filter = (StaticFilters.FILTER_CARD_CREATURE_A);
        Cards cards = new CardsImpl(player.getLibrary().getTopCards(game, 3));
        player.revealCards(source, cards, game);
        Cards cardsToKeep = new CardsImpl(cards.getCards(filter, game));
        cards.removeAll(cardsToKeep);
        player.moveCards(cardsToKeep, Zone.HAND, source, game);
        player.moveCards(cards, Zone.GRAVEYARD, source, game);
        return true;
    }
}
