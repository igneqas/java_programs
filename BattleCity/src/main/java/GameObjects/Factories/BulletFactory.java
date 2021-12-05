package GameObjects.Factories;

import GameObjects.Bullet;
import GameObjects.Entity;

public class BulletFactory extends AbstractEntityFactory {

    @Override
    public Entity createEntity(String entityType, int x, int y) {
        return null;
    }

    @Override
    public Entity createEntity(String entityType, int x, int y, char direction) {
        return new Bullet(y, x, direction);
    }

    @Override
    public Entity createEntity(String entityType, int x, int y, int directionChangeInterval) {
        return null;
    }
}
