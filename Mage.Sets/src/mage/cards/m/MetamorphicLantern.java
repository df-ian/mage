package mage.cards.m;

import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.Effect;
import mage.abilities.effects.EntersBattlefieldEffect;
import mage.abilities.effects.common.CopyPermanentEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ComparisonType;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.filter.FilterPermanent;
import mage.filter.StaticFilters;
import mage.filter.common.FilterNonlandPermanent;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.Game;
import mage.util.functions.CopyApplier;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class MetamorphicLantern extends CardImpl {

    public MetamorphicLantern(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{U}");


        CopyApplier phyrexianMetamorphCopyApplier = new CopyApplier() {
            @Override
            public boolean apply(Game game, MageObject blueprint, Ability source, UUID copyToObjectId) {
                blueprint.addCardType(CardType.ARTIFACT);
                return true;
            }
        };

        FilterPermanent filter = new FilterNonlandPermanent();
        filter.add(new ManaValuePredicate(ComparisonType.OR_LESS, 2));

        // {U/P} ( can be paid with either {U} or 2 life.)
        // You may have Phyrexian Metamorph enter the battlefield as a copy of any artifact or creature on the battlefield, except it's an artifact in addition to its other types.
        Effect effect = new CopyPermanentEffect(filter, phyrexianMetamorphCopyApplier);
        effect.setText("You may have {this} enter the battlefield as a copy of any nonland permanent on the battlefield with mana value 2 or less, except its an artifact in addition to its other types.");
        Ability ability = new SimpleStaticAbility(Zone.ALL, new EntersBattlefieldEffect(effect, "", true));
        this.addAbility(ability);

        this.addAbility(new EntersBattlefieldTriggeredAbility(new DrawCardSourceControllerEffect(1)));
    }

    private MetamorphicLantern(final MetamorphicLantern card) {
        super(card);
    }

    @Override
    public MetamorphicLantern copy() {
        return new MetamorphicLantern(this);
    }

}
