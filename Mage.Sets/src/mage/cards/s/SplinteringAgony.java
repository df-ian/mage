
package mage.cards.s;

import mage.abilities.Ability;
import mage.abilities.DelayedTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.common.delayed.AtTheBeginOfNextEndStepDelayedTriggeredAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.AttachEffect;
import mage.abilities.effects.common.CreateTokenCopyTargetEffect;
import mage.abilities.effects.common.ExileTargetEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.keyword.EnchantAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.target.TargetPermanent;
import mage.target.common.TargetCreaturePermanent;
import mage.target.targetpointer.FixedTarget;

import java.util.UUID;

/**
 *
 * @author Ian
 */
public final class SplinteringAgony extends CardImpl {

    public SplinteringAgony(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ENCHANTMENT},"{3}{R}{R}");
        this.subtype.add(SubType.AURA);

        // Enchant creature
        TargetPermanent auraTarget = new TargetCreaturePermanent();
        this.getSpellAbility().addTarget(auraTarget);
        this.getSpellAbility().addEffect(new AttachEffect(Outcome.AddAbility));
        Ability ability = new EnchantAbility(auraTarget);
        this.addAbility(ability);
        // Enchanted creature has "{tap}: Create a token that's a copy of this creature, except it has haste. Exile it at the beginning of the next end step."
        SimpleActivatedAbility gainedAbility = new SimpleActivatedAbility(Zone.BATTLEFIELD, new SplinteringAgonyEffect(), new TapSourceCost());
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new GainAbilityAttachedEffect(gainedAbility, AttachmentType.AURA)));
    }

    private SplinteringAgony(final SplinteringAgony card) {
        super(card);
    }

    @Override
    public SplinteringAgony copy() {
        return new SplinteringAgony(this);
    }
}

class SplinteringAgonyEffect extends OneShotEffect {

    SplinteringAgonyEffect() {
        super(Outcome.PutCreatureInPlay);
        this.staticText = "Create a token that's a copy of this creature, except it has haste. Exile that token at the beginning of the next end step";
    }

    private SplinteringAgonyEffect(final SplinteringAgonyEffect effect) {
        super(effect);
    }

    @Override
    public SplinteringAgonyEffect copy() {
        return new SplinteringAgonyEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Permanent permanent = game.getPermanentOrLKIBattlefield(source.getSourceId());
        if (permanent != null) {
            CreateTokenCopyTargetEffect effect = new CreateTokenCopyTargetEffect(source.getControllerId(), null, true);
            effect.setTargetPointer(new FixedTarget(permanent, game));
            effect.apply(game, source);
            for (Permanent addedToken : effect.getAddedPermanents()) {
                ExileTargetEffect exileEffect = new ExileTargetEffect();
                exileEffect.setTargetPointer(new FixedTarget(addedToken, game));
                DelayedTriggeredAbility delayedAbility = new AtTheBeginOfNextEndStepDelayedTriggeredAbility(exileEffect);
                game.addDelayedTriggeredAbility(delayedAbility, source);
            }
            return true;
        }

        return false;
    }
}
