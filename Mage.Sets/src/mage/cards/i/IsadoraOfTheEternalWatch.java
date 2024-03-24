package mage.cards.i;

import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.cost.SpellsCostIncreasingAllEffect;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.predicate.Predicates;

import java.util.UUID;

/**
 * @author BetaSteward
 */
public final class IsadoraOfTheEternalWatch extends CardImpl {

    private static final FilterCard filter = new FilterCard("Noncreature spells");

    static {
        filter.add(Predicates.not(CardType.CREATURE.getPredicate()));
    }

    public IsadoraOfTheEternalWatch(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{W}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SOLDIER);

        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        this.addAbility(FirstStrikeAbility.getInstance());
        this.addAbility(VigilanceAbility.getInstance());

        // Noncreature spells cost {1} more to cast.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new SpellsCostIncreasingAllEffect(1, filter, TargetController.ANY)));

    }

    private IsadoraOfTheEternalWatch(final IsadoraOfTheEternalWatch card) {
        super(card);
    }

    @Override
    public IsadoraOfTheEternalWatch copy() {
        return new IsadoraOfTheEternalWatch(this);
    }
}