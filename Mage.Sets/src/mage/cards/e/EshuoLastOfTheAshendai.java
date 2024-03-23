package mage.cards.e;

import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.LoyaltyAbility;
import mage.abilities.PlayLandAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.common.delayed.ReflexiveTriggeredAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.mana.ManaCost;
import mage.abilities.costs.mana.ManaCosts;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.CardsInControllerGraveyardCount;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.*;
import mage.abilities.keyword.DeathtouchAbility;
import mage.abilities.mana.BlackManaAbility;
import mage.abilities.mana.GreenManaAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.counters.CounterType;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.common.FilterLandCard;
import mage.filter.common.FilterLandPermanent;
import mage.filter.predicate.mageobject.AnotherPredicate;
import mage.game.Game;
import mage.game.command.emblems.EshuoEmblem;
import mage.game.command.emblems.WrennAndSixEmblem;
import mage.game.permanent.Permanent;
import mage.game.permanent.token.BlackGreenWormToken;
import mage.game.permanent.token.IzoniInsectToken;
import mage.game.permanent.token.Token;
import mage.players.Player;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCreatureOrPlaneswalker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Ian
 */
public final class EshuoLastOfTheAshendai extends CardImpl {

    private static final DynamicValue xValue = new CardsInControllerGraveyardCount(StaticFilters.FILTER_CARD_CREATURES);

    public EshuoLastOfTheAshendai(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.PLANESWALKER}, "{1}{B}{G}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.ESHUO);
        this.setStartingLoyalty(3);

        // As long as Grist, the Hunger Tide isn't on the battlefield, it's a 1/1 Insect creature in addition to its other types.
        this.addAbility(new SimpleStaticAbility(Zone.ALL, new EshuoLastOfTheAshendaiTypeEffect()));
        ReflexiveTriggeredAbility ability = new ReflexiveTriggeredAbility(new MillCardsControllerEffect(2), false);
        ability.addEffect(new ReturnFromGraveyardToBattlefieldTargetEffect().concatBy(", then"));
        ability.addTarget(new TargetCardInGraveyard(1, 1, new FilterLandCard(), true));
        FilterLandPermanent filter = new FilterLandPermanent("another land");
        filter.add(AnotherPredicate.instance);
        this.addAbility(new LoyaltyAbility(new DoWhenCostPaid(ability, new SacrificeTargetCost(filter), null, false), 1));

        Token token = new BlackGreenWormToken();
        token.addAbility(DeathtouchAbility.getInstance());

        this.addAbility(new LoyaltyAbility(new CreateTokenEffect(token, 2, false), -3));


        this.addAbility(new LoyaltyAbility(new GetEmblemEffect(new EshuoEmblem()), -5));
    }

    private EshuoLastOfTheAshendai(final EshuoLastOfTheAshendai card) {
        super(card);
    }

    @Override
    public EshuoLastOfTheAshendai copy() {
        return new EshuoLastOfTheAshendai(this);
    }

    @Override
    public List<CardType> getCardTypeForDeckbuilding() {
        return Arrays.asList(CardType.PLANESWALKER, CardType.LAND);
    }

    public List<CardType> getCardType(Game game) {
        if (game == null) {
            return super.getCardType(null);
        }
        if (game.getState().getZone(this.objectId) == Zone.HAND) {
            return Arrays.asList(CardType.PLANESWALKER);
        }
        return Arrays.asList(CardType.LAND, CardType.PLANESWALKER);

    }

    @Override
    public ManaCosts<ManaCost> getManaCost() {
        return super.getManaCost();
    }

    @Override
    public boolean isLand(Game game) {
        if (game == null) {
            return super.isLand(null);
        }
        if (game.getState().getZone(this.objectId) != Zone.HAND) {
            return true;
        }
        return false;
    }
}

class EshuoLastOfTheAshendaiTypeEffect extends ContinuousEffectImpl {

    EshuoLastOfTheAshendaiTypeEffect() {
        super(Duration.Custom, Outcome.Benefit);
        staticText = "as long as {this} isn't wasn't in your hand, " +
                "it's a Forest Swamp land in addition to its other types.";
    }

    private EshuoLastOfTheAshendaiTypeEffect(final EshuoLastOfTheAshendaiTypeEffect effect) {
        super(effect);
    }

    @Override
    public EshuoLastOfTheAshendaiTypeEffect copy() {
        return new EshuoLastOfTheAshendaiTypeEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return false;
    }

    @Override
    public boolean apply(Layer layer, SubLayer sublayer, Ability source, Game game) {
        MageObject sourceObject = game.getCard(source.getSourceId());
        if (sourceObject == null) {
            return false;
        }
        if (game.getState().getZone(source.getSourceId()) == Zone.HAND || game.getState().getZone(source.getSourceId()) == Zone.STACK) {
            switch(layer) {
                case TypeChangingEffects_4:
//                    sourceObject.removeCardType(CardType.LAND);
                    sourceObject.removeSubType(game, SubType.SWAMP);
                    sourceObject.removeSubType(game, SubType.FOREST);
                    break;
            }
            return false;
        }
        switch (layer) {
            case TypeChangingEffects_4:
                sourceObject.addCardType(game, CardType.LAND);
                sourceObject.addSubType(game, SubType.SWAMP);
                sourceObject.addSubType(game, SubType.FOREST);
                break;
            case AbilityAddingRemovingEffects_6:
                Permanent p = game.getPermanent(source.getSourceId());
                if (p != null) {
                    p.addAbility(new GreenManaAbility(), source.getSourceId(), game);
                    p.addAbility(new BlackManaAbility(), source.getSourceId(), game);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean hasLayer(Layer layer) {
        return layer == Layer.TypeChangingEffects_4 || layer == Layer.AbilityAddingRemovingEffects_6;
    }

}
