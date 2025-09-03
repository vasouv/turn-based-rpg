package vs.turnbasedrpg.entities;

import java.util.StringJoiner;

public abstract class Player implements Fighter {

    private final String name;
    private int health;
    private int attack;
    private int defense;

    protected Player(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public void setHealth(int value) {
        this.health = value;
    }

    @Override
    public void setAttack(int value) {
        this.attack =  value;
    }

    @Override
    public void setDefense(int value) {
        this.defense = value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("NAME:'" + name + "'")
                .add("HP:" + health)
                .add("ATK:" + attack)
                .add("DEF:" + defense)
                .toString();
    }
}
