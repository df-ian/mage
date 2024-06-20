
package mage.cards.c;

import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.common.BecomesTappedAttachedTriggeredAbility;
import mage.abilities.costs.common.DiscardHandCost;
import mage.abilities.effects.common.AttachEffect;
import mage.abilities.effects.common.DestroyAttachedToEffect;
import mage.abilities.effects.common.LookLibraryAndPickControllerEffect;
import mage.abilities.keyword.EnchantAbility;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.Cards;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.PutCards;
import mage.constants.SubType;
import mage.game.Game;
import mage.players.Player;
import mage.target.TargetPermanent;
import mage.target.common.TargetLandPermanent;

import java.util.UUID;

/**
 *
 * @author jeffwadsworth
 */
public final class ConjuredCorruption extends CardImpl {

    public ConjuredCorruption(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{2}{B}");

        this.getSpellAbility().addEffect(new ConjuredCorruptionEffect());

        this.addAbility(new FlashbackAbility(this, new DiscardHandCost()));
    }

    private ConjuredCorruption(final ConjuredCorruption card) {
        super(card);
    }

    @Override
    public ConjuredCorruption copy() {
        return new ConjuredCorruption(this);
    }
}

class ConjuredCorruptionEffect extends LookLibraryAndPickControllerEffect {

    ConjuredCorruptionEffect() {
        super(5, Integer.MAX_VALUE, PutCards.HAND, PutCards.GRAVEYARD);
        this.optional = true;
    }

    private ConjuredCorruptionEffect(final ConjuredCorruptionEffect effect) {
        super(effect);
    }

    @Override
    public ConjuredCorruptionEffect copy() {
        return new ConjuredCorruptionEffect(this);
    }

    @Override
    protected boolean actionWithPickedCards(Game game, Ability source, Player player, Cards pickedCards, Cards otherCards) {
        super.actionWithPickedCards(game, source, player, pickedCards, otherCards);
        player.loseLife(pickedCards.size() * 2, game, source, false);
        return true;
    }

    @Override
    public String getText(Mode mode) {
        return super.getText(mode).concat(". You lose 2 life for each card you put into your hand this way");
    }
}