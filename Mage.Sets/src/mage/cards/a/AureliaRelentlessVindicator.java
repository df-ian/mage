
package mage.cards.a;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.LeavesBattlefieldAllTriggeredAbility;
import mage.abilities.common.SacrificePermanentTriggeredAbility;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.game.permanent.token.SoldierToken;
import mage.game.permanent.token.TreasureToken;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class AureliaRelentlessVindicator extends CardImpl {

    public AureliaRelentlessVindicator(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{R}{W}");

        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.ANGEL);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // Haste
        this.addAbility(FlyingAbility.getInstance());
        this.addAbility(HasteAbility.getInstance());

        // Whenever Captain lannery Storm attacks, create a colorless Treasure artifact token with "{T}, Sacrifice this artifact: Add one mana of any color."
        this.addAbility(new AttacksTriggeredAbility(new CreateTokenEffect(new SoldierToken()), false));

        // Whenever you sacrifice a Treasure, Captain Lannery Storm gets +1/+0 until end of turn.
        Ability a = new LeavesBattlefieldAllTriggeredAbility(new DamageTargetEffect(1), StaticFilters.FILTER_CONTROLLED_CREATURE);
        a.addTarget(new TargetAnyTarget());
        this.addAbility(a);
    }

    private AureliaRelentlessVindicator(final AureliaRelentlessVindicator card) {
        super(card);
    }

    @Override
    public AureliaRelentlessVindicator copy() {
        return new AureliaRelentlessVindicator(this);
    }
}
