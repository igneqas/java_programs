package GameObjects.TankRelated;

import GameObjects.Bullet;
import GameObjects.BulletList;
import GameObjects.Factories.AbstractEntityFactory;
import GameObjects.Factories.BulletFactory;

public class TankFiring implements BulletList {
    private Tank tank;
    private AbstractEntityFactory factory;

    public TankFiring(Tank tank) {
        this.tank = tank;
        factory = new BulletFactory();
    }

    public void fire(char direction, int y, int x){
        if(!tank.isEligibleToShoot()) {
            return;
        }
        switch(direction){
            case 'u':
                bullets.add((Bullet) factory.createEntity("Bullet",x + tank.getWidth()/3 + 1, y-9, direction));
                break;
            case 'd':
                bullets.add((Bullet) factory.createEntity("Bullet",x + tank.getWidth()/3 + 1, y+29, direction));
                break;
            case 'l':
                bullets.add((Bullet) factory.createEntity("Bullet",x-9, y + tank.getWidth()/3 + 1, direction));
                break;
            case 'r':
                bullets.add((Bullet) factory.createEntity("Bullet",x+29, y + tank.getWidth()/3 + 1, direction));
                break;
            default:
                break;
        }
        tank.setTimeSinceShooting(System.nanoTime());
    }
}
