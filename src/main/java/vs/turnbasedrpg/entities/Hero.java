package vs.turnbasedrpg.entities;

public class Hero extends Player {

    public Hero(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    @Override
    public String toString() {
        return Hero.class.getSimpleName() + super.toString();
    }
}
