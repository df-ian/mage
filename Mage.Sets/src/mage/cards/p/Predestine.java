package mage.cards.p;

import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.keyword.ScryEffect;
import mage.abilities.effects.keyword.SurveilEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class Predestine extends CardImpl {

    public Predestine(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{U}");

        // Scry 2, then draw a card. (To scry 2, look at the top two cards of your library, then put any number of them on the bottom of your library and the rest on top in any order.)
        this.getSpellAbility().addEffect(
                new SurveilEffect(2, false)
        );
        this.getSpellAbility().addEffect(
                new DrawCardSourceControllerEffect(1)
                        .setText(", then draw a card.")
        );
    }

    private Predestine(final Predestine card) {
        super(card);
    }

    @Override
    public Predestine copy() {
        return new Predestine(this);
    }
}
