package mage.cards.f;

import mage.abilities.Ability;
import mage.abilities.common.AsEntersBattlefieldAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.abilities.effects.common.cost.SpellsCostReductionControllerEffect;
import mage.abilities.mana.BlueManaAbility;
import mage.abilities.mana.WhiteManaAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.cards.CardsImpl;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.ColorlessPredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCardInHand;

import java.util.UUID;

/**
 * @author Ian
 */
public final class FaeCourtOfTuinvale extends CardImpl {

    private static final FilterCard filterSpells = new FilterCard("Faerie spells");

    static {
        filterSpells.add(SubType.FAERIE.getPredicate());
    }

    public FaeCourtOfTuinvale(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");

        this.supertype.add(SuperType.LEGENDARY);
        // As Fortified Beachhead enters the battlefield, you may reveal a Faerie card from your hand. Fortified Beachhead enters the battlefield tapped unless you revealed a Faerie card this way or you control a Faerie.
        this.addAbility(new AsEntersBattlefieldAbility(new FaeCourtOfTuinvaleEffect()));
        // {T}: Add {W} or {U}.;
        this.addAbility(new BlueManaAbility());


        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new SpellsCostReductionControllerEffect(filterSpells, 1)));
    }

    private FaeCourtOfTuinvale(final FaeCourtOfTuinvale card) {
        super(card);
    }

    @Override
    public FaeCourtOfTuinvale copy() {
        return new FaeCourtOfTuinvale(this);
    }
}

class FaeCourtOfTuinvaleEffect extends OneShotEffect {

    private static final FilterCard filter = new FilterCard("Faerie card from your hand");

    static {
        filter.add(SubType.FAERIE.getPredicate());
    }

    public FaeCourtOfTuinvaleEffect() {
        super(Outcome.Tap);
        this.staticText = "you may reveal a Faerie card from your hand. {this} enters the battlefield tapped unless you revealed a Faerie card this way or you control a Faerie.";
    }

    private FaeCourtOfTuinvaleEffect(final FaeCourtOfTuinvaleEffect effect) {
        super(effect);
    }

    @Override
    public FaeCourtOfTuinvaleEffect copy() {
        return new FaeCourtOfTuinvaleEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent land = game.getPermanentEntering(source.getSourceId());
        if (land == null) {
            return false;
        }
        if (revealFaerie(game, source)) {
            return true;
        }
        for (Permanent permanent : game.getBattlefield().getAllActivePermanents(source.getControllerId())) {
            if (permanent.hasSubtype(SubType.FAERIE, game)) {
                return true;
            }
        }
        // Intentional use of Permanent::setTapped, to not cause any trigger
        land.setTapped(true);
        return true;
    }

    private boolean revealFaerie(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null) {
            return false;
        }
        TargetCardInHand target = new TargetCardInHand(0, 1, filter);
        target.withChooseHint("to reveal");
        controller.chooseTarget(Outcome.Benefit, target, source, game);
        Card card = game.getCard(target.getFirstTarget());
        if (card == null) {
            return false;
        }
        controller.revealCards(source, new CardsImpl(card), game);
        return true;
    }
}
