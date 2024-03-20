
package mage.cards.a;

import mage.MageInt;
import mage.abilities.common.LimitedTimesPerTurnActivatedAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.combat.CantBeBlockedAllEffect;
import mage.abilities.effects.common.combat.CantBeBlockedSourceEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.keyword.MadnessAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.Zone;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class AmphibiousRootwalla extends CardImpl {

    public AmphibiousRootwalla(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{U}");
        this.subtype.add(SubType.FISH);
        this.subtype.add(SubType.LIZARD);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        LimitedTimesPerTurnActivatedAbility ability = new LimitedTimesPerTurnActivatedAbility(Zone.BATTLEFIELD,
                new BoostSourceEffect(1, 1, Duration.EndOfTurn), new ManaCostsImpl<>("{U}"));
                ability.addEffect(new CantBeBlockedSourceEffect(Duration.EndOfTurn));
        // {1}{G}: Basking Rootwalla gets +2/+2 until end of turn. Activate this ability only once each turn.
        this.addAbility(ability);

        // Madness {0}
        this.addAbility(new MadnessAbility(new ManaCostsImpl<>("{0}")));
    }

    private AmphibiousRootwalla(final AmphibiousRootwalla card) {
        super(card);
    }

    @Override
    public AmphibiousRootwalla copy() {
        return new AmphibiousRootwalla(this);
    }
}
