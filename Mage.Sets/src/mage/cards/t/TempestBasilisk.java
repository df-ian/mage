package mage.cards.t;

import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.costs.common.ExileFromHandCost;
import mage.abilities.effects.common.PutOnTopOrBottomLibraryTargetEffect;
import mage.abilities.keyword.EvokeAbility;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.FilterCard;
import mage.filter.FilterSpell;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.target.TargetSpell;
import mage.target.common.TargetCardInHand;
import mage.target.common.TargetCreatureOrPlaneswalker;

import java.util.UUID;

/**
 * @author Ian
 */
public final class TempestBasilisk extends CardImpl {

    private static final FilterSpell filter = new FilterSpell("creature or planeswalker");

    public TempestBasilisk(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{U}{U}");

        this.subtype.add(SubType.BASILISK);
        this.power = new MageInt(3);
        this.toughness = new MageInt(1);

        // Flash
        this.addAbility(FlashAbility.getInstance());

        // When Subtlety enters the battlefield, choose up to one target creature spell or planeswalker spell. Its owner puts it on the top or bottom of their library.
        Ability ability = new EntersBattlefieldTriggeredAbility(new PutOnTopOrBottomLibraryTargetEffect(false).setText(
                "choose up to one target creature or planeswalker. " +
                        "Its owner puts it on the top or bottom of their library"
        ));
        ability.addTarget(new TargetCreatureOrPlaneswalker());
        this.addAbility(ability);

        // Evoke—Exile a blue card from your hand.
        this.addAbility(new EvokeAbility("{2}{U}"));
    }

    private TempestBasilisk(final TempestBasilisk card) {
        super(card);
    }

    @Override
    public TempestBasilisk copy() {
        return new TempestBasilisk(this);
    }
}
