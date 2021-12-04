package Tests;

import GameMechanics.CollisionService;
import GameObjects.Brick;
import GameObjects.EntityWithHealth;
import GameObjects.TankRelated.AITank;
import GameObjects.TankRelated.PlayerTank;
import GameObjects.TankRelated.Tank;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TankMovementTest {

    Tank enemyTank = new AITank(100, 200, 30);
    Tank tank = new PlayerTank(100,100);

    @Test
    public void shouldMoveTankLeftByOneUnit() {
        //Given
        CollisionService collisionService = new CollisionService(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        tank.initializeTankMovement(collisionService, enemyTank);

        //When
        tank.performTankAction('l');

        //Then
        Assert.assertEquals(99, tank.getX());
        Assert.assertEquals(100, tank.getY());
    }

    @Test
    public void shouldMoveTankRightByOneUnit() {
        //Given
        CollisionService collisionService = new CollisionService(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        tank.initializeTankMovement(collisionService, enemyTank);

        //When
        tank.performTankAction('r');

        //Then
        Assert.assertEquals(101, tank.getX());
        Assert.assertEquals(100, tank.getY());
    }

    @Test
    public void shouldMoveTankUpByOneUnit() {
        //Given
        CollisionService collisionService = new CollisionService(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        tank.initializeTankMovement(collisionService, enemyTank);

        //When
        tank.performTankAction('u');

        //Then
        Assert.assertEquals(100, tank.getX());
        Assert.assertEquals(99, tank.getY());
    }

    @Test
    public void shouldMoveTankDownByOneUnit() {
        //Given
        CollisionService collisionService = new CollisionService(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        tank.initializeTankMovement(collisionService, enemyTank);

        //When
        tank.performTankAction('d');

        //Then
        Assert.assertEquals(100, tank.getX());
        Assert.assertEquals(101, tank.getY());
    }

    @Test
    public void shouldNotMoveTankDown() {
        //Given
        List<EntityWithHealth> entities = new ArrayList<>();
        entities.add(new Brick(125,100));
        CollisionService collisionService = new CollisionService(entities, new ArrayList<>(), entities);
        tank.initializeTankMovement(collisionService, enemyTank);

        //When
        tank.performTankAction('d');

        //Then
        Assert.assertEquals(100, tank.getX());
        Assert.assertEquals(100, tank.getY());
    }

    @Test
    public void shouldMoveTankDown() {
        //Given
        List<EntityWithHealth> entities = new ArrayList<>();
        entities.add(new Brick(126,100));
        CollisionService collisionService = new CollisionService(entities, new ArrayList<>(), entities);
        tank.initializeTankMovement(collisionService, enemyTank);

        //When
        tank.performTankAction('d');

        //Then
        Assert.assertEquals(100, tank.getX());
        Assert.assertEquals(101, tank.getY());
    }

    @Test
    public void shouldNotMoveTankLeft() {
        //Given
        List<EntityWithHealth> entities = new ArrayList<>();
        entities.add(new Brick(100,84));
        CollisionService collisionService = new CollisionService(entities, new ArrayList<>(), entities);
        tank.initializeTankMovement(collisionService, enemyTank);

        //When
        tank.performTankAction('l');

        //Then
        Assert.assertEquals(100, tank.getX());
        Assert.assertEquals(100, tank.getY());
    }

    @Test
    public void shouldMoveTankLeft() {
        //Given
        List<EntityWithHealth> entities = new ArrayList<>();
        entities.add(new Brick(100,83));
        CollisionService collisionService = new CollisionService(entities, new ArrayList<>(), entities);
        tank.initializeTankMovement(collisionService, enemyTank);

        //When
        tank.performTankAction('l');

        //Then
        Assert.assertEquals(99, tank.getX());
        Assert.assertEquals(100, tank.getY());
    }
}
