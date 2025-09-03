package vs.turnbasedrpg.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vs.turnbasedrpg.entities.Hero;

class GameEngineTest {

    @Test
    @DisplayName("Initialize game engine with Hero")
    void initializeWithHero() {
        var vasouv = new Hero("vasouv", 15, 15, 15);

        GameEngine gameEngine = GameEngine.initialize(vasouv);

        Assertions.assertThat(gameEngine).isNotNull();
        Assertions.assertThat(gameEngine.getHero()).isNotNull();
        Assertions.assertThat(gameEngine.getEnemy()).isNull();
    }
}