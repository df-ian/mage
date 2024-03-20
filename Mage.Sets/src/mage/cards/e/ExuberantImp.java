package mage.cards.e;

import mage.MageInt;
import mage.abilities.common.CantBlockAbility;
import mage.abilities.common.DealtDamageToSourceTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.dynamicvalue.common.SavedDamageValue;
import mage.abilities.effects.common.DamageAllEffect;
import mage.abilities.effects.common.DamageControllerEffect;
import mage.abilities.effects.common.DamagePlayersEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ExuberantImp extends CardImpl {

    public ExuberantImp(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{R}");
        this.subtype.add(SubType.IMP);

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        this.addAbility(FlyingAbility.getInstance());
        this.addAbility(new CantBlockAbility());

        this.addAbility(new EntersBattlefieldTriggeredAbility(new DamagePlayersEffect(1)));
    }

    private ExuberantImp(final ExuberantImp card) {
        super(card);
    }

    @Override
    public ExuberantImp copy() {
        return new ExuberantImp(this);
    }
}
