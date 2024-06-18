package mage.cards.s;

import mage.abilities.Mode;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.*;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.StaticFilters;
import mage.target.TargetPermanent;
import mage.target.TargetPlayer;
import mage.target.TargetSpell;
import mage.target.common.TargetNonlandPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SealOfPhimyrium extends CardImpl {

    public SealOfPhimyrium(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.INSTANT}, "{U}");

        // Spree
        this.addAbility(new SpreeAbility(this));


        // + {2} -- Create a token that's a copy of target artifact or creature you control.
        this.getSpellAbility().addMode(new Mode(new ReturnToHandTargetEffect())
                .addTarget(new TargetNonlandPermanent())
                .withCost(new GenericManaCost(2)));

        // + {U} -- Counter target spell.
        this.getSpellAbility().addEffect(new CounterTargetEffect());
        this.getSpellAbility().addTarget(new TargetSpell());
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{U}"));

        // + {3} -- Target player draws two cards.
        this.getSpellAbility().addMode(new Mode(new DrawCardTargetEffect(2))
                .withCost(new GenericManaCost(3))
                .addTarget(new TargetPlayer()));
    }

    private SealOfPhimyrium(final SealOfPhimyrium card) {
        super(card);
    }

    @Override
    public SealOfPhimyrium copy() {
        return new SealOfPhimyrium(this);
    }
}
