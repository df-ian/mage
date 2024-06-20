package mage.cards.c;

import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.common.ArtifactYouControlCount;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.LoseLifeTargetEffect;
import mage.abilities.keyword.CyclingAbility;
import mage.abilities.keyword.FlashbackAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.target.TargetPlayer;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 *
 * @author Ian.com
 */
public final class ConsumingConviction extends CardImpl {

    public ConsumingConviction(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{B}");

        this.getSpellAbility().addTarget(new TargetPlayer());
        this.getSpellAbility().addEffect(new LoseLifeTargetEffect(ArtifactYouControlCount.instance));

        this.addAbility(new CyclingAbility(new SacrificeTargetCost(StaticFilters.FILTER_PERMANENT_ARTIFACT)));
        this.addAbility(new FlashbackAbility(this, new ManaCostsImpl<>("{1}{R}")));
    }

    private ConsumingConviction(final ConsumingConviction card) {
        super(card);
    }

    @Override
    public ConsumingConviction copy() {
        return new ConsumingConviction(this);
    }

}
