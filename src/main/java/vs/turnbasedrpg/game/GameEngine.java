package vs.turnbasedrpg.game;

import vs.turnbasedrpg.entities.Player;

import java.util.Objects;

public class GameEngine {

    private final Player hero;
    private final Player enemy;

    private GameEngine(Player hero, Player enemy) {
        this.hero = hero;
        this.enemy = enemy;
    }

    public static GameEngine initialize(Player hero, Player enemy) {
        return new GameEngine(hero, enemy);
    }

    public Player getHero() {
        return hero;
    }

    public Player getEnemy() {
        return enemy;
    }

    public boolean gameInitialized() {
        return Objects.nonNull(hero) && Objects.nonNull(enemy);
    }

    /**
     * <h1>Attack phase</h1>
     * <p>First, the raw damage is calculated. It's the attacker's attack value minus the defender's defense. It's compared to 0
     * and the highest value is kept, this way there can be no negative damage if the attack is lower than the defense.</p>
     * <p>The actual damage is the comparison between the raw damage and the defender's health. The minimum value is kept so
     * that the defender's health is not negative.</p>
     * @param attacker
     * @param defender
     */
    public void attack(Player attacker, Player defender) {
        int raw = Math.max(0, attacker.getAttack() - defender.getDefense());
        int damage = Math.min(raw, defender.getHealth());
        defender.setHealth(defender.getHealth() - damage);
        System.out.printf("%s hits %s for %d DMG (%d HP left).%n", attacker.getName(), defender.getName(), damage, defender.getHealth());
    }

}
