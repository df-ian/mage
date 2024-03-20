package mage.cards.d;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.combat.CantAttackBlockTargetEffect;
import mage.abilities.keyword.LifelinkAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCardInYourGraveyard;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 * @author Ian
 */
public final class DawnthreadElk extends CardImpl {

    private static final FilterCard filter
            = new FilterCreatureCard("creature card with mana value 2 or less from your graveyard");

    static {
        filter.add(new ManaValuePredicate(ComparisonType.FEWER_THAN, 3));
    }

    public DawnthreadElk(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G/W}{G/W}");

        this.subtype.add(SubType.ELK);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // Lifelink
        this.addAbility(LifelinkAbility.getInstance());

        // When Extraction Specialist enters the battlefield, return target creature card with mana value 2 or less from your graveyard to the battlefield.
        // That creature can't attack or block for as long as you control Extraction Specialist.
        Ability ability = new EntersBattlefieldTriggeredAbility(new DawnthreadElkEffect());
        ability.addTarget(new TargetCardInYourGraveyard(filter));
        this.addAbility(ability);
    }

    private DawnthreadElk(final DawnthreadElk card) {
        super(card);
    }

    @Override
    public DawnthreadElk copy() {
        return new DawnthreadElk(this);
    }
}

class DawnthreadElkEffect extends OneShotEffect {

    DawnthreadElkEffect() {
        super(Outcome.Benefit);
        staticText = "return target creature card with mana value 2 or less from your graveyard to the battlefield. ";
    }

    private DawnthreadElkEffect(final DawnthreadElkEffect effect) {
        super(effect);
    }

    @Override
    public DawnthreadElkEffect copy() {
        return new DawnthreadElkEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player player = game.getPlayer(source.getControllerId());
        Card card = game.getCard(getTargetPointer().getFirst(game, source));
        if (player == null || card == null) {
            return false;
        }
        player.moveCards(card, Zone.BATTLEFIELD, source, game);
        return true;
    }
}
