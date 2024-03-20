package mage.cards.f;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.*;
import mage.abilities.keyword.FlashAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.common.FilterNonlandPermanent;
import mage.filter.predicate.mageobject.AnotherPredicate;
import mage.game.permanent.token.BirdToken;
import mage.game.permanent.token.TreasureToken;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetNonlandPermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class FerventTrailblazers extends CardImpl {

    private static final FilterNonlandPermanent filter = new FilterNonlandPermanent("another nonland permanent");

    static {
        filter.add(AnotherPredicate.instance);
    }

    public FerventTrailblazers(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{U}{R}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        this.addAbility(FlashAbility.getInstance());

        // When Aether Channeler enters the battlefield, choose one --
        // * Create a 1/1 white Bird creature token with flying.
        // * Return another target nonland permanent to its owner's hand.
        // * Draw a card.
        Ability ability = new EntersBattlefieldTriggeredAbility(new CreateTokenEffect(new TreasureToken()));
        Mode mode = new Mode(new DamageTargetEffect(1));
        mode.addTarget(new TargetAnyTarget());
        ability.addMode(mode);
        ability.addMode(new Mode(new DrawDiscardControllerEffect(1, 1, false)));
        ability.getModes().setMaxModes(2);
        ability.getModes().setMinModes(2);
        this.addAbility(ability);
    }

    private FerventTrailblazers(final FerventTrailblazers card) {
        super(card);
    }

    @Override
    public FerventTrailblazers copy() {
        return new FerventTrailblazers(this);
    }
}
