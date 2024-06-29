package mage.cards.f;

import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.continuous.BoostAllEffect;
import mage.abilities.keyword.DeathtouchAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.abilities.keyword.WardAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterSpell;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.AbilityPredicate;
import mage.game.permanent.token.FaerieToken;

import java.util.UUID;

/**
 * @author Ian
 */
public final class FeywildSonneteer extends CardImpl {

    private static final FilterSpell filter2 = new FilterSpell("a Faerie spell");

    static {
        filter2.add(Predicates.or(
                SubType.FAERIE.getPredicate()
        ));
    }

    public FeywildSonneteer(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{U}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.BARD);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        this.addAbility(new WardAbility(new ManaCostsImpl<>("{1}")));

        // Whenever you cast an artifact or enchantment spell, create a 1/1 blue Faerie creature token with flying.
        this.addAbility(new SpellCastControllerTriggeredAbility(
                new CreateTokenEffect(new FaerieToken()), filter2, false
        ));
    }

    private FeywildSonneteer(final FeywildSonneteer card) {
        super(card);
    }

    @Override
    public FeywildSonneteer copy() {
        return new FeywildSonneteer(this);
    }
}
