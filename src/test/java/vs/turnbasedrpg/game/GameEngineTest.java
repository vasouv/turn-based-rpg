package vs.turnbasedrpg.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vs.turnbasedrpg.entities.Enemy;
import vs.turnbasedrpg.entities.Hero;

import static org.assertj.core.api.Assertions.assertThat;

class GameEngineTest {

    GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        var vasouv = new Hero("vasouv", 100, 20, 5);
        var enemy = new Enemy("enemy", 80, 15, 3);

        gameEngine = GameEngine.initialize(vasouv, enemy);
    }

    @Test
    @DisplayName("Initialize game engine with Hero and Enemy")
    void initializeWithHero() {
        assertThat(gameEngine).isNotNull();
        assertThat(gameEngine.getHero()).isNotNull();
        assertThat(gameEngine.getEnemy()).isNotNull();
    }

    @Test
    @DisplayName("Hero attacks for 17 DMG - enemy health is 63")
    void heroAttacks() {
        gameEngine.attack(gameEngine.getHero(), gameEngine.getEnemy());

        assertThat(gameEngine.getEnemy().getHealth()).isEqualTo(63);
    }

    @Test
    @DisplayName("Enemy attacks for 10 DMG - hero health is 90")
    void enemyAttacks() {
        gameEngine.attack(gameEngine.getEnemy(), gameEngine.getHero());

        assertThat(gameEngine.getHero().getHealth()).isEqualTo(90);
    }

    @Test
    @DisplayName("Hero and enemy attack each other once - both are alive")
    void attackEachOtherOnce() {
        gameEngine.attack(gameEngine.getHero(), gameEngine.getEnemy());
        gameEngine.attack(gameEngine.getEnemy(), gameEngine.getHero());

        assertThat(gameEngine.getHero().isAlive()).isTrue();
        assertThat(gameEngine.getEnemy().isAlive()).isTrue();
    }

    @Test
    @DisplayName("Hero and enemy attack each other twice - both are alive")
    void attackEachOtherTwice() {
        gameEngine.attack(gameEngine.getHero(), gameEngine.getEnemy());
        gameEngine.attack(gameEngine.getEnemy(), gameEngine.getHero());
        gameEngine.attack(gameEngine.getHero(), gameEngine.getEnemy());
        gameEngine.attack(gameEngine.getEnemy(), gameEngine.getHero());

        assertThat(gameEngine.getHero().isAlive()).isTrue();
        assertThat(gameEngine.getEnemy().isAlive()).isTrue();
    }

    @Test
    @DisplayName("Hero attacks 7 times - enemy is dead")
    void enemyDies() {
        for (int i = 0; i < 7; i++) {
            gameEngine.attack(gameEngine.getHero(), gameEngine.getEnemy());
        }
        assertThat(gameEngine.getHero().isAlive()).isTrue();
        assertThat(gameEngine.getEnemy().isDead()).isTrue();
        assertThat(gameEngine.getEnemy().getHealth()).isEqualTo(0);
    }

    @Test
    @DisplayName("Turn 1 - no game over")
    void turn1() {
        gameEngine.turn();
        gameEngine.gameState();

        assertThat(gameEngine.gameOver()).isFalse();
    }

    @Test
    @DisplayName("Turns 7 - game over")
    void turns7() {
        for (int i = 0; i < 7; i++) {
            gameEngine.turn();
        }
        gameEngine.gameState();
        assertThat(gameEngine.gameOver()).isTrue();
    }
}