package GameMechanics;

import GameObjects.Factories.AbstractEntityFactory;
import GameObjects.Factories.ExplosionFactory;
import GameObjects.*;
import GameObjects.EntityWithHealth;
import GameObjects.TankRelated.Tank;

import java.awt.*;
import java.util.List;


public class CollisionService implements BulletList{

    private List<EntityWithHealth> entities;
    private List<Explosion> explosions;
    private AbstractEntityFactory factory;

    public CollisionService(List<EntityWithHealth> b, List<Explosion> e){
        entities = b;
        explosions = e;
        factory = new ExplosionFactory();
    }

    public boolean checkCollisionBetweenTankAndBlock(Rectangle tank){
        for(EntityWithHealth b : entities){
            if(b.isVisible() && b.getHitbox().intersects(tank))
                return true;
        }
        return false;
    }

    public void checkCollisionBetweenBulletAndObject() {
        for (Bullet b : bullets) {
            for (EntityWithHealth x : entities) {
                if (x.isVisible() && b.isVisible() && b.getHitbox().intersects(x.getHitbox())) {
                    x.decreaseHealth();
                    b.setVisible(false);
                    explosions.add((Explosion) factory.createEntity("Explosion", x.getX(), x.getY()));
                }
            }
        }
    }

    public void checkCollisionBetweenBulletAndTank(Tank tank){
        for (Bullet b : bullets) {
            if (tank.isVisible() && b.isVisible() && b.getHitbox().intersects(tank.getHitbox())) {
                tank.decreaseHealth();
                b.setVisible(false);
                explosions.add((Explosion) factory.createEntity("Explosion", tank.getX(), tank.getY()));
            }
        }
    }

    public boolean checkCollisionBetweenTanks(Rectangle tank, Tank enemyTank){
        if(enemyTank.isVisible() && enemyTank.getHitbox().intersects(tank)){
            return true;
        }
        else {
            return false;
        }
    }
}
