package mage.cards.s;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.LandfallAbility;
import mage.abilities.common.SacrificePermanentTriggeredAbility;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.game.permanent.token.JunkToken;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ScorchfieldScavenger extends CardImpl {

    public ScorchfieldScavenger(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{R}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SCOUT);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        this.addAbility(new LandfallAbility(new CreateTokenEffect(new JunkToken())));

        // Whenever you sacrifice a permanent, Havoc Jester deals 1 damage to any target.
        FilterPermanent junk = new FilterPermanent(SubType.JUNK, "Junk");
        Ability ability = new SacrificePermanentTriggeredAbility(new DamageTargetEffect(1), junk);
        ability.addTarget(new TargetAnyTarget());
        this.addAbility(ability);
    }

    private ScorchfieldScavenger(final ScorchfieldScavenger card) {
        super(card);
    }

    @Override
    public ScorchfieldScavenger copy() {
        return new ScorchfieldScavenger(this);
    }
}
