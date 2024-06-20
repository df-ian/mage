package mage.cards.t;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksWithCreaturesTriggeredAbility;
import mage.abilities.common.delayed.ReflexiveTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.Cards;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.counters.CounterType;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.game.Game;
import mage.players.Player;
import mage.target.TargetPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class TalionsWispweaver extends CardImpl {

    private static final FilterPermanent filter = new FilterCreaturePermanent(SubType.FAERIE, "Faeries");

    public TalionsWispweaver(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{U}");

        this.subtype.add(SubType.FAERIE);
        this.subtype.add(SubType.WIZARD);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Whenever you attack with one or more Faeries, draw a card, then discard a card. When you discard a card this way, put a +1/+1 counter on target Faerie you control.
        this.addAbility(new AttacksWithCreaturesTriggeredAbility(
                new TalionsWispweaverEffect(), 1, filter
        ));
    }

    private TalionsWispweaver(final TalionsWispweaver card) {
        super(card);
    }

    @Override
    public TalionsWispweaver copy() {
        return new TalionsWispweaver(this);
    }
}

class TalionsWispweaverEffect extends OneShotEffect {

    private static final FilterPermanent filter = new FilterPermanent(SubType.FAERIE, "Faerie you control");

    TalionsWispweaverEffect() {
        super(Outcome.Benefit);
        staticText = "draw two cards, then discard a card. When you discard a card this way, put a +1/+1 counter on target Faerie you control";
    }

    private TalionsWispweaverEffect(final TalionsWispweaverEffect effect) {
        super(effect);
    }

    @Override
    public TalionsWispweaverEffect copy() {
        return new TalionsWispweaverEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null) {
            return false;
        }
        controller.drawCards(2, source, game);
        Cards discard = controller.discard(1, false, false, source, game);
        if (!discard.isEmpty()) {
            ReflexiveTriggeredAbility reflexive = new ReflexiveTriggeredAbility(
                    new AddCountersTargetEffect(CounterType.P1P1.createInstance()),
                    false
            );
            reflexive.addTarget(new TargetPermanent(filter));
            game.fireReflexiveTriggeredAbility(reflexive, source);
        }
        return true;
    }

}