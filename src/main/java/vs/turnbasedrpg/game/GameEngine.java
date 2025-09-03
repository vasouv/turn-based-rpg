package vs.turnbasedrpg.game;

import vs.turnbasedrpg.entities.Player;

public class GameEngine {

    private final Player hero;
    private final Player enemy;

    private GameEngine(Player hero, Player enemy) {
        this.hero = hero;
        this.enemy = enemy;
    }

    public static GameEngine initialize(Player hero) {
        return new GameEngine(hero, null);
    }

    public Player getHero() {
        return hero;
    }

    public Player getEnemy() {
        return enemy;
    }

}
