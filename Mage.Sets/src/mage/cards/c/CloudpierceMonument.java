package mage.cards.c;

import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.AsEntersBattlefieldAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.ContinuousRuleModifyingEffectImpl;
import mage.abilities.effects.common.ChooseACardNameEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.ExileFromZoneTargetEffect;
import mage.abilities.mana.ActivatedManaAbilityImpl;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.target.common.TargetCardInOpponentsGraveyard;
import mage.util.CardUtil;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Ian
 */
public final class CloudpierceMonument extends CardImpl {

    public CloudpierceMonument(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{1}");

        // As Pithing Needle enters the battlefield, name a card.
        this.addAbility(new AsEntersBattlefieldAbility(new ChooseACardNameEffect(ChooseACardNameEffect.TypeOfName.ALL)));

        // Activated abilities of sources with the chosen name can't be activated unless they're mana abilities.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new CloudpierceMonumentEffect()));

        SimpleActivatedAbility ability = new SimpleActivatedAbility(new ExileFromZoneTargetEffect(Zone.GRAVEYARD, false), new ManaCostsImpl<>("{1}"));
        ability.addCost(new SacrificeSourceCost());
        ability.addTarget(new TargetCardInOpponentsGraveyard(0, 1, new FilterCard()));
        ability.addEffect(new DrawCardSourceControllerEffect(1));
    }

    private CloudpierceMonument(final CloudpierceMonument card) {
        super(card);
    }

    @Override
    public CloudpierceMonument copy() {
        return new CloudpierceMonument(this);
    }
}

class CloudpierceMonumentEffect extends ContinuousRuleModifyingEffectImpl {

    CloudpierceMonumentEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Detriment);
        staticText = "Activated abilities of sources with the chosen name can't be activated unless they're mana abilities";
    }

    private CloudpierceMonumentEffect(final CloudpierceMonumentEffect effect) {
        super(effect);
    }

    @Override
    public CloudpierceMonumentEffect copy() {
        return new CloudpierceMonumentEffect(this);
    }

    @Override
    public boolean checksEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ACTIVATE_ABILITY;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        MageObject object = game.getObject(event.getSourceId());
        String cardName = (String) game.getState().getValue(source.getSourceId().toString() + ChooseACardNameEffect.INFO_KEY);
        Optional<Ability> ability = game.getAbility(event.getTargetId(), event.getSourceId());
        if (ability.isPresent()
                && object != null) {
            return game.getState().getPlayersInRange(source.getControllerId(), game).contains(event.getPlayerId()) // controller in range
                    && !(ability.get() instanceof ActivatedManaAbilityImpl) // not an activated mana ability
                    && CardUtil.haveSameNames(object, cardName, game);
        }
        return false;
    }
}
