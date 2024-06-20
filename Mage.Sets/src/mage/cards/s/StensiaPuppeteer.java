
package mage.cards.s;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.delayed.AtTheBeginOfNextEndStepDelayedTriggeredAbility;
import mage.abilities.effects.ContinuousEffect;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HasteAbility;
import mage.abilities.keyword.PersistAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.Target;
import mage.target.common.TargetCardInGraveyard;
import mage.target.common.TargetCardInOpponentsGraveyard;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 * @author Ian
 */
public final class StensiaPuppeteer extends CardImpl {

    public StensiaPuppeteer(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}{B}");
        this.subtype.add(SubType.VAMPIRE);
        this.subtype.add(SubType.WIZARD);

        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // When Puppeteer Clique enters the battlefield, put target creature card from an opponent's graveyard onto the battlefield under your control. It gains haste. At the beginning of your next end step, exile it.
        Ability ability = new EntersBattlefieldTriggeredAbility(new StensiaPuppeteerEffect(), false);
        FilterCreatureCard card = new FilterCreatureCard("creature card with mana value 4 or less in a graveyard");
        card.add(new ManaValuePredicate(ComparisonType.OR_LESS, 4));
        Target target = new TargetCardInGraveyard(new FilterCreatureCard("creature card with mana value 4 or less in a graveyard"));
        ability.addTarget(target);
        this.addAbility(ability);
    }

    private StensiaPuppeteer(final StensiaPuppeteer card) {
        super(card);
    }

    @Override
    public StensiaPuppeteer copy() {
        return new StensiaPuppeteer(this);
    }
}

class StensiaPuppeteerEffect extends OneShotEffect {

    StensiaPuppeteerEffect() {
        super(Outcome.PutCreatureInPlay);
        staticText = "put target creature card with mana value 4 or less from a graveyard onto the battlefield under your control. It gains haste. At the beginning of your next end step, exile it";
    }

    private StensiaPuppeteerEffect(final StensiaPuppeteerEffect effect) {
        super(effect);
    }

    @Override
    public StensiaPuppeteerEffect copy() {
        return new StensiaPuppeteerEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Card card = game.getCard(getTargetPointer().getFirst(game, source));
        if (card == null) {
            return false;
        }
        Player controller = game.getPlayer(source.getControllerId());
        if (controller == null || !controller.moveCards(card, Zone.BATTLEFIELD, source, game)) {
            return false;
        }
        Permanent permanent = game.getPermanent(card.getId());
        if (permanent == null) {
            return false;
        }
        ContinuousEffect hasteEffect = new GainAbilityTargetEffect(HasteAbility.getInstance(), Duration.Custom);
        hasteEffect.setTargetPointer(new FixedTarget(permanent, game));
        game.addEffect(hasteEffect, source);
        ExileTargetEffect exileEffect = new ExileTargetEffect("exile " + permanent.getLogName());
        exileEffect.setTargetPointer(new FixedTarget(permanent, game));
        DelayedTriggeredAbility delayedAbility = new AtTheBeginOfNextEndStepDelayedTriggeredAbility(exileEffect, TargetController.YOU);
        game.addDelayedTriggeredAbility(delayedAbility, source);
        return true;
    }
}
