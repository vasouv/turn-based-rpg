package vs.turnbasedrpg.game;

import vs.turnbasedrpg.entities.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameEngine {

    private final Player hero;
    private final Player enemy;
    private final List<String> turns;
    private int turn;

    private GameEngine(Player hero, Player enemy) {
        this.hero = hero;
        this.enemy = enemy;
        this.turns = new ArrayList<>();
        this.turn = 0;
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

    public List<String> getTurns() {
        return turns;
    }

    public boolean gameInitialized() {
        return Objects.nonNull(hero) && Objects.nonNull(enemy);
    }

    public boolean gameOver() {
        return hero.isDead() || enemy.isDead();
    }

    /**
     * <h1>Attack phase</h1>
     * <p>First, the raw damage is calculated. It's the attacker's attack value minus the defender's defense. It's compared to 0
     * and the highest value is kept, this way there can be no negative damage if the attack is lower than the defense.</p>
     * <p>The actual damage is the comparison between the raw damage and the defender's health. The minimum value is kept so
     * that the defender's health is not negative.</p>
     *
     * @param attacker
     * @param defender
     * @return attack result, which player hits and opponent's health
     */
    public String attack(Player attacker, Player defender) {
        int raw = Math.max(0, attacker.getAttack() - defender.getDefense());
        int damage = Math.min(raw, defender.getHealth());
        defender.setHealth(defender.getHealth() - damage);
        return "%s hits %s for %s DMG (%s HP left).".formatted(attacker.getName(), defender.getName(),
                String.valueOf(damage), String.valueOf(defender.getHealth()));
    }

    /**
     * <h1>Game turn</h1>
     * <p>Hero attacks first, and then the enemy. Before each attack, checks if the opponent is dead and returns
     * winning text.</p>
     */
    public void turn() {

        if (gameOver()) {
            turns.add("Turn %s: %s".formatted(String.valueOf(turn), winner()));
            return;
        }

        turn++;

        turns.add("Turn %s: %s".formatted(String.valueOf(turn), attack(hero, enemy)));

        if (gameOver()) {
            turns.add("Turn %s: %s".formatted(String.valueOf(turn), winner()));
            return;
        }

        turns.add("Turn %s: %s".formatted(String.valueOf(turn), attack(enemy, hero)));
    }

    public void gameState() {
        turns.forEach(System.out::println);
    }

    private String winner() {
        return "Winner: %s".formatted(hero.isAlive() ? hero : enemy);
    }

}
