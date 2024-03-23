package mage.game.command.emblems;

import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.ContinuousEffectImpl;
import mage.abilities.effects.common.ruleModifying.PlayLandsFromGraveyardControllerEffect;
import mage.abilities.keyword.RetraceAbility;
import mage.cards.*;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.common.FilterLandCard;
import mage.filter.predicate.Predicates;
import mage.game.Game;
import mage.game.command.Emblem;
import mage.players.Player;

import java.util.UUID;
import java.util.function.Predicate;

/**
 * @author Ian
 */
public final class EshuoEmblem extends Emblem {

    public EshuoEmblem() {
        super("Emblem Eshuo");
        this.getAbilities().add(new SimpleStaticAbility(Zone.COMMAND, new PlayLandsFromGraveyardControllerEffect()));
    }

    private EshuoEmblem(final EshuoEmblem card) {
        super(card);
    }

    @Override
    public EshuoEmblem copy() {
        return new EshuoEmblem(this);
    }
}
