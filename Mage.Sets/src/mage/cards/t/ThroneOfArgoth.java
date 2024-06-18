package mage.cards.t;

import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTappedUnlessAbility;
import mage.abilities.common.EntersBattlefieldUntappedTriggeredAbility;
import mage.abilities.condition.common.YouControlPermanentCondition;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.MillCardsControllerEffect;
import mage.abilities.effects.common.PutOnLibraryTargetEffect;
import mage.abilities.mana.BlueManaAbility;
import mage.abilities.mana.GreenManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ComparisonType;
import mage.constants.SubType;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.common.FilterInstantOrSorceryCard;
import mage.filter.predicate.mageobject.AnotherPredicate;
import mage.game.permanent.token.BearToken;
import mage.target.common.TargetCardInYourGraveyard;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ThroneOfArgoth extends CardImpl {

    private static final YouControlPermanentCondition condition
            = new YouControlPermanentCondition(new FilterControlledCreaturePermanent());

    public ThroneOfArgoth(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");


        // ({T}: Add {U}.)
        this.addAbility(new GreenManaAbility());

        // Mystic Sanctuary enters the battlefield tapped unless you control three or more other Islands.
        this.addAbility(new EntersBattlefieldTappedUnlessAbility(condition).addHint(condition.getHint()));

        // When Mystic Sanctuary enters the battlefield untapped, you may put target instant or sorcery card from your graveyard on top of your library.
        Ability ability = new EntersBattlefieldUntappedTriggeredAbility(new CreateTokenEffect(new BearToken()), false);
        ability.addEffect(new MillCardsControllerEffect(3).concatBy(", then"));
        this.addAbility(ability);
    }

    private ThroneOfArgoth(final ThroneOfArgoth card) {
        super(card);
    }

    @Override
    public ThroneOfArgoth copy() {
        return new ThroneOfArgoth(this);
    }
}
