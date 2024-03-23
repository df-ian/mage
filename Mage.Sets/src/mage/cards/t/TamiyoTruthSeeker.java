package mage.cards.t;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DiesSourceTriggeredAbility;
import mage.abilities.common.EntersBattlefieldOrAttacksSourceTriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.BrainstormEffect;
import mage.abilities.effects.common.ShuffleIntoLibrarySourceEffect;
import mage.abilities.effects.keyword.ScryEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HexproofAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class TamiyoTruthSeeker extends CardImpl {

    public TamiyoTruthSeeker(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{G}{U}{U}");

        this.subtype.add(SubType.MOONFOLK);
        this.subtype.add(SubType.WIZARD);
        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // Flying
        this.addAbility(HexproofAbility.getInstance());

        // When Cavalier of Gales enters the battlefield, draw three cards, then put two cards from your hand on top of your library in any order.
        this.addAbility(new EntersBattlefieldOrAttacksSourceTriggeredAbility(new BrainstormEffect()));
    }

    private TamiyoTruthSeeker(final TamiyoTruthSeeker card) {
        super(card);
    }

    @Override
    public TamiyoTruthSeeker copy() {
        return new TamiyoTruthSeeker(this);
    }
}
