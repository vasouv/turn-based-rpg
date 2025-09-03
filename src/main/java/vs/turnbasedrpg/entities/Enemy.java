package vs.turnbasedrpg.entities;

public class Enemy extends Player {

    public Enemy(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    @Override
    public String toString() {
        return Enemy.class.getSimpleName() + super.toString();
    }
}
