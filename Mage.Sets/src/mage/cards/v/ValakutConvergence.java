
package mage.cards.v;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldControlledTriggeredAbility;
import mage.abilities.condition.common.MetalcraftCondition;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.decorator.ConditionalActivatedAbility;
import mage.abilities.effects.common.DamagePlayersEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayEffect;
import mage.abilities.hint.common.MetalcraftHint;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.Filter;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.common.FilterCreatureCard;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetCardInLibrary;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class ValakutConvergence extends CardImpl {

    public ValakutConvergence(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ENCHANTMENT},"{2}{R}");

        Ability a = new EntersBattlefieldControlledTriggeredAbility(Zone.BATTLEFIELD, new DamageTargetEffect(1),
                StaticFilters.FILTER_PERMANENT_ARTIFACT, false);
        a.addTarget(new TargetAnyTarget());
        // Whenever an artifact enters the battlefield under your control, Reckless Fireweaver deals 1 damage to each opponent.
        this.addAbility(a);

        FilterCard dorgon = new FilterCreatureCard("Dragon creature card");
        dorgon.add(SubType.DRAGON.getPredicate());
        a = new ConditionalActivatedAbility(new SearchLibraryPutInPlayEffect(new TargetCardInLibrary(dorgon)), new SacrificeSourceCost(), MetalcraftCondition.instance);
        a.addHint(MetalcraftHint.instance);
        a.setAbilityWord(AbilityWord.METALCRAFT);

        this.addAbility(a);
    }

    private ValakutConvergence(final ValakutConvergence card) {
        super(card);
    }

    @Override
    public ValakutConvergence copy() {
        return new ValakutConvergence(this);
    }
}
