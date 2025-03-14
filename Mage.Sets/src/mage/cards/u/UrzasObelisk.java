package mage.cards.u;

import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.condition.common.SourceEnteredThisTurnCondition;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.GenericManaCost;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.mana.AddManaOfAnyColorEffect;
import mage.abilities.mana.ActivateIfConditionManaAbility;
import mage.abilities.mana.ColorlessManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.game.permanent.token.PhyrexianMiteToken;

import java.util.UUID;

/**
 * @author Ian
 */
public final class UrzasObelisk extends CardImpl {

    public UrzasObelisk(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.LAND}, "");

        this.subtype.add(SubType.URZAS);

        // {T}: Add {C}.
        this.addAbility(new ColorlessManaAbility());

        // {T}: Add one mana of any color. Activate only if Mirrex entered the battlefield this turn.
        this.addAbility(new ActivateIfConditionManaAbility(
                Zone.BATTLEFIELD, new AddManaOfAnyColorEffect(),
                new TapSourceCost(), SourceEnteredThisTurnCondition.instance
        ));

        // {3}, {T}: Create a 1/1 colorless Phyrexian Mite artifact creature token with toxic 1 and "This creature can't block."
        Ability ability = new SimpleActivatedAbility(
                new AddManaOfAnyColorEffect(), new GenericManaCost(1)
        );
        ability.addEffect(new DrawCardSourceControllerEffect(1));
        ability.addCost(new TapSourceCost());
        this.addAbility(ability);
    }

    private UrzasObelisk(final UrzasObelisk card) {
        super(card);
    }

    @Override
    public UrzasObelisk copy() {
        return new UrzasObelisk(this);
    }
}
