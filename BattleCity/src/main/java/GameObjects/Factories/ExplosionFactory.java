package GameObjects.Factories;

import GameObjects.Entity;
import GameObjects.Explosion;

public class ExplosionFactory extends AbstractEntityFactory {

    @Override
    public Entity createEntity(String entityType, int x, int y) {
        return new Explosion(y, x);
    }

    @Override
    public Entity createEntity(String entityType, int x, int y, char direction) {
        return null;
    }

    @Override
    public Entity createEntity(String entityType, int x, int y, int directionChangeInterval) {
        return null;
    }
}
