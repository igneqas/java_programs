package Tests;

import GameMechanics.CollisionUtility;
import GameObjects.Brick;
import GameObjects.Bullet;
import GameObjects.BulletList;
import GameObjects.EntityWithHealth;
import GameObjects.TankRelated.PlayerTank;
import GameObjects.TankRelated.Tank;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ExtraUtilities.Constants.DIRECTION_DOWN;
import static ExtraUtilities.Constants.DIRECTION_UP;

public class BulletAndOtherEntityCollisionTest implements BulletList {

    @Test
    public void shouldDamageBrickAndMakeBulletInvisible() {
        //Given
        Brick brick = new Brick(100, 100);
        Bullet bullet = new Bullet(95, 103, DIRECTION_UP);
        bullets.add(bullet);
        List<EntityWithHealth> blocks = new ArrayList<>();
        blocks.add(brick);
        CollisionUtility collisionUtility = new CollisionUtility(blocks, new ArrayList<>(), blocks);

        //When
        collisionUtility.checkCollisionBetweenBulletAndOtherEntity();

        //Then
        Assert.assertEquals(6, brick.getHealth());
        Assert.assertFalse(bullet.isVisible());
    }

    @Test
    public void shouldDamageTankAndMakeBulletInvisible() {
        //Given
        Tank tank = new PlayerTank(100, 100);
        Bullet bullet = new Bullet(102, 99, DIRECTION_DOWN);
        bullets.add(bullet);
        List<EntityWithHealth> healthEntities = new ArrayList<>();
        healthEntities.add(tank);
        CollisionUtility collisionUtility = new CollisionUtility(new ArrayList<>(), new ArrayList<>(), healthEntities);

        //When
        collisionUtility.checkCollisionBetweenBulletAndOtherEntity();

        //Then
        Assert.assertEquals(5, tank.getHealth());
        Assert.assertFalse(bullet.isVisible());
    }
}
