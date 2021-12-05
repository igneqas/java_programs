package GameObjects;

import static ExtraUtilities.Constants.MINIMUM_ENTITY_HEALTH;
import static ExtraUtilities.Constants.STANDARD_ENTITY_HEALTH;

public abstract class EntityWithHealth extends Entity {

    public EntityWithHealth(int y, int x) {
        super(y, x);
        health = STANDARD_ENTITY_HEALTH;
    }

    protected int health;
    public abstract void decreaseHealth();

    protected void checkHealth() {
        if (health <= MINIMUM_ENTITY_HEALTH) {
            setVisible(false);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
