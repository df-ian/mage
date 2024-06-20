package mage.cards.e;

import mage.abilities.Ability;
import mage.abilities.SpellAbility;
import mage.abilities.common.SagaAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.ReturnFromGraveyardToBattlefieldTargetEffect;
import mage.abilities.effects.common.cost.CostModificationEffectImpl;
import mage.abilities.effects.common.counter.AddCountersAllEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.counters.Counter;
import mage.counters.CounterType;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetPermanent;
import mage.target.common.TargetCardInYourGraveyard;
import mage.util.CardUtil;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ElspethFellsTheCompleated extends CardImpl {

    private static final FilterPermanent filter
            = new FilterCreaturePermanent("creature an opponent controls with mana value 3 or greater");
    private static final FilterCard filter2
            = new FilterCard("creature card with mana value 3 or less from your graveyard");

    static {
        filter.add(TargetController.OPPONENT.getControllerPredicate());
        filter.add(new ManaValuePredicate(ComparisonType.MORE_THAN, 2));
        filter2.add(Predicates.or(
                CardType.CREATURE.getPredicate()
        ));
        filter2.add(new ManaValuePredicate(ComparisonType.OR_LESS, 3));
    }

    public ElspethFellsTheCompleated(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{1}{W}{W}");

        this.subtype.add(SubType.SAGA);

        // <i>(As this Saga enters and after your draw step, add a lore counter. Sacrifice after III.)</i>
        SagaAbility sagaAbility = new SagaAbility(this);

        // I - Exile target permanent an opponent controls with converted mana cost 3 or greater.
        sagaAbility.addChapterEffect(
                this, SagaChapter.CHAPTER_I, SagaChapter.CHAPTER_I,
                new ExileTargetEffect(), new TargetPermanent(filter)
        );

        // III - Return target creature or planeswalker card from your graveyard to the battlefield. Put a +1/+1 counter or a loyalty counter on it.
        sagaAbility.addChapterEffect(
                this, SagaChapter.CHAPTER_II, SagaChapter.CHAPTER_II,
                new ReturnFromGraveyardToBattlefieldTargetEffect(), new TargetCardInYourGraveyard(filter2)
        );

        sagaAbility.addChapterEffect(this, SagaChapter.CHAPTER_III, SagaChapter.CHAPTER_IV, new AddCountersAllEffect(CounterType.P1P1.createInstance(1), StaticFilters.FILTER_CONTROLLED_CREATURE));
        this.addAbility(sagaAbility);
    }

    private ElspethFellsTheCompleated(final ElspethFellsTheCompleated card) {
        super(card);
    }

    @Override
    public ElspethFellsTheCompleated copy() {
        return new ElspethFellsTheCompleated(this);
    }
}
