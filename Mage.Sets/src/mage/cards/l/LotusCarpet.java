package mage.cards.l;

import mage.Mana;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.mana.ManaEffect;
import mage.abilities.keyword.CyclingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.choices.ChoiceColor;
import mage.constants.*;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.Predicates;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.players.Player;
import mage.target.common.TargetOpponent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Ian
 */
public final class LotusCarpet extends CardImpl {

    public LotusCarpet(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{G}");

        // At the beginning of each of your main phases, if you haven't added mana 
        // with this ability this turn, you may add up to X mana of any one color, 
        // where X is the number of Islands target opponent controls.
        this.addAbility(new LotusCarpetTriggeredAbility());

        this.addAbility(new CyclingAbility(new ManaCostsImpl<>("{2}")));
    }

    private LotusCarpet(final LotusCarpet card) {
        super(card);
    }

    @Override
    public LotusCarpet copy() {
        return new LotusCarpet(this);
    }
}

class LotusCarpetTriggeredAbility extends TriggeredAbilityImpl {

    LotusCarpetTriggeredAbility() {
        super(Zone.BATTLEFIELD, new DoIfCostPaid(new LotusCarpetEffect(), new ManaCostsImpl<>("{1}")));
        this.addTarget(new TargetOpponent());
        setTriggerPhrase("At the beginning of your precombat main phase, ");
    }

    private LotusCarpetTriggeredAbility(final LotusCarpetTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public LotusCarpetTriggeredAbility copy() {
        return new LotusCarpetTriggeredAbility(this);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.PRECOMBAT_MAIN_PHASE_PRE;
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        return event.getPlayerId().equals(this.controllerId);
    }

    @Override
    public boolean checkInterveningIfClause(Game game) {
        return !Boolean.TRUE.equals(game.getState().getValue(this.getOriginalId().toString()
                + "addMana"
                + game.getState().getZoneChangeCounter(sourceId)));
    }

    @Override
    public boolean resolve(Game game) {
        boolean value = super.resolve(game);
        if (value == true) {
            game.getState().setValue(this.getOriginalId().toString()
                            + "addMana"
                            + game.getState().getZoneChangeCounter(sourceId),
                    Boolean.TRUE);
        }
        return value;
    }

    @Override
    public void reset(Game game) {
        game.getState().setValue(this.getOriginalId().toString()
                        + "addMana"
                        + game.getState().getZoneChangeCounter(sourceId),
                Boolean.FALSE);
    }
}

class LotusCarpetEffect extends ManaEffect {

    private static final FilterControlledPermanent filter = new FilterControlledPermanent("nonbasic land ");

    static {
        filter.add(Predicates.not(SuperType.BASIC.getPredicate()));
        filter.add(CardType.LAND.getPredicate());
    }

    LotusCarpetEffect() {
        super();
        staticText = "you may add X mana of any one color, where X is the number of nonbasic lands target opponent controls";
    }

    private LotusCarpetEffect(final LotusCarpetEffect effect) {
        super(effect);
    }

    @Override
    public List<Mana> getNetMana(Game game, Ability source) {
        List<Mana> netMana = new ArrayList<>();
        if (game != null) {
            int count = game.getBattlefield().count(filter, source.getTargets().getFirstTarget(), source, game);
            if (count > 0) {
                netMana.add(Mana.AnyMana(count));
            }
        }
        return netMana;
    }

    @Override
    public Mana produceMana(Game game, Ability source) {
        Mana mana = new Mana();
        if (game == null) {
            return mana;
        }
        Player controller = game.getPlayer(source.getControllerId());
        ChoiceColor choice = new ChoiceColor();
        if (controller != null && controller.choose(Outcome.Benefit, choice, game)) {
            int count = game.getBattlefield().count(filter, source.getTargets().getFirstTarget(), source, game);
            if (count > 0) {
                switch (choice.getChoice()) {
                    case "Black":
                        mana.setBlack(count);
                        break;
                    case "Blue":
                        mana.setBlue(count);
                        break;
                    case "Red":
                        mana.setRed(count);
                        break;
                    case "Green":
                        mana.setGreen(count);
                        break;
                    case "White":
                        mana.setWhite(count);
                        break;
                    default:
                        break;
                }
            }
        }
        return mana;
    }

    @Override
    public LotusCarpetEffect copy() {
        return new LotusCarpetEffect(this);
    }
}
