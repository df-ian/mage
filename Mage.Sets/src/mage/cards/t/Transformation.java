package mage.cards.t;

import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.costs.common.ExileFromHandCost;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.keyword.EvokeAbility;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.common.FilterNonlandPermanent;
import mage.filter.predicate.mageobject.AnotherPredicate;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.TargetPermanent;
import mage.target.common.TargetCardInHand;

import java.util.UUID;

/**
 * @author Ian
 */
public final class Transformation extends CardImpl {

    private static final FilterNonlandPermanent filter = new FilterNonlandPermanent("other nonland permanent");
    private static final FilterCard filter2 = new FilterCard("a white card from your hand");

    static {
        filter.add(AnotherPredicate.instance);
        filter2.add(new ColorPredicate(ObjectColor.WHITE));
    }

    public Transformation(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W}{W}");

        this.subtype.add(SubType.ELEMENTAL);
        this.subtype.add(SubType.INCARNATION);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // Flash
        this.addAbility(FlashAbility.getInstance());

        // When Solitude enters the battlefield, exile up to one other target creature. That creature's controller gains life equal to its power.
        Ability ability = new EntersBattlefieldTriggeredAbility(new ExileAndDrawTargetEffect()
                .setText("exile up to one other target nonland permanent. That permanent's controller draws a card."));
        ability.addTarget(new TargetPermanent(0, 1, filter));
        this.addAbility(ability);

        // Evoke—Exile a white card from your hand.
        this.addAbility(new EvokeAbility(new ExileFromHandCost(new TargetCardInHand(filter2))));
    }

    private Transformation(final Transformation card) {
        super(card);
    }

    @Override
    public Transformation copy() {
        return new Transformation(this);
    }
}

class ExileAndDrawTargetEffect extends OneShotEffect {

    public ExileAndDrawTargetEffect() {
        super(Outcome.Removal);
        staticText = "Exile target nonland permanent. Its controller draws a card.";
    }

    private ExileAndDrawTargetEffect(final ExileAndDrawTargetEffect effect) {
        super(effect);
    }

    @Override
    public ExileAndDrawTargetEffect copy() {
        return new ExileAndDrawTargetEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanentOrLKIBattlefield(getTargetPointer().getFirst(game, source));
        if (permanent == null) {
            return false;
        }
        Player controller = game.getPlayer(source.getControllerId());
        Player player = game.getPlayer(permanent.getControllerId());
        if (controller == null || player == null) {
            return true;
        }
        controller.moveCards(permanent, Zone.EXILED, source, game);
        player.drawCards(1, source, game);
        return true;
    }
}
