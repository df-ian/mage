package mage.cards.s;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.keyword.AffinityForArtifactsAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.game.permanent.token.ServoToken;
import mage.game.permanent.token.ThopterToken;

import java.util.UUID;

/**
 * @author Ian
 */
public final class ServoswipeAdept extends CardImpl {

    public ServoswipeAdept(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{4}{U}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.ARTIFICER);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Affinity for artifacts
        this.addAbility(new AffinityForArtifactsAbility());

        // When Thought Monitor enters the battlefield, draw two cards.
        this.addAbility(new EntersBattlefieldTriggeredAbility(new CreateTokenEffect(new ThopterToken())));
    }

    private ServoswipeAdept(final ServoswipeAdept card) {
        super(card);
    }

    @Override
    public ServoswipeAdept copy() {
        return new ServoswipeAdept(this);
    }
}
