package mage.cards.r;

import mage.MageInt;
import mage.abilities.common.BecomesTargetSourceTriggeredAbility;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.ReturnFromGraveyardToHandTargetEffect;
import mage.abilities.effects.common.continuous.DamageCantBePreventedEffect;
import mage.abilities.keyword.DredgeAbility;
import mage.cards.AdventureCard;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SetTargetPointer;
import mage.constants.SubType;
import mage.filter.StaticFilters;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCardInYourGraveyard;

import java.util.UUID;

/**
 * @author Ian
 */
public final class RottingWitness extends AdventureCard {

    public RottingWitness(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, new CardType[]{CardType.SORCERY}, "{1}{B}", "Promising Sludge", "{2}{G}");

        this.subtype.add(SubType.ELF);
        this.subtype.add(SubType.WARLOCK);
        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        // Whenever Bonecrusher Giant becomes the target of a spell, Bonecrusher Giant deals 2 damage to that spell's controller.
        this.addAbility(new DredgeAbility(1));
        // Stomp
        // Damage can’t be prevented this turn. Stomp deals 2 damage to any target.
        this.getSpellCard().getSpellAbility().addEffect(new ReturnFromGraveyardToHandTargetEffect());
        this.getSpellCard().getSpellAbility().addTarget(new TargetCardInYourGraveyard(StaticFilters.FILTER_CARD_PERMANENT));

        this.finalizeAdventure();
    }

    private RottingWitness(final RottingWitness card) {
        super(card);
    }

    @Override
    public RottingWitness copy() {
        return new RottingWitness(this);
    }
}
