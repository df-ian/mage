package mage.cards.d;

import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateTokenAttachSourceEffect;
import mage.abilities.effects.common.ReturnToHandSourceEffect;
import mage.abilities.effects.common.continuous.BoostEquippedEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.keyword.EquipAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.AttachmentType;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.game.permanent.token.HumanToken;
import mage.game.permanent.token.SoldierToken;
import mage.game.permanent.token.Token;

import java.util.UUID;

/**
 * @author Ian
 */
public final class DevilshroudVeil extends CardImpl {

    public DevilshroudVeil(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}{R}");

        this.subtype.add(SubType.EQUIPMENT);

        // When Ancestral Blade enters the battlefield, create a 1/1 white Soldier creature token, then attach Ancestral Blade to it.
        Token token = new HumanToken();
        token.addSubType(SubType.NOBLE);
        this.addAbility(new EntersBattlefieldTriggeredAbility(new CreateTokenAttachSourceEffect(token)));

        // Equipped creature get +1/+1
        this.addAbility(new SimpleStaticAbility(new BoostEquippedEffect(1, 0)));
        this.addAbility(new SimpleStaticAbility(new GainAbilityAttachedEffect(HasteAbility.getInstance(), AttachmentType.EQUIPMENT)));

        this.addAbility(new SimpleActivatedAbility(new ReturnToHandSourceEffect(), new ManaCostsImpl<>("{R}")));
    }

    private DevilshroudVeil(final DevilshroudVeil card) {
        super(card);
    }

    @Override
    public DevilshroudVeil copy() {
        return new DevilshroudVeil(this);
    }
}
