package mage.cards.b;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.BeginningOfCombatTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.dynamicvalue.common.StaticValue;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.LoseLifeSourceControllerEffect;
import mage.abilities.effects.common.combat.AttacksIfAbleAllEffect;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.TargetController;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.AnotherPredicate;
import mage.filter.predicate.permanent.AttackingPredicate;
import mage.game.permanent.token.GoblinToken;
import mage.game.permanent.token.OphiomancerSnakeToken;

import java.util.UUID;

/**
 * @author Ian
 */
public final class BitterOphiomancer extends CardImpl {


    public BitterOphiomancer(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{B}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.SHAMAN);

        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        this.addAbility(new WardAbility(new ManaCostsImpl<>("{1}")));

        // At the beginning of combat on your turn, create a 1/1 red Goblin creature token with haste.
        Ability ability = new BeginningOfCombatTriggeredAbility(new CreateTokenEffect(new OphiomancerSnakeToken()), TargetController.YOU, false);
        ability.addEffect(new LoseLifeSourceControllerEffect(1).concatBy(", then"));
        this.addAbility(ability);
    }

    private BitterOphiomancer(final BitterOphiomancer card) {
        super(card);
    }

    @Override
    public BitterOphiomancer copy() {
        return new BitterOphiomancer(this);
    }
}
