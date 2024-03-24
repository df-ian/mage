

package mage.cards.a;

import mage.abilities.common.EntersBattlefieldOrDiesSourceTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class AethercatchRelic extends CardImpl {

    public AethercatchRelic(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");

        // When Ichor Wellspring enters the battlefield or is put into a graveyard from the battlefield, draw a card.
        this.addAbility(new EntersBattlefieldOrDiesSourceTriggeredAbility(
                new DrawCardSourceControllerEffect(1), false, false
        ));

        SimpleActivatedAbility ability = new SimpleActivatedAbility(new GainLifeEffect(3), new ManaCostsImpl<>("{2}"));
        ability.addCost(new SacrificeSourceCost());

        this.addAbility(ability);
    }

    private AethercatchRelic(final AethercatchRelic card) {
        super(card);
    }

    @Override
    public AethercatchRelic copy() {
        return new AethercatchRelic(this);
    }

}
