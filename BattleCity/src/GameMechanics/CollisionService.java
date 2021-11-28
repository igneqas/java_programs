package GameMechanics;

import GameObjects.*;
import GameObjects.Entity;

import java.awt.*;
import java.util.ArrayList;


public class CollisionService {

    private static ArrayList<Entity> entities;
    private static ArrayList<Explosion> explosions;

    static public void initializeCollisionService(ArrayList<Entity> b, ArrayList<Explosion> e, TankAI tank){
        entities = b;
        explosions = e;
    }

    public static boolean checkCollisionBetweenTankAndBlock(Rectangle tank){
        for(Entity b : entities){
            if(b.isVisible() && b.getHitbox().intersects(tank))
                return true;
        }
        return false;
    }

    public static void checkCollisionBetweenBulletAndObject(ArrayList<Bullet> bullets) {
        for (Bullet b : bullets) {
            for (Entity x : entities) {
                if (x.isVisible() && b.isVisible() && b.getHitbox().intersects(x.getHitbox())) {
                    x.decreaseHealth();
                    b.setVisible(false);
                    explosions.add(new Explosion(x.getY(),x.getX()));
                }
            }
        }
    }

    public static void checkCollisionBetweenBulletAndTank(ArrayList<Bullet> bullets, Tank tank){
        for (Bullet b : bullets) {
            if (tank.isVisible() && b.isVisible() && b.getHitbox().intersects(tank.getHitbox())) {
                tank.decreaseHealth();
                b.setVisible(false);
                explosions.add(new Explosion(tank.getY(),tank.getX()));
            }
        }
    }

    public static boolean checkCollisionBetweenTanks(Rectangle tank, Tank enemyTank){
        if(enemyTank.isVisible() && enemyTank.getHitbox().intersects(tank)){
            return true;
        }
        else return false;
    }
}
