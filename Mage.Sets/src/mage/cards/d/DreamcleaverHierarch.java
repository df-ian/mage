package mage.cards.d;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.LookLibraryAndPickControllerEffect;
import mage.abilities.keyword.AffinityForArtifactsAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.PutCards;
import mage.constants.SubType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class DreamcleaverHierarch extends CardImpl {

    public DreamcleaverHierarch(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{6}{U/P}{U/P}");

        this.subtype.add(SubType.PHYREXIAN);
        this.subtype.add(SubType.ANGEL);
        this.power = new MageInt(3);
        this.toughness = new MageInt(1);

        this.addAbility(new AffinityForArtifactsAbility());

        // When Organ Hoarder enters the battlefield, look at the top three cards of your library, then put one of them into your hand and the rest into you graveyard.
        this.addAbility(new EntersBattlefieldTriggeredAbility(new LookLibraryAndPickControllerEffect(
                2, 1, PutCards.HAND, PutCards.GRAVEYARD
        ).setText("look at the top two cards of your library, then put one of them into your hand and the rest into your graveyard")));
    }

    private DreamcleaverHierarch(final DreamcleaverHierarch card) {
        super(card);
    }

    @Override
    public DreamcleaverHierarch copy() {
        return new DreamcleaverHierarch(this);
    }
}
