package mage.cards.w;

import mage.abilities.Mode;
import mage.abilities.costs.Cost;
import mage.abilities.costs.common.DiscardCardCost;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.LoseLifeTargetEffect;
import mage.abilities.effects.common.continuous.BoostTargetEffect;
import mage.abilities.effects.common.discard.DiscardCardYouChooseTargetEffect;
import mage.abilities.keyword.EscalateAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.predicate.Predicates;
import mage.target.TargetPermanent;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCreaturePermanent;
import mage.target.common.TargetOpponent;
import mage.target.common.TargetOpponentOrPlaneswalker;

import java.util.UUID;

/**
 * @author Ian
 */
public final class WorldToAshes extends CardImpl {


    public WorldToAshes(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{1}{R}{R}");

        // Escalate - Discard a card.
        Cost cost = new SacrificeTargetCost(StaticFilters.FILTER_PERMANENT);
        cost.setText("&mdash; Sacrifice a permanent");
        this.addAbility(new EscalateAbility(cost));

        // Choose one or more &mdash;
        this.getSpellAbility().getModes().setMinModes(1);
        this.getSpellAbility().getModes().setMaxModes(3);

        this.getSpellAbility().addEffect(new DestroyTargetEffect("land", false));
        this.getSpellAbility().addTarget(new TargetPermanent(StaticFilters.FILTER_LAND));

        Mode mode = new Mode(new DamageTargetEffect(5));
        mode.addTarget(new TargetCreaturePermanent());
        this.getSpellAbility().addMode(mode);

        mode = new Mode(new DamageTargetEffect(3));
        mode.addTarget(new TargetOpponentOrPlaneswalker());
        this.getSpellAbility().addMode(mode);
    }

    private WorldToAshes(final WorldToAshes card) {
        super(card);
    }

    @Override
    public WorldToAshes copy() {
        return new WorldToAshes(this);
    }
}
