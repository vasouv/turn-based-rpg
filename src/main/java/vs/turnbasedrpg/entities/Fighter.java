package vs.turnbasedrpg.entities;

public interface Fighter {

    String getName();
    int getHealth();
    int getAttack();
    int getDefense();

    void setHealth(int value);
    void setAttack(int value);
    void setDefense(int value);

    default boolean isAlive() { return getHealth() > 0; }
    default boolean isDead() { return !isAlive(); }

}
