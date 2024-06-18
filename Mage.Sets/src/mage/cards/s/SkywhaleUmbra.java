package mage.cards.s;

import mage.abilities.Ability;
import mage.abilities.common.AttacksTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.AttachEffect;
import mage.abilities.effects.common.UntapAllLandsControllerEffect;
import mage.abilities.effects.common.continuous.BoostEnchantedEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.keyword.EnchantAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.UmbraArmorAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.AttachmentType;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.target.TargetPermanent;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author Ian
 */
public final class SkywhaleUmbra extends CardImpl {

    public SkywhaleUmbra(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{G}{W}");
        this.subtype.add(SubType.AURA);

        // Enchant creature
        TargetPermanent auraTarget = new TargetCreaturePermanent();
        this.getSpellAbility().addTarget(auraTarget);
        this.getSpellAbility().addEffect(new AttachEffect(Outcome.BoostCreature));
        this.addAbility(new EnchantAbility(auraTarget));

        // Enchanted creature gets +2/+2 and has "Whenever this creature attacks, untap all lands you control."
        Ability ability = new SimpleStaticAbility(new BoostEnchantedEffect(2, 2));
        ability.addEffect(new GainAbilityAttachedEffect(FlyingAbility.getInstance(), AttachmentType.AURA).setText("and has flying"));
        this.addAbility(ability);

        // Umbra armor
        this.addAbility(new UmbraArmorAbility());
    }

    private SkywhaleUmbra(final SkywhaleUmbra card) {
        super(card);
    }

    @Override
    public SkywhaleUmbra copy() {
        return new SkywhaleUmbra(this);
    }
}
