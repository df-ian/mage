package org.mage.test.cards.abilities.keywords;

import mage.constants.PhaseStep;
import mage.constants.Zone;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestPlayerBase;

/**
 * @author escplan9 (Derek Monturo - dmontur1 at gmail dot com) and jimga150
 */
public class ProwlTest extends CardTestPlayerBase {

    @Test
    public void test_ProwlNormal() {
        // Auntie's Snitch {2}{B} Creature — Goblin Rogue (3/1) 
        // Auntie's Snitch can't block.
        // Prowl {1}{B} (You may cast this for its prowl cost if you dealt combat damage to a player this turn with a Goblin or Rogue.)
        // Whenever a Goblin or Rogue you control deals combat damage to a player, if Auntie's Snitch is in your graveyard, you may return Auntie's Snitch to your hand.
        addCard(Zone.HAND, playerA, "Auntie's Snitch");
        addCard(Zone.BATTLEFIELD, playerA, "Swamp", 2);
        //
        // {1}{R} Creature — Goblin Warrior 1/1
        // Red creatures you control have first strike.
        addCard(Zone.BATTLEFIELD, playerA, "Bloodmark Mentor");

        // prepare prowl condition
        checkPlayableAbility("can't", 1, PhaseStep.PRECOMBAT_MAIN, playerA, "Cast Auntie's Snitch", false);
        attack(1, playerA, "Bloodmark Mentor");

        checkPlayableAbility("must play", 1, PhaseStep.POSTCOMBAT_MAIN, playerA, "Cast Auntie's Snitch", true);
        castSpell(1, PhaseStep.POSTCOMBAT_MAIN, playerA, "Auntie's Snitch");
        setChoice(playerA, true); // choosing to pay prowl cost

        setStrictChooseMode(true);
        setStopAt(1, PhaseStep.END_TURN);
        execute();

        assertLife(playerB, 19);
        assertPermanentCount(playerA, "Bloodmark Mentor", 1);
        assertPermanentCount(playerA, "Auntie's Snitch", 1);
    }

    /*
     * Reported bug: Prowl is not taking into consideration other cost reducing effects. For instance Goblin Warchief
     * does not reduce the Prowl cost of other Goblin cards with Prowl ability.
     */
    @Test
    public void test_ProwlWithCostReduce() {
        // Auntie's Snitch {2}{B} Creature — Goblin Rogue (3/1) 
        // Auntie's Snitch can't block.
        // Prowl {1}{B} (You may cast this for its prowl cost if you dealt combat damage to a player this turn with a Goblin or Rogue.)
        // Whenever a Goblin or Rogue you control deals combat damage to a player, if Auntie's Snitch is in your graveyard, you may return Auntie's Snitch to your hand.
        addCard(Zone.HAND, playerA, "Auntie's Snitch");
        addCard(Zone.BATTLEFIELD, playerA, "Swamp", 1);

        // Goblin Warchief {1}{R}{R} Creature — Goblin Warrior (2/2)
        // Goblin spells you cast cost 1 less to cast.
        // Goblin creatures you control have haste.
        addCard(Zone.BATTLEFIELD, playerA, "Goblin Warchief");

        // prepare prowl condition
        checkPlayableAbility("can't", 1, PhaseStep.PRECOMBAT_MAIN, playerA, "Cast Auntie's Snitch", false);
        attack(1, playerA, "Goblin Warchief");

        checkPlayableAbility("must play", 1, PhaseStep.POSTCOMBAT_MAIN, playerA, "Cast Auntie's Snitch", true);
        castSpell(1, PhaseStep.POSTCOMBAT_MAIN, playerA, "Auntie's Snitch"); // should only cost {B} with Warchief discount
        setChoice(playerA, true); // choosing to pay prowl cost

        setStrictChooseMode(true);
        setStopAt(1, PhaseStep.END_TURN);
        execute();

        assertLife(playerB, 18);
        assertPermanentCount(playerA, "Goblin Warchief", 1);
        assertPermanentCount(playerA, "Auntie's Snitch", 1);
    }

    /*
     * Reported bug: you can't cast something for its prowl cost if you could not afford to cast it for its normal cost. The game won't allow me to put the creature on the stack at all.
     * It seems it is only in the case of Hunting Velociraptor and not Prowl in general.
     * GainAbilityControlledSpellsEffect not adding AlternativeSourceCosts in time to check playable
     */
    @Test
    public void test_ProwlWithGainAbilityControlledSpellsEffect() {
        // {2}{R} Creature — Dinosaur (3/2)
        // Dinosaur spells you cast have prowl {2}{R}.
        addCard(Zone.BATTLEFIELD, playerA, "Hunting Velociraptor");
        addCard(Zone.BATTLEFIELD, playerA, "Mountain", 3);
        addCard(Zone.HAND, playerA, "Thrasta, Tempest's Roar");

        // prepare prowl condition
        checkPlayableAbility("can't", 1, PhaseStep.PRECOMBAT_MAIN, playerA, "Cast Thrasta", false);
        attack(1, playerA, "Hunting Velociraptor");

        checkPlayableAbility("must play", 1, PhaseStep.POSTCOMBAT_MAIN, playerA, "Cast Thrasta", true);
        castSpell(1, PhaseStep.POSTCOMBAT_MAIN, playerA, "Thrasta, Tempest's Roar");
        setChoice(playerA, true); // choosing to pay prowl cost

        setStrictChooseMode(true);
        setStopAt(1, PhaseStep.END_TURN);
        execute();

        assertLife(playerB, currentGame.getStartingLife() - 3);
        assertPermanentCount(playerA, "Hunting Velociraptor", 1);
        assertPermanentCount(playerA, "Thrasta, Tempest's Roar", 1);
    }
}
