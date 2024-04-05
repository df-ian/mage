
package mage.cards.s;

import mage.ConditionalMana;
import mage.MageObject;
import mage.Mana;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTappedAbility;
import mage.abilities.condition.Condition;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.mana.ColorlessManaAbility;
import mage.abilities.mana.ConditionalColorlessManaAbility;
import mage.abilities.mana.builder.ConditionalManaBuilder;
import mage.abilities.mana.conditional.ConditionalSpellManaBuilder;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.common.FilterArtifactSpell;
import mage.game.Game;

import java.util.UUID;

/**
 *
 * @author LevelX2
 */
public final class StarlitCairn extends CardImpl {

    public StarlitCairn(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.LAND},"");

        this.addAbility(new EntersBattlefieldTappedAbility());
        this.addAbility(new ColorlessManaAbility());
        // {tap}: Add {C}{C}{C}. Spend this mana only to cast artifact spells.
        this.addAbility(new ConditionalColorlessManaAbility(new TapSourceCost(), 2, new StarlitCairnManaBuilder()));

    }

    private StarlitCairn(final StarlitCairn card) {
        super(card);
    }

    @Override
    public StarlitCairn copy() {
        return new StarlitCairn(this);
    }
}


class StarlitCairnConditionalMana extends ConditionalMana {

    StarlitCairnConditionalMana(Mana mana) {
        super(mana);
        this.staticText = "Spend this mana only to cast artifact spells or activate abilities of artifacts.";
        addCondition(StarlitCairnManaCondition.instance);
    }
}

class StarlitCairnManaBuilder extends ConditionalManaBuilder {

    @Override
    public ConditionalMana build(Object... options) {
        return new StarlitCairnConditionalMana(this.mana);
    }

    @Override
    public String getRule() {
        return "Spend this mana only to cast artifact spells or activate abilities of artifacts.";
    }
}


enum StarlitCairnManaCondition implements Condition {
    instance;

    @Override
    public boolean apply(Game game, Ability source) {
        MageObject object = game.getObject(source);
        if (object != null && object.isArtifact(game)) {
            return true;
        }
        return false;
    }
}