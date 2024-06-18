package mage.cards.k;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.*;
import mage.abilities.effects.common.discard.DiscardTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.StaticFilters;
import mage.filter.common.FilterNonlandPermanent;
import mage.filter.predicate.mageobject.AnotherPredicate;
import mage.game.permanent.token.BirdToken;
import mage.target.TargetPermanent;
import mage.target.TargetPlayer;
import mage.target.common.TargetCardInYourGraveyard;
import mage.target.common.TargetNonlandPermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class KolaghanThunderousRegent extends CardImpl {


    public KolaghanThunderousRegent(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{B}{R}");

        this.supertype.add(SuperType.LEGENDARY);

        this.subtype.add(SubType.DRAGON);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        Ability ability = new EntersBattlefieldTriggeredAbility(new ReturnFromGraveyardToHandTargetEffect());
        ability.addTarget(new TargetCardInYourGraveyard(1, StaticFilters.FILTER_CARD_CREATURE_YOUR_GRAVEYARD));

        // or Target player discards a card;
        Mode mode = new Mode(new DiscardTargetEffect(1));
        mode.addTarget(new TargetPlayer());
        ability.addMode(mode);

        // or Destroy target artifact;
        mode = new Mode(new DestroyTargetEffect());
        mode.addTarget(new TargetPermanent(StaticFilters.FILTER_PERMANENT_ARTIFACT));
        ability.addMode(mode);
        this.addAbility(ability);
    }

    private KolaghanThunderousRegent(final KolaghanThunderousRegent card) {
        super(card);
    }

    @Override
    public KolaghanThunderousRegent copy() {
        return new KolaghanThunderousRegent(this);
    }
}
