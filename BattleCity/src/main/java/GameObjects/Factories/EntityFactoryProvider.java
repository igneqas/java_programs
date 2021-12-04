package GameObjects.Factories;

public class EntityFactoryProvider {
    public static AbstractEntityFactory getFactory(String EntityType) {
        switch (EntityType) {
            case "Bullet":
                return new BulletFactory();
            case "Explosion":
                return new ExplosionFactory();
            case "EntityWithHealth":
                return new EntityWithHealthFactory();
            default:
                return null;
        }
    }
}
