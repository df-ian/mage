package mage.cards.u;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.StaticAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.LifeCompareCondition;
import mage.abilities.costs.Cost;
import mage.abilities.costs.OptionalAdditionalModeSourceCosts;
import mage.abilities.dynamicvalue.common.PartyCount;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.DrawDiscardControllerEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.effects.common.counter.AddCountersTargetEffect;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.KickerAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.counters.CounterType;
import mage.filter.common.FilterNonlandPermanent;
import mage.filter.predicate.mageobject.AnotherPredicate;
import mage.game.Game;
import mage.game.permanent.token.SkeletonMenaceToken;
import mage.game.permanent.token.TreasureToken;
import mage.target.common.TargetAnyTarget;
import mage.target.common.TargetControlledCreaturePermanent;
import mage.target.common.TargetCreaturePermanentSameController;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class UndercityAdventurers extends CardImpl {

    public UndercityAdventurers(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{4}{W}");

        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        Ability ability = new EntersBattlefieldTriggeredAbility(new CreateTokenEffect(new TreasureToken()));
        ability.withFirstModeFlavorWord("Stash");
        Mode mode = new Mode(new AddCountersTargetEffect(CounterType.P1P1.createInstance(2)));
        mode.addTarget(new TargetControlledCreaturePermanent());
        mode.withFlavorWord("Forge");
        ability.addMode(mode);
        mode = new Mode(new DrawCardSourceControllerEffect(1));
        mode.withFlavorWord("Archives");
        ability.addMode(mode);
        mode = new Mode(new CreateTokenEffect(new SkeletonMenaceToken()));
        mode.withFlavorWord("Catacombs");
        ability.addMode(mode);

        ability.getModes().setMinModes(0);
        ability.getModes().setMaxModeDyn(PartyCount.instance);

        this.addAbility(ability);

    }

    private UndercityAdventurers(final UndercityAdventurers card) {
        super(card);
    }

    @Override
    public UndercityAdventurers copy() {
        return new UndercityAdventurers(this);
    }
}