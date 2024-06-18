package mage.cards.s;

import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.AsEntersBattlefieldAbility;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.TargetPermanentPowerCount;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.LoseLifeOpponentsEffect;
import mage.abilities.effects.common.ReturnFromGraveyardToBattlefieldTargetEffect;
import mage.abilities.effects.common.TapSourceUnlessPaysEffect;
import mage.abilities.mana.BlackManaAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.ModalDoubleFacedCard;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.FilterCard;
import mage.filter.common.FilterCreatureCard;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.target.common.TargetCardInYourGraveyard;
import mage.target.targetadjustment.TargetAdjuster;
import mage.target.targetadjustment.TargetsCountAdjuster;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Ian
 */
public final class StrixhavenFalls extends CardImpl {

    public StrixhavenFalls(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo,
                new CardType[]{CardType.SORCERY}, "{B}{B/R/P}{R}"
        );

        // 1.
        // Agadeem's Awakening
        // Sorcery

        // Return from your graveyard to the battlefield any number of target creature cards that each have a different converted mana cost X or less.
        this.getSpellAbility().addEffect(new DestroyTargetEffect().setText(
                "Destroy any number of target creatures with the same mana value"
        ));
        this.getSpellAbility().setTargetAdjuster(StrixhavenFallsAdjuster.instance);

        this.getSpellAbility().addEffect(new LoseLifeOpponentsEffect(TargetCounter.instance).setText("Each opponent loses that much life."));

    }

    private StrixhavenFalls(final StrixhavenFalls card) {
        super(card);
    }

    @Override
    public StrixhavenFalls copy() {
        return new StrixhavenFalls(this);
    }
}

enum StrixhavenFallsAdjuster implements TargetAdjuster {
    instance;

    @Override
    public void adjustTargets(Ability ability, Game game) {
        ability.getTargets().clear();
        ability.addTarget(new StrixhavenFallsTarget());
    }
}

class StrixhavenFallsTarget extends TargetCardInYourGraveyard {

    private static final FilterCard filter
            = new FilterCreatureCard("with the same mana value");

    StrixhavenFallsTarget() {
        super(0, Integer.MAX_VALUE, filter, true);
    }

    private StrixhavenFallsTarget(final StrixhavenFallsTarget target) {
        super(target);
    }

    @Override
    public StrixhavenFallsTarget copy() {
        return new StrixhavenFallsTarget(this);
    }

    @Override
    public Set<UUID> possibleTargets(UUID sourceControllerId, Ability source, Game game) {
        Set<UUID> possibleTargets = super.possibleTargets(sourceControllerId, source, game);
        Set<Integer> cmcs = this.getTargets()
                .stream()
                .map(game::getCard)
                .map(MageObject::getManaValue)
                .collect(Collectors.toSet());
        possibleTargets.removeIf(uuid -> {
            Card card = game.getCard(uuid);
            return card != null && !cmcs.contains(card.getManaValue()) && cmcs.size() > 0;
        });
        return possibleTargets;
    }
}

enum TargetCounter implements DynamicValue {
    instance;

    @Override
    public int calculate(Game game, Ability sourceAbility, Effect effect) {
        return sourceAbility.getTargets().size();
    }

    @Override
    public TargetCounter copy() {
        return TargetCounter.instance;
    }

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public String getMessage() {
        return "the number of creatures";
    }
}