package mage.cards.s;

import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.common.LandsYouControlCount;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.effects.common.DrawCardTargetEffect;
import mage.abilities.effects.common.MillThenPutInHandEffect;
import mage.abilities.effects.common.ReturnToHandTargetEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayEffect;
import mage.abilities.hint.common.LandsYouControlHint;
import mage.abilities.keyword.SpreeAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.token.SeedGuardianToken;
import mage.target.TargetPlayer;
import mage.target.TargetSpell;
import mage.target.common.TargetCardInLibrary;
import mage.target.common.TargetNonlandPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SealOfPrimorthus extends CardImpl {

    public SealOfPrimorthus(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.SORCERY}, "{G}");

        // Spree
        this.addAbility(new SpreeAbility(this));

        this.getSpellAbility().addMode(new Mode(new SearchLibraryPutInPlayEffect(new TargetCardInLibrary(StaticFilters.FILTER_CARD_BASIC_LAND_A)))
                .withCost(new ManaCostsImpl<>("{G}")));

        this.getSpellAbility().addEffect(new MillThenPutInHandEffect(4, StaticFilters.FILTER_CARD_CREATURE_OR_LAND, null, true, 2));
        this.getSpellAbility().withFirstModeCost(new ManaCostsImpl<>("{G}"));


        // + {3} -- Create an X/X green Elemental creature token, where X is the number of lands you control.
        this.getSpellAbility().addMode(new Mode(new SealOfPrimorthusEffect()).withCost(new GenericManaCost(2)));
        this.getSpellAbility().addHint(LandsYouControlHint.instance);
    }

    private SealOfPrimorthus(final SealOfPrimorthus card) {
        super(card);
    }

    @Override
    public SealOfPrimorthus copy() {
        return new SealOfPrimorthus(this);
    }
}

class SealOfPrimorthusEffect extends OneShotEffect {

    SealOfPrimorthusEffect() {
        super(Outcome.Benefit);
        staticText = "create an X/X green Elemental creature token, where X is the number of lands you control";
    }

    private SealOfPrimorthusEffect(final SealOfPrimorthusEffect effect) {
        super(effect);
    }

    @Override
    public SealOfPrimorthusEffect copy() {
        return new SealOfPrimorthusEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return new SeedGuardianToken(LandsYouControlCount.instance.calculate(game, source, this))
                .putOntoBattlefield(1, game, source);
    }
}