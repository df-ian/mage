
package mage.cards.l;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.keyword.CantBeBlockedSourceAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.Target;
import mage.target.common.TargetCardInLibrary;
import mage.target.common.TargetControlledCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class LeoninStoneblade extends CardImpl {

    public LeoninStoneblade(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}{U}");
        this.subtype.add(SubType.CAT);
        this.subtype.add(SubType.WARRIOR);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // Vigilance
        this.addAbility(new CantBeBlockedSourceAbility());
        this.addAbility(new EntersBattlefieldTriggeredAbility(new LeoninStonebladeEffect()));

    }

    private LeoninStoneblade(final LeoninStoneblade card) {
        super(card);
    }

    @Override
    public LeoninStoneblade copy() {
        return new LeoninStoneblade(this);
    }
}

class LeoninStonebladeEffect extends OneShotEffect {

    LeoninStonebladeEffect() {
        super(Outcome.PutCardInPlay);
        this.staticText = "search your library for an Equipment card with mana value 2 or less, put it onto the battlefield, attach it to a creature you control, then shuffle";
    }

    private LeoninStonebladeEffect(final LeoninStonebladeEffect effect) {
        super(effect);
    }

    @Override
    public LeoninStonebladeEffect copy() {
        return new LeoninStonebladeEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null) {
            return false;
        }

        FilterCard filter = new FilterCard("Equipment with mana value 2 or less");
        filter.add(SubType.EQUIPMENT.getPredicate());
        filter.add(new ManaValuePredicate(ComparisonType.OR_LESS, 2));
        TargetCardInLibrary target = new TargetCardInLibrary(filter);
        if (controller.searchLibrary(target, source, game)) {
            Card card = controller.getLibrary().getCard(target.getFirstTarget(), game);
            if (card != null) {
                controller.moveCards(card, Zone.BATTLEFIELD, source, game);
                Permanent equipment = game.getPermanent(card.getId());
                Target targetCreature = new TargetControlledCreaturePermanent();
                targetCreature.withNotTarget(true);
                if (equipment != null && controller.choose(Outcome.BoostCreature, targetCreature, source, game)) {
                    Permanent permanent = game.getPermanent(targetCreature.getFirstTarget());
                    permanent.addAttachment(equipment.getId(), source, game);
                }
            }
        }
        controller.shuffleLibrary(source, game);
        return true;
    }
}
