package GameObjects.TankRelated;

import GameObjects.Bullet;
import GameObjects.BulletList;
import GameObjects.Factories.AbstractEntityFactory;
import GameObjects.Factories.BulletFactory;

import static ExtraUtilities.Constants.*;

public class TankFiring implements BulletList {

    private Tank tank;
    private AbstractEntityFactory factory;

    public TankFiring(Tank tank) {
        this.tank = tank;
        factory = new BulletFactory();
    }

    public void fire(char direction, int y, int x) {
        if (!tank.isEligibleToShoot()) {
            return;
        }
        switch (direction) {
            case DIRECTION_UP:
                bullets.add((Bullet) factory.createEntity("Bullet", x + BULLET_COORDINATE_ADJUSTMENT_ONE, y - BULLET_COORDINATE_ADJUSTMENT_ONE, direction));
                break;
            case DIRECTION_DOWN:
                bullets.add((Bullet) factory.createEntity("Bullet", x + BULLET_COORDINATE_ADJUSTMENT_ONE, y + BULLET_COORDINATE_ADJUSTMENT_TWO, direction));
                break;
            case DIRECTION_LEFT:
                bullets.add((Bullet) factory.createEntity("Bullet", x - BULLET_COORDINATE_ADJUSTMENT_ONE, y + BULLET_COORDINATE_ADJUSTMENT_ONE, direction));
                break;
            case DIRECTION_RIGHT:
                bullets.add((Bullet) factory.createEntity("Bullet", x + BULLET_COORDINATE_ADJUSTMENT_TWO, y + BULLET_COORDINATE_ADJUSTMENT_ONE, direction));
                break;
            default:
                break;
        }
        tank.setTimeSinceShooting(System.nanoTime());
    }
}
