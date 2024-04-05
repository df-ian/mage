package mage.cards.t;

import mage.abilities.Ability;
import mage.abilities.common.ActivateIfConditionActivatedAbility;
import mage.abilities.common.BeginningOfUpkeepTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTappedAbility;
import mage.abilities.condition.Condition;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayEffect;
import mage.abilities.mana.ColorlessManaAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ComparisonType;
import mage.constants.TargetController;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterArtifactCard;
import mage.filter.common.FilterControlledLandPermanent;
import mage.filter.predicate.Predicate;
import mage.game.Game;
import mage.target.common.TargetCardInLibrary;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Ian
 */


public final class TorporSummit extends CardImpl {

    private static final FilterCard filter = new FilterArtifactCard("artifact card with mana cost {0} or {2}");

    static {
        filter.add(TorporSummitPredicate.instance);
    }

    public TorporSummit(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");

        this.addAbility(new EntersBattlefieldTappedAbility());

        // {T}: Add {C}.
        this.addAbility(new ColorlessManaAbility());

        Ability a = new BeginningOfUpkeepTriggeredAbility(Zone.BATTLEFIELD, new DoIfCostPaid(
                new SearchLibraryPutInPlayEffect(new TargetCardInLibrary(filter)), new SacrificeSourceCost()), TargetController.SOURCE_CONTROLLER, false,  false,"At the beginning of your upkeep,");
        this.addAbility(a);
    }

    private TorporSummit(final TorporSummit card) {
        super(card);
    }

    @Override
    public TorporSummit copy() {
        return new TorporSummit(this);
    }
}

enum TorporSummitPredicate implements Predicate<Card> {
    instance;

    private static final List<String> costs = Arrays.asList("{0}", "{2}");

    @Override
    public boolean apply(Card input, Game game) {
        return costs.contains(String.join("", input.getManaCostSymbols()));
    }
}