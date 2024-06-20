package mage.cards.f;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.OneShotEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.common.FilterArtifactCard;
import mage.filter.common.FilterEnchantmentCard;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetArtifactPermanent;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetEnchantmentPermanent;
import mage.target.targetpointer.EachTargetPointer;

import java.util.UUID;

/**
 * @author Ian
 */
public final class Fateweaver extends CardImpl {

    public Fateweaver(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{G}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);

        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        // {tap}: Choose target artifact a player controls and target artifact card in that player's graveyard. If both targets are still legal as this ability resolves, that player simultaneously sacrifices the artifact and returns the artifact card to the battlefield.
        Ability ability = new SimpleActivatedAbility(new FateweaverEffect(), new TapSourceCost());
        ability.addTarget(new TargetEnchantmentPermanent());
        ability.addTarget(new FateweaverTarget());
        this.addAbility(ability);
    }

    private Fateweaver(final Fateweaver card) {
        super(card);
    }

    @Override
    public Fateweaver copy() {
        return new Fateweaver(this);
    }

    private static class FateweaverEffect extends OneShotEffect {

        public FateweaverEffect() {
            super(Outcome.PutCardInPlay);
            staticText = "Choose target enchantment a player controls "
                    + "and target enchantment card in that player's graveyard. "
                    + "If both targets are still legal as this ability resolves, "
                    + "that player simultaneously sacrifices the enchantment "
                    + "and returns the enchantment card to the battlefield";
            this.setTargetPointer(new EachTargetPointer());
        }

        private FateweaverEffect(final FateweaverEffect effect) {
            super(effect);
        }

        @Override
        public boolean apply(Game game, Ability source) {
            if (getTargetPointer().getTargets(game, source).size() < 2) {
                return false;
            }
            Permanent artifact = game.getPermanent(getTargetPointer().getFirst(game, source));
            Card card = game.getCard(getTargetPointer().getTargets(game, source).get(1));
            Player controller = game.getPlayer(source.getControllerId());
            if (artifact == null
                    || card == null
                    || controller == null) {
                return false;
            }
            Player owner = game.getPlayer(card.getOwnerId());
            if (owner == null) {
                return false;
            }
            boolean sacrifice = artifact.sacrifice(source, game);
            boolean putOnBF = owner.moveCards(card, Zone.BATTLEFIELD, source, game);
            if (sacrifice || putOnBF) {
                return true;
            }
            return false;
        }

        @Override
        public FateweaverEffect copy() {
            return new FateweaverEffect(this);
        }

    }

    private static class FateweaverTarget extends TargetCardInGraveyard {

        public FateweaverTarget() {
            super(new FilterEnchantmentCard());
            targetName = "target enchantment card in that player's graveyard";
        }

        private FateweaverTarget(final FateweaverTarget target) {
            super(target);
        }

        @Override
        public boolean canTarget(UUID id, Ability source, Game game) {
            Permanent permanent = game.getPermanent(source.getFirstTarget());
            if (permanent == null) {
                return false;
            }
            Card card = game.getCard(id);
            return card != null
                    && card.isEnchantment(game)
                    && card.isOwnedBy(permanent.getControllerId());
        }

        @Override
        public FateweaverTarget copy() {
            return new FateweaverTarget(this);
        }
    }
}
