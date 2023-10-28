package src;

import java.util.Observable;

import java.util.Random;

/**
 * Model represents the board on which the game state is changed and updated.
 * Contains two player objects, and allows interaction between them. Thus simulating
 * the state and progression of the game.
 */
public class AutoBattlerModel extends Observable {
    private final Player p1;
    private final Player p2;

    /**
     * constructor for board object.
     * Instantiates player objects.
     */
    public AutoBattlerModel() {
        p1 = new Player();
        p2 = new Player();
    }

    /**
     * Executes the attackPhase. Each player takes turns attack with their champions
     * from right to left. Defending champions are chosen at random. Starting player
     * is chosen at random.
     */
    public void attackPhase() {
        Random rng = new Random();
        int attackRound = rng.nextInt(2);
        boolean roundOver = false;

        while (!(isRoundOver())) {
            if (attackRound % 2 == 0) {
                // p1 attacks
                findChamps(rng, p1, p2);
            }
            else {
                // p2 attacks
                findChamps(rng, p2, p1);
            }
            attackRound++;
        }

    }

    /**
     * Finds champions to attack with, and executes one single attack and respective defender's
     * singular defence.
     * Assumes that both player's battlefields are NOT EMPTY.
     * @param rng A Random object for choosing a defending champion to attack.
     * @param attacking Player that is attacking.
     * @param defending Player that is defending.
     */
    private void findChamps(Random rng, Player attacking, Player defending) {
        int i = 0;
        int j;
        Champion attacker = null;
        Champion defender = null;
        while (attacker == null || attacker.getHp() <= 0) {
            attacker = attacking.getBattleField()[i];
            if (i > 5)
                i = 0;
            else
                i++;
        }
        while (defender == null || defender.getHp() <= 0) {
            j = rng.nextInt(7);
            defender = defending.getBattleField()[j];
        }
        executeAttack(attacker, defender);
        // TODO pass attacking and defending players and indices for their battlefield to Observer.
    }

    /**
     * Executes one attack. Subtracts each champions HP by the attack of the other Champion.
     * @param attacker Champion attacking.
     * @param defender Champion defending.
     */
    private void executeAttack(Champion attacker, Champion defender) {
        defender.loseHp(attacker.getAtk());
        attacker.loseHp(defender.getAtk());
    }

    /**
     * Checks both player's battlefields. If either only contains champions with hp <= 0, the
     * round is over.
     * @return true if either player only have champions with hp <= 0. false otherwise.
     */
    private boolean isRoundOver() {
        boolean stillAlive = false;

        for (int i = 0; i < 7; i++) {
            if (p1.getBattleField()[i].getHp() > 0)
                stillAlive = true;
        }
        if (!stillAlive)
            return true;
        for (int i = 0; i < 7; i++) {
            if (p2.getBattleField()[i].getHp() > 0)
                return false;
        }
        return true;
    }

//    /**
//     * Removes champion from given location, returns boolean for whether space contained something to remove.
//     * @param location Location on battlefield with champion to move.
//     * @param player   Player with battlefield to move from.
//     * @return         Boolean, false if the attempted removal space was empty, and true if removal was successful.
//     */
//    public boolean moveToBench(int location, Player player) {
//        if (player.getBattleField()[location] == null)
//            return false;
//
//        player.getBench().add(player.getBattleField()[location]);
//        player.getBattleField()[location] = null;
//        return true;
//    }

}