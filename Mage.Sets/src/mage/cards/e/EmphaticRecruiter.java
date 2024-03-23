
package mage.cards.e;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.abilities.keyword.MentorAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ComparisonType;
import mage.constants.SubType;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.mageobject.PowerPredicate;
import mage.filter.predicate.mageobject.ToughnessPredicate;
import mage.target.common.TargetCardInLibrary;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class EmphaticRecruiter extends CardImpl {

    private static final FilterCreatureCard filter = new FilterCreatureCard("a creature card with power 1 or less");
    static {
        filter.add(new PowerPredicate(ComparisonType.FEWER_THAN, 3));
    }

    public EmphaticRecruiter(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{W}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SOLDIER);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        this.addAbility(new MentorAbility());

        this.addAbility(new EntersBattlefieldTriggeredAbility(new SearchLibraryPutInHandEffect(
                new TargetCardInLibrary(filter), true), false));
    }

    private EmphaticRecruiter(final EmphaticRecruiter card) {
        super(card);
    }

    @Override
    public EmphaticRecruiter copy() {
        return new EmphaticRecruiter(this);
    }
}
