
package mage.cards.u;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.PutIntoGraveFromAnywhereSourceTriggeredAbility;
import mage.abilities.effects.common.CastSourceTriggeredAbility;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.ShuffleIntoLibraryGraveOfSourceOwnerEffect;
import mage.abilities.keyword.AnnihilatorAbility;
import mage.abilities.keyword.IndestructibleAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.StaticFilters;
import mage.target.TargetPermanent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class UlamogTheVaucousFamine extends CardImpl {

    public UlamogTheVaucousFamine(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{10}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELDRAZI);

        this.power = new MageInt(10);
        this.toughness = new MageInt(10);

        // When you cast Ulamog, the Infinite Gyre, destroy target permanent.
        Ability ability = new CastSourceTriggeredAbility(new DestroyTargetEffect());
        ability.addTarget(new TargetPermanent(3, StaticFilters.FILTER_PERMANENT));
        this.addAbility(ability);

        // Annihilator 4 (Whenever this creature attacks, defending player sacrifices four permanents.)
        this.addAbility(new AnnihilatorAbility(2));
        // Indestructible
        this.addAbility(IndestructibleAbility.getInstance());
    }

    private UlamogTheVaucousFamine(final UlamogTheVaucousFamine card) {
        super(card);
    }

    @Override
    public UlamogTheVaucousFamine copy() {
        return new UlamogTheVaucousFamine(this);
    }
}
