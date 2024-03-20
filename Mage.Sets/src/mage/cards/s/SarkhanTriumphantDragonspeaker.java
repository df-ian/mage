package mage.cards.s;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.condition.common.KickedCondition;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.decorator.ConditionalInterveningIfTriggeredAbility;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.abilities.keyword.KickerAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreatureCard;
import mage.filter.common.FilterLandCard;
import mage.filter.predicate.Predicates;
import mage.target.common.TargetCardInLibrary;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SarkhanTriumphantDragonspeaker extends CardImpl {

    private static final FilterCreatureCard filter = new FilterCreatureCard("Dragon creature card");

    static {
        filter.add(SubType.DRAGON.getPredicate());
    }

    public SarkhanTriumphantDragonspeaker(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{R}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SHAMAN);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        this.addAbility(new KickerAbility("{R}"));

        // When Sprouting Goblin enters the battlefield, if it was kicked, search your library for a land card with a basic land type, reveal it, put it into your hand, then shuffle.
        this.addAbility(new ConditionalInterveningIfTriggeredAbility(
                new EntersBattlefieldTriggeredAbility(new SearchLibraryPutInHandEffect(new TargetCardInLibrary(filter), true)),
                KickedCondition.ONCE,
                "When {this} enters, if it was kicked, search your library for a Dragon creature card, reveal it, put it into your hand, then shuffle."
        ));
    }

    private SarkhanTriumphantDragonspeaker(final SarkhanTriumphantDragonspeaker card) {
        super(card);
    }

    @Override
    public SarkhanTriumphantDragonspeaker copy() {
        return new SarkhanTriumphantDragonspeaker(this);
    }
}
