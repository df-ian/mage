
package mage.cards.s;

import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.FilterPermanent;
import mage.filter.FilterSpell;
import mage.filter.predicate.ObjectSourcePlayer;
import mage.filter.predicate.ObjectSourcePlayerPredicate;
import mage.game.Game;
import mage.target.TargetSpell;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SpellstutterCohort extends CardImpl {

    private static final FilterSpell filter = new FilterSpell("spell with mana value X or less, where X is the number of Faeries you control");

    static {
        filter.add(SpellstutterCohortPredicate.instance);
    }

    public SpellstutterCohort(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{1}{U}");
        this.subtype.add(SubType.FAERIE);
        this.subtype.add(SubType.WIZARD);

        this.power = new MageInt(2);
        this.toughness = new MageInt(1);

        // Flash
        this.addAbility(FlashAbility.getInstance());

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // When Spellstutter Cohort enters the battlefield, counter target spell with converted mana cost X or less, where X is the number of Faeries you control.
        Ability ability = new EntersBattlefieldTriggeredAbility(new CounterTargetEffect());
        ability.addTarget(new TargetSpell(filter));
        this.addAbility(ability);
    }

    private SpellstutterCohort(final SpellstutterCohort card) {
        super(card);
    }

    @Override
    public SpellstutterCohort copy() {
        return new SpellstutterCohort(this);
    }
}

enum SpellstutterCohortPredicate implements ObjectSourcePlayerPredicate<MageObject> {
    instance;
    private static final FilterPermanent filter = new FilterPermanent();

    static {
        filter.add(SubType.FAERIE.getPredicate());
    }

    @Override
    public boolean apply(ObjectSourcePlayer<MageObject> input, Game game) {
        return input.getObject().getManaValue() <=
                game.getBattlefield().countAll(filter, game.getControllerId(input.getSourceId()), game);
    }
}