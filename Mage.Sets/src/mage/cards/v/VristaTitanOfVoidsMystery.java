package mage.cards.v;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldOrAttacksSourceTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.effects.common.LoseLifeSourceControllerEffect;
import mage.abilities.effects.common.PutCardFromHandOntoBattlefieldEffect;
import mage.abilities.keyword.EscapeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.Permanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class VristaTitanOfVoidsMystery extends CardImpl {

    public VristaTitanOfVoidsMystery(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{U}{B}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELDER);
        this.subtype.add(SubType.GIANT);
        this.power = new MageInt(6);
        this.toughness = new MageInt(6);

        // When Uro enters the battlefield, sacrifice it unless it escaped.
        this.addAbility(new EntersBattlefieldTriggeredAbility(new VristaTitanOfVoidsMysteryEffect()));

        // Whenever Uro enters the battlefield or attacks, you gain 3 life and draw a card, then you may put a land card from your hand onto the battlefield.
        Ability ability = new EntersBattlefieldOrAttacksSourceTriggeredAbility(new LoseLifeSourceControllerEffect(2));
        ability.addEffect(new DrawCardSourceControllerEffect(2).concatBy("and"));
        this.addAbility(ability);

        // Escape-{G}{G}{U}{U}, Exile five other cards from your graveyard.
        this.addAbility(new EscapeAbility(this, "{U}{U}{B}{B}", 5));
    }

    private VristaTitanOfVoidsMystery(final VristaTitanOfVoidsMystery card) {
        super(card);
    }

    @Override
    public VristaTitanOfVoidsMystery copy() {
        return new VristaTitanOfVoidsMystery(this);
    }
}

class VristaTitanOfVoidsMysteryEffect extends OneShotEffect {

    VristaTitanOfVoidsMysteryEffect() {
        super(Outcome.Sacrifice);
        staticText = "sacrifice it unless it escaped";
    }

    private VristaTitanOfVoidsMysteryEffect(final VristaTitanOfVoidsMysteryEffect effect) {
        super(effect);
    }

    @Override
    public VristaTitanOfVoidsMysteryEffect copy() {
        return new VristaTitanOfVoidsMysteryEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = source.getSourcePermanentIfItStillExists(game);
        if (permanent == null) {
            return false;
        }
        if (EscapeAbility.wasCastedWithEscape(game, source.getSourceId(), source.getSourceObjectZoneChangeCounter())) {
            return false;
        }
        return permanent.sacrifice(source, game);
    }
}
