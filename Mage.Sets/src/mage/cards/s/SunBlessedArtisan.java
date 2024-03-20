package mage.cards.s;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.search.SearchLibraryPutOnLibraryEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.FilterCard;
import mage.filter.common.FilterArtifactCard;
import mage.target.common.TargetCardInLibrary;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SunBlessedArtisan extends CardImpl {

    private static final FilterCard filter = new FilterArtifactCard();

    public SunBlessedArtisan(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ARTIFICER);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        // Divine Intervention — When Moon-Blessed Cleric enters the battlefield, you may search your library for an enchantment card, reveal it, then shuffle and put that card on top.
        this.addAbility(new EntersBattlefieldTriggeredAbility(
                new SearchLibraryPutOnLibraryEffect(
                        new TargetCardInLibrary(filter), true
                ), true
        ));
    }

    private SunBlessedArtisan(final SunBlessedArtisan card) {
        super(card);
    }

    @Override
    public SunBlessedArtisan copy() {
        return new SunBlessedArtisan(this);
    }
}
