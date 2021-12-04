package GameObjects.Factories;

import GameObjects.Entity;

public abstract class AbstractEntityFactory {
    public abstract Entity createEntity(String entityType, int x, int y);
    public abstract Entity createEntity(String entityType, int x, int y, char direction);
    public abstract Entity createEntity(String entityType, int x, int y, int directionChangeInterval);
}
