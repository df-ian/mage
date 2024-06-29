package mage.cards.e;

import mage.MageObjectReference;
import mage.abilities.Ability;
import mage.abilities.common.CantBeCounteredSourceAbility;
import mage.abilities.effects.AsThoughEffectImpl;
import mage.abilities.effects.AsThoughManaEffect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.keyword.DevoidAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.common.FilterSpellOrPermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.PermanentPredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.ManaPoolItem;
import mage.target.common.TargetNonlandPermanent;
import mage.target.common.TargetSpellOrPermanent;
import mage.util.CardUtil;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Ian
 */
public final class ElegantAppropriation extends CardImpl {

    public ElegantAppropriation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{2}{W}{U}");

        this.addAbility(new CantBeCounteredSourceAbility());
        FilterSpellOrPermanent filter = new FilterSpellOrPermanent("permanent spell or nonland permanent");
        filter.add(PermanentPredicate.instance);
        filter.add(Predicates.not(CardType.LAND.getPredicate()));
        // Exile target permanent spell or nonland permanent. You may cast that card for as long as it remains exiled, and you may spend colorless mana as though it were mana of any color to cast that spell.
        this.getSpellAbility().addTarget(new TargetSpellOrPermanent(filter));
        this.getSpellAbility().addEffect(new ElegantAppropriationEffect());
    }

    private ElegantAppropriation(final ElegantAppropriation card) {
        super(card);
    }

    @Override
    public ElegantAppropriation copy() {
        return new ElegantAppropriation(this);
    }
}

class ElegantAppropriationEffect extends OneShotEffect {

    ElegantAppropriationEffect() {
        super(Outcome.Exile);
        staticText = "Exile target permanent spell or nonland permanent. You may cast that card for as long as it remains exiled, "
                + "and you may spend mana as though it were mana of any color to cast that spell";
    }

    private ElegantAppropriationEffect(final ElegantAppropriationEffect effect) {
        super(effect);
    }

    @Override
    public ElegantAppropriationEffect copy() {
        return new ElegantAppropriationEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Card permanent = game.getCard(getTargetPointer().getFirst(game, source));
        if (permanent == null) {
            return false;
        }
        new ExileTargetEffect()
                .setToSourceExileZone(true)
                .setTargetPointer(getTargetPointer().copy())
                .apply(game, source);
        game.processAction();
        Card card = game.getCard(getTargetPointer().getFirst(game, source));
        if (card == null || !game.getState().getZone(card.getId()).equals(Zone.EXILED)) {
            return true;
        }
        card = card.getMainCard(); // for mdfc
        CardUtil.makeCardPlayable(game, source, card, true, Duration.Custom, true);
        return true;
    }
}
