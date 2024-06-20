package mage.cards.s;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.ExileReturnBattlefieldNextEndStepTargetEffect;
import mage.abilities.keyword.CyclingAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.FilterPermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.AnotherPredicate;
import mage.target.TargetPermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SpectorFlask extends CardImpl {

    private static final FilterPermanent filter = new FilterPermanent("another target artifact or creature");

    static {
        filter.add(AnotherPredicate.instance);
        filter.add(Predicates.or(CardType.ARTIFACT.getPredicate(), CardType.CREATURE.getPredicate()));
    }

    public SpectorFlask(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{2}");

        // When Flickerwisp enters the battlefield, exile another target permanent. Return that card to the battlefield under its owner's control at the beginning of the next end step.
        Ability ability = new EntersBattlefieldTriggeredAbility(new ExileReturnBattlefieldNextEndStepTargetEffect());
        ability.addTarget(new TargetPermanent(filter));
        this.addAbility(ability);

        this.addAbility(new CyclingAbility(new ManaCostsImpl<>("{2}")));
    }

    private SpectorFlask(final SpectorFlask card) {
        super(card);
    }

    @Override
    public SpectorFlask copy() {
        return new SpectorFlask(this);
    }
}
