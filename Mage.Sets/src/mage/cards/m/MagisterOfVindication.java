

package mage.cards.m;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.CycleTriggeredAbility;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DamagePlayersEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.keyword.CyclingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.TargetController;
import mage.filter.FilterSpell;
import mage.filter.predicate.Predicates;
import mage.target.common.TargetAnyTarget;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class MagisterOfVindication extends CardImpl {

    private static final FilterSpell filter = new FilterSpell("an instant or sorcery spell");

    static {
        filter.add(Predicates.or(
                CardType.INSTANT.getPredicate(),
                CardType.SORCERY.getPredicate()));
    }

    public MagisterOfVindication(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{R}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);

        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Whenever you cast an instant or sorcery spell, Guttersnipe deals 1 damage to each opponent.
        Ability ability = new SpellCastControllerTriggeredAbility(new DamageTargetEffect(1), filter, false);
        ability.addTarget(new TargetAnyTarget());
        this.addAbility(ability);

        ability = new CycleTriggeredAbility(new DamageTargetEffect(1));
        ability.addTarget(new TargetAnyTarget());
        this.addAbility(ability);

        this.addAbility(new CyclingAbility(new ManaCostsImpl<>("{1}{R}")));
    }

    private MagisterOfVindication(final MagisterOfVindication card) {
        super(card);
    }

    @Override
    public MagisterOfVindication copy() {
        return new MagisterOfVindication(this);
    }
}
