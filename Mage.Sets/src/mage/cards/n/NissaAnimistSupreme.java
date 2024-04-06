
package mage.cards.n;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldOrAttacksSourceTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayEffect;
import mage.abilities.keyword.TrampleAbility;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.SuperType;
import mage.filter.common.FilterLandCard;
import mage.target.common.TargetCardInLibrary;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class NissaAnimistSupreme extends CardImpl {

    public NissaAnimistSupreme(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{G}{G}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.ELF);
        this.subtype.add(SubType.DRUID);

        this.power = new MageInt(5);
        this.toughness = new MageInt(7);

        // Trample
        this.addAbility(new WardAbility(new ManaCostsImpl<>("{4}")));

        // Whenever Primeval Titan enters the battlefield or attacks, you may search your library for up to two land cards, put them onto the battlefield tapped, then shuffle your library.
        this.addAbility(new EntersBattlefieldOrAttacksSourceTriggeredAbility(new SearchLibraryPutInPlayEffect(new TargetCardInLibrary(0, 2, new FilterLandCard("land cards")), true), true));
    }

    private NissaAnimistSupreme(final NissaAnimistSupreme card) {
        super(card);
    }

    @Override
    public NissaAnimistSupreme copy() {
        return new NissaAnimistSupreme(this);
    }

}
