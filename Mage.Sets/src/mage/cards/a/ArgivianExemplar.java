package mage.cards.a;

import mage.MageInt;
import mage.abilities.common.DrawNthCardTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.dynamicvalue.common.ArtifactYouControlCount;
import mage.abilities.dynamicvalue.common.CreaturesYouControlCount;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.cost.SpellCostReductionForEachSourceEffect;
import mage.abilities.hint.common.ArtifactYouControlHint;
import mage.abilities.hint.common.CreaturesYouControlHint;
import mage.abilities.keyword.DevoidAbility;
import mage.abilities.keyword.EscapeAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.game.permanent.token.EldraziSpawnToken;

import java.util.UUID;

/**
 * @author Susucr
 */
public final class ArgivianExemplar extends CardImpl {

    public ArgivianExemplar(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{8}{W}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SOLDIER);
        this.power = new MageInt(8);
        this.toughness = new MageInt(8);

        this.addAbility(new SimpleStaticAbility(Zone.ALL,
                new SpellCostReductionForEachSourceEffect(1, CreaturesYouControlCount.instance)
        ).addHint(CreaturesYouControlHint.instance));

        // Devoid
        this.addAbility(VigilanceAbility.getInstance());

        this.addAbility(new EscapeAbility(this, "{10}{W}{W}", 2));
    }

    private ArgivianExemplar(final ArgivianExemplar card) {
        super(card);
    }

    @Override
    public ArgivianExemplar copy() {
        return new ArgivianExemplar(this);
    }
}
