package Tests;

import GameMechanics.CollisionService;
import GameObjects.TankRelated.AITank;
import GameObjects.TankRelated.PlayerTank;
import GameObjects.TankRelated.Tank;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

public class TankWithTankCollisionTest {

    Tank tankOne = new PlayerTank(100,100);
    CollisionService collisionService = new CollisionService(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    @Test
    public void shouldReturnTrueIfTanksCollide(){
        //Given
        Tank tankTwo = new AITank(69,100,30);

        //When
        boolean tanksCollide = collisionService.checkCollisionBetweenTanks(new Rectangle(tankOne.getX(), tankOne.getY(), tankOne.getWidth(), tankOne.getHeight()), tankTwo);

        //Then
        Assert.assertTrue(tanksCollide);
    }

    @Test
    public void shouldReturnFalseIfTanksDontCollide(){
        //Given
        Tank tankTwo = new AITank(68,105,30);

        //When
        boolean tanksCollide = collisionService.checkCollisionBetweenTanks(new Rectangle(tankOne.getX(), tankOne.getY(), tankOne.getWidth(), tankOne.getHeight()), tankTwo);

        //Then
        Assert.assertFalse(tanksCollide);
    }
}
