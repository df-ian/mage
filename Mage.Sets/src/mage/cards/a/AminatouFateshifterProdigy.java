package mage.cards.a;

import mage.MageInt;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.effects.common.cost.SpellsCostReductionControllerEffect;
import mage.abilities.effects.keyword.SurveilEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.FilterCard;
import mage.filter.StaticFilters;
import mage.filter.predicate.card.CastFromZonePredicate;

import java.util.UUID;

/**
 * @author Ian
 */
public final class AminatouFateshifterProdigy extends CardImpl {

    private static final FilterCard filter = new FilterCard("spells you cast from your graveyard");

    static {
        filter.add(new CastFromZonePredicate(Zone.GRAVEYARD));
    }

    public AminatouFateshifterProdigy(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{W}{U}{B}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);

        this.power = new MageInt(1);
        this.toughness = new MageInt(3);

        this.addAbility(new SpellCastControllerTriggeredAbility(Zone.BATTLEFIELD, new SurveilEffect(2), StaticFilters.FILTER_SPELL_A, false, SetTargetPointer.NONE, Zone.GRAVEYARD));
        // Spells you cast from your graveyard cost {1} less to cast.
        this.addAbility(new SimpleStaticAbility(new SpellsCostReductionControllerEffect(filter, 1)));
    }

    private AminatouFateshifterProdigy(final AminatouFateshifterProdigy card) {
        super(card);
    }

    @Override
    public AminatouFateshifterProdigy copy() {
        return new AminatouFateshifterProdigy(this);
    }
}
