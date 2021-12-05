package Tests;

import GameMechanics.CollisionUtility;
import GameObjects.TankRelated.AITank;
import GameObjects.TankRelated.PlayerTank;
import GameObjects.TankRelated.Tank;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static ExtraUtilities.Constants.BASIC_TANK_DIRECTION_CHANGE_INTERVAL;

public class TankWithTankCollisionTest {

    Tank tankOne = new PlayerTank(100,100);
    CollisionUtility collisionUtility = new CollisionUtility(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    @Test
    public void shouldReturnTrueIfTanksCollide(){
        //Given
        Tank tankTwo = new AITank(69,100,BASIC_TANK_DIRECTION_CHANGE_INTERVAL);

        //When
        boolean tanksCollide = collisionUtility.tanksCollide(new Rectangle(tankOne.getX(), tankOne.getY(), tankOne.getWidth(), tankOne.getHeight()), tankTwo);

        //Then
        Assert.assertTrue(tanksCollide);
    }

    @Test
    public void shouldReturnFalseIfTanksDontCollide(){
        //Given
        Tank tankTwo = new AITank(68,105,BASIC_TANK_DIRECTION_CHANGE_INTERVAL);

        //When
        boolean tanksCollide = collisionUtility.tanksCollide(new Rectangle(tankOne.getX(), tankOne.getY(), tankOne.getWidth(), tankOne.getHeight()), tankTwo);

        //Then
        Assert.assertFalse(tanksCollide);
    }
}
