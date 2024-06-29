package mage.cards.g;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.keyword.AffinityForArtifactsAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

import java.util.UUID;

/**
 * @author Ian
 */
public final class Glimmerzoa extends CardImpl {

    public Glimmerzoa(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{2}{U}");

        this.subtype.add(SubType.JELLYFISH);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        // Affinity for artifacts
        this.addAbility(new AffinityForArtifactsAbility());

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        this.addAbility(new WardAbility(new ManaCostsImpl<>("{1}")));
    }

    private Glimmerzoa(final Glimmerzoa card) {
        super(card);
    }

    @Override
    public Glimmerzoa copy() {
        return new Glimmerzoa(this);
    }
}
