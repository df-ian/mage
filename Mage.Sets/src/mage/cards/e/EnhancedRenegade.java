
package mage.cards.e;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.PutIntoGraveFromBattlefieldAllTriggeredAbility;
import mage.abilities.costs.common.PayLifeCost;
import mage.abilities.dynamicvalue.common.SourcePermanentPowerCount;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.LoseLifeTargetEffect;
import mage.abilities.keyword.FabricateAbility;
import mage.abilities.keyword.MenaceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.Filter;
import mage.filter.common.FilterControlledArtifactPermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.permanent.TokenPredicate;
import mage.target.common.TargetOpponent;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class EnhancedRenegade extends CardImpl {

    public EnhancedRenegade(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT, CardType.CREATURE}, "{2}{B}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(4);
        this.toughness = new MageInt(2);

        this.addAbility(new MenaceAbility(false));

        Filter filter = new FilterControlledArtifactPermanent();
        filter.add(TokenPredicate.FALSE);

        // Whenever an artifact you control is put into a graveyard from the battlefield, target opponent loses life equal to Marionette Master's power.
        Effect effect = new DoIfCostPaid(new DrawCardSourceControllerEffect(1), new PayLifeCost(2));
        Ability ability = new PutIntoGraveFromBattlefieldAllTriggeredAbility(effect, false, new FilterControlledArtifactPermanent("an artifact you control"), false);
        this.addAbility(ability);
    }

    private EnhancedRenegade(final EnhancedRenegade card) {
        super(card);
    }

    @Override
    public EnhancedRenegade copy() {
        return new EnhancedRenegade(this);
    }
}
