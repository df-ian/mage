package mage.cards.h;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.dynamicvalue.common.DevotionCount;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.effects.common.continuous.GainAbilityTargetEffect;
import mage.abilities.effects.common.continuous.LoseCreatureTypeSourceEffect;
import mage.abilities.keyword.IndestructibleAbility;
import mage.abilities.keyword.LifelinkAbility;
import mage.abilities.keyword.VigilanceAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.filter.StaticFilters;
import mage.game.permanent.token.HeliodGodOfTheSunToken;
import mage.target.TargetPermanent;
import mage.target.common.TargetControlledCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class HeliodResplendentOmen extends CardImpl {

    public HeliodResplendentOmen(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT, CardType.CREATURE}, "{2}{W}");
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.GOD);

        this.power = new MageInt(5);
        this.toughness = new MageInt(5);

        // Indestructible
        this.addAbility(IndestructibleAbility.getInstance());

        // As long as your devotion to white is less than five, Heliod isn't a creature.<i>(Each {W} in the mana costs of permanents you control counts towards your devotion to white.)</i>
        this.addAbility(new SimpleStaticAbility(new LoseCreatureTypeSourceEffect(DevotionCount.W, 5))
                .addHint(DevotionCount.W.getHint()));

        this.addAbility(new SpellCastControllerTriggeredAbility(new CreateTokenEffect(new HeliodGodOfTheSunToken()), StaticFilters.FILTER_SPELL_CREATURE, false));

        // Other creatures you control have vigilance.
        this.addAbility(new SimpleStaticAbility(new GainAbilityControlledEffect(
                VigilanceAbility.getInstance(), Duration.WhileOnBattlefield,
                StaticFilters.FILTER_PERMANENT_CREATURES, true
        )));
        Ability ability = new SimpleActivatedAbility(new GainAbilityTargetEffect(
                IndestructibleAbility.getInstance(), Duration.EndOfTurn
        ), new ManaCostsImpl<>("{2}{W}"));
        ability.addTarget(new TargetControlledCreaturePermanent());
        this.addAbility(ability);
        // {2}{W}{W}: Create a 2/1 white Cleric enchantment creature token.
        this.addAbility(ability);

    }

    private HeliodResplendentOmen(final HeliodResplendentOmen card) {
        super(card);
    }

    @Override
    public HeliodResplendentOmen copy() {
        return new HeliodResplendentOmen(this);
    }
}
