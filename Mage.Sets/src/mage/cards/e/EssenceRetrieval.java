package mage.cards.e;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.*;
import mage.abilities.condition.common.CardsInControllerGraveyardCondition;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.dynamicvalue.common.ArtifactYouControlCount;
import mage.abilities.effects.AsThoughEffectImpl;
import mage.abilities.effects.common.MillCardsControllerEffect;
import mage.abilities.effects.common.cost.SpellCostReductionForEachSourceEffect;
import mage.abilities.effects.common.counter.GetEnergyCountersControllerEffect;
import mage.abilities.hint.common.ArtifactYouControlHint;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.target.common.TargetCardInYourGraveyard;

import java.util.UUID;

/**
 * @author Ian
 */
public final class EssenceRetrieval extends CardImpl {

    public EssenceRetrieval(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{B}");

        this.addAbility(new EntersBattlefieldThisOrAnotherTriggeredAbility(
                new MillCardsControllerEffect(1),
                StaticFilters.FILTER_PERMANENT_NON_LAND, false, true
        ));


        // {T}: Choose target artifact card in your graveyard. You may cast that card this turn
        Ability ability = new ActivateIfConditionActivatedAbility(new EssenceRetrievalPlayEffect(), new SacrificeSourceCost(), new CardsInControllerGraveyardCondition(7));
        ability.addTarget(new TargetCardInYourGraveyard());
        ability.setAbilityWord(AbilityWord.THRESHOLD);
        this.addAbility(ability);
    }

    private EssenceRetrieval(final EssenceRetrieval card) {
        super(card);
    }

    @Override
    public EssenceRetrieval copy() {
        return new EssenceRetrieval(this);
    }
}

class EssenceRetrievalPlayEffect extends AsThoughEffectImpl {

    EssenceRetrievalPlayEffect() {
        super(AsThoughEffectType.CAST_FROM_NOT_OWN_HAND_ZONE, Duration.EndOfTurn, Outcome.Benefit);
        staticText = "Choose target artifact card in your graveyard. You may cast that card this turn.";
    }

    private EssenceRetrievalPlayEffect(final EssenceRetrievalPlayEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public EssenceRetrievalPlayEffect copy() {
        return new EssenceRetrievalPlayEffect(this);
    }

    @Override
    public boolean applies(UUID objectId, Ability source, UUID affectedControllerId, Game game) {
        UUID targetId = getTargetPointer().getFirst(game, source);
        if (targetId != null) {
            return targetId.equals(objectId)
                    && source.isControlledBy(affectedControllerId)
                    && Zone.GRAVEYARD == game.getState().getZone(objectId)
                    && !game.getCard(targetId).isLand(game);
        } else {
            // the target card has changed zone meanwhile, so the effect is no longer needed
            discard();
            return false;
        }
    }
}
