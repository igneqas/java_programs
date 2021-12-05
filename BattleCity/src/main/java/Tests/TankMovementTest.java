package Tests;

import GameMechanics.CollisionUtility;
import GameObjects.Brick;
import GameObjects.EntityWithHealth;
import GameObjects.TankRelated.AITank;
import GameObjects.TankRelated.PlayerTank;
import GameObjects.TankRelated.Tank;
import GameObjects.TankRelated.TankMovement;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static ExtraUtilities.Constants.*;

public class TankMovementTest {

    Tank enemyTank = new AITank(100, 200, BASIC_TANK_DIRECTION_CHANGE_INTERVAL);
    Tank tank = new PlayerTank(100, 100);

    @Test
    public void shouldMoveTankLeftByOneUnit() {
        //Given
        CollisionUtility collisionUtility = new CollisionUtility(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        tank.setDirection(DIRECTION_LEFT);
        TankMovement tankMovement = new TankMovement(tank, enemyTank, collisionUtility);

        //When
        tankMovement.move();

        //Then
        Assert.assertEquals(99, tank.getX());
        Assert.assertEquals(100, tank.getY());
    }

    @Test
    public void shouldMoveTankRightByOneUnit() {
        //Given
        CollisionUtility collisionUtility = new CollisionUtility(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        tank.setDirection(DIRECTION_RIGHT);
        TankMovement tankMovement = new TankMovement(tank, enemyTank, collisionUtility);

        //When
        tankMovement.move();

        //Then
        Assert.assertEquals(101, tank.getX());
        Assert.assertEquals(100, tank.getY());
    }

    @Test
    public void shouldMoveTankUpByOneUnit() {
        //Given
        CollisionUtility collisionUtility = new CollisionUtility(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        tank.setDirection(DIRECTION_UP);
        TankMovement tankMovement = new TankMovement(tank, enemyTank, collisionUtility);

        //When
        tankMovement.move();

        //Then
        Assert.assertEquals(100, tank.getX());
        Assert.assertEquals(99, tank.getY());
    }

    @Test
    public void shouldMoveTankDownByOneUnit() {
        //Given
        CollisionUtility collisionUtility = new CollisionUtility(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        tank.setDirection(DIRECTION_DOWN);
        TankMovement tankMovement = new TankMovement(tank, enemyTank, collisionUtility);

        //When
        tankMovement.move();

        //Then
        Assert.assertEquals(100, tank.getX());
        Assert.assertEquals(101, tank.getY());
    }

    @Test
    public void shouldNotMoveTankDown() {
        //Given
        List<EntityWithHealth> entities = new ArrayList<>();
        entities.add(new Brick(125, 100));
        CollisionUtility collisionUtility = new CollisionUtility(entities, new ArrayList<>(), entities);
        tank.setDirection(DIRECTION_DOWN);
        TankMovement tankMovement = new TankMovement(tank, enemyTank, collisionUtility);

        //When
        tankMovement.move();

        //Then
        Assert.assertEquals(100, tank.getX());
        Assert.assertEquals(100, tank.getY());
    }

    @Test
    public void shouldMoveTankDown() {
        //Given
        List<EntityWithHealth> entities = new ArrayList<>();
        entities.add(new Brick(126, 100));
        CollisionUtility collisionUtility = new CollisionUtility(entities, new ArrayList<>(), entities);
        tank.setDirection(DIRECTION_DOWN);
        TankMovement tankMovement = new TankMovement(tank, enemyTank, collisionUtility);

        //When
        tankMovement.move();

        //Then
        Assert.assertEquals(100, tank.getX());
        Assert.assertEquals(101, tank.getY());
    }

    @Test
    public void shouldNotMoveTankLeft() {
        //Given
        List<EntityWithHealth> entities = new ArrayList<>();
        entities.add(new Brick(100, 84));
        CollisionUtility collisionUtility = new CollisionUtility(entities, new ArrayList<>(), entities);
        tank.setDirection(DIRECTION_LEFT);
        TankMovement tankMovement = new TankMovement(tank, enemyTank, collisionUtility);

        //When
        tankMovement.move();

        //Then
        Assert.assertEquals(100, tank.getX());
        Assert.assertEquals(100, tank.getY());
    }

    @Test
    public void shouldMoveTankLeft() {
        //Given
        List<EntityWithHealth> entities = new ArrayList<>();
        entities.add(new Brick(100, 83));
        CollisionUtility collisionUtility = new CollisionUtility(entities, new ArrayList<>(), entities);
        tank.setDirection(DIRECTION_LEFT);
        TankMovement tankMovement = new TankMovement(tank, enemyTank, collisionUtility);

        //When
        tankMovement.move();

        //Then
        Assert.assertEquals(99, tank.getX());
        Assert.assertEquals(100, tank.getY());
    }

}
