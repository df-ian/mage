package mage.cards.i;

import mage.abilities.costs.common.DiscardCardCost;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.discard.DiscardControllerEffect;
import mage.abilities.keyword.JumpStartAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.FilterCard;
import mage.filter.common.FilterArtifactCard;

import java.util.UUID;

/**
 * @author Ian
 */
public final class InorganicPortent extends CardImpl {

    private static final FilterCard filter = new FilterArtifactCard();

    public InorganicPortent(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{1}{R}");

        // Draw three cards. Then discard two cards unless you discard an artifact card.
        DiscardCardCost cost = new DiscardCardCost(filter);
        cost.setText("discard an artifact card instead of discarding two cards");
        this.getSpellAbility().addEffect(new DrawCardSourceControllerEffect(2));
        this.getSpellAbility().addEffect(new DoIfCostPaid(
                null, new DiscardControllerEffect(2), cost
        ).setText("Then discard two cards unless you discard an artifact card"));

        this.addAbility(new JumpStartAbility(this));
    }

    private InorganicPortent(final InorganicPortent card) {
        super(card);
    }

    @Override
    public InorganicPortent copy() {
        return new InorganicPortent(this);
    }
}
