package GameMechanics;

import GameObjects.Factories.AbstractEntityFactory;
import GameObjects.Factories.ExplosionFactory;
import GameObjects.*;
import GameObjects.EntityWithHealth;
import GameObjects.TankRelated.Tank;

import java.awt.*;
import java.util.List;


public class CollisionUtility implements BulletList {

    private List<EntityWithHealth> blocks;
    private List<EntityWithHealth> healthEntities;
    private List<Explosion> explosions;
    private AbstractEntityFactory factory;

    public CollisionUtility(List<EntityWithHealth> blocks, List<Explosion> explosions, List<EntityWithHealth> healthEntities) {
        this.blocks = blocks;
        this.explosions = explosions;
        this.healthEntities = healthEntities;
        factory = new ExplosionFactory();
    }

    public boolean tanksCollidesWithBlock(Rectangle tank) {
        for (EntityWithHealth b : blocks) {
            if (b.isVisible() && b.getHitbox().intersects(tank))
                return true;
        }
        return false;
    }

    public void checkCollisionBetweenBulletAndOtherEntity() {
        for (Bullet b : bullets) {
            for (EntityWithHealth x : healthEntities) {
                if (x.isVisible() && b.isVisible() && b.getHitbox().intersects(x.getHitbox())) {
                    x.decreaseHealth();
                    b.setVisible(false);
                    explosions.add((Explosion) factory.createEntity("Explosion", x.getX(), x.getY()));
                }
            }
        }
    }

    public boolean tanksCollide(Rectangle tank, Tank enemyTank) {
        return enemyTank.isVisible() && enemyTank.getHitbox().intersects(tank);
    }
}
