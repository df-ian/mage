
package mage.cards.a;

import mage.abilities.Mode;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DestroyAllEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.ComparisonType;
import mage.filter.common.FilterArtifactPermanent;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.common.FilterEnchantmentPermanent;
import mage.filter.predicate.mageobject.ManaValuePredicate;
import mage.game.permanent.token.Angel33Token;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class AngelicWrath extends CardImpl {

    private static final FilterCreaturePermanent filter3orLess = new FilterCreaturePermanent("creatures with mana value 3 or less");
    private static final FilterCreaturePermanent filter4orMore = new FilterCreaturePermanent("creatures with mana value 4 or greater");
    static {
        filter3orLess.add(new ManaValuePredicate(ComparisonType.FEWER_THAN, 4));
        filter4orMore.add(new ManaValuePredicate(ComparisonType.MORE_THAN, 3));
    }

    public AngelicWrath(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{3}{W}{W}");


        // Choose two -
        this.getSpellAbility().getModes().setMinModes(2);
        this.getSpellAbility().getModes().setMaxModes(2);
        // Destroy all artifacts;
        this.getSpellAbility().addEffect(new DestroyAllEffect(filter3orLess));
        // or destroy all creatures with converted mana cost 4 or greater.
        Mode mode = new Mode(new DestroyAllEffect(filter4orMore));
        this.getSpellAbility().getModes().addMode(mode);

        mode = new Mode(new CreateTokenEffect(new Angel33Token()));
        this.getSpellAbility().addMode(mode);
    }

    private AngelicWrath(final AngelicWrath card) {
        super(card);
    }

    @Override
    public AngelicWrath copy() {
        return new AngelicWrath(this);
    }
}
