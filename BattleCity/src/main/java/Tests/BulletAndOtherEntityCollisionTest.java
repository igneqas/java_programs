package Tests;

import GameMechanics.CollisionService;
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

public class BulletAndOtherEntityCollisionTest implements BulletList {

    @Test
    public void shouldDamageBrickAndMakeBulletInvisible() {
        //Given
        Brick brick = new Brick(100, 100);
        Bullet bullet = new Bullet(95, 103, 'u');
        bullets.add(bullet);
        List<EntityWithHealth> blocks = new ArrayList<>();
        blocks.add(brick);
        CollisionService collisionService = new CollisionService(blocks, new ArrayList<>(), blocks);

        //When
        collisionService.checkCollisionBetweenBulletAndOtherEntity();

        //Then
        Assert.assertEquals(7, brick.getHealth());
        Assert.assertFalse(bullet.isVisible());
    }

    @Test
    public void shouldDamageTankAndMakeBulletInvisible() {
        //Given
        Tank tank = new PlayerTank(100, 100);
        Bullet bullet = new Bullet(102, 99, 'd');
        bullets.add(bullet);
        List<EntityWithHealth> healthEntities = new ArrayList<>();
        healthEntities.add(tank);
        CollisionService collisionService = new CollisionService(new ArrayList<>(), new ArrayList<>(), healthEntities);

        //When
        collisionService.checkCollisionBetweenBulletAndOtherEntity();

        //Then
        Assert.assertEquals(5, tank.getHealth());
        Assert.assertFalse(bullet.isVisible());
    }
}
