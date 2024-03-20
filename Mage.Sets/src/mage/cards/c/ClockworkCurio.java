
package mage.cards.c;

import mage.abilities.common.EntersBattlefieldTappedAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.keyword.CyclingAbility;
import mage.abilities.mana.BlackManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class ClockworkCurio extends CardImpl {

    public ClockworkCurio(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT},"{0}");

        // Charcoal Diamond enters the battlefield tapped.
        this.addAbility(new EntersBattlefieldTappedAbility());

        SimpleActivatedAbility ability = new SimpleActivatedAbility(new DrawCardSourceControllerEffect(1), new TapSourceCost());
        ability.addCost(new SacrificeSourceCost());
        ability.addEffect(new GainLifeEffect(1).concatBy("and"));
        this.addAbility(ability);
        this.addAbility(new CyclingAbility(new ManaCostsImpl<>("{1}")));
    }

    private ClockworkCurio(final ClockworkCurio card) {
        super(card);
    }

    @Override
    public ClockworkCurio copy() {
        return new ClockworkCurio(this);
    }
}
