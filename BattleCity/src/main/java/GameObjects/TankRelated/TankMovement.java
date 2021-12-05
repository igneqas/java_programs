package GameObjects.TankRelated;

import GameMechanics.CollisionUtility;

import java.awt.*;

import static ExtraUtilities.Constants.*;

public class TankMovement {

    private Tank tank;
    private Tank enemyTank;
    private CollisionUtility collisionUtility;

    public TankMovement(Tank tank, Tank enemyTank, CollisionUtility collisionUtility) {
        this.tank = tank;
        this.enemyTank = enemyTank;
        this.collisionUtility = collisionUtility;
    }

    public void move() {
        switch (tank.getDirection()) {
            case DIRECTION_UP:
                moveUp();
                break;
            case DIRECTION_DOWN:
                moveDown();
                break;
            case DIRECTION_LEFT:
                moveLeft();
                break;
            case DIRECTION_RIGHT:
                moveRight();
                break;
            default:
                break;
        }
    }

    private void moveUp() {
        if (!collisionUtility.tanksCollidesWithBlock(new Rectangle(tank.getX(), tank.getY() - SINGLE_COORDINATE_UNIT, tank.getWidth(), tank.getHeight())) && !collisionUtility.tanksCollide(new Rectangle(tank.getX(), tank.getY() - SINGLE_COORDINATE_UNIT, tank.getWidth(), tank.getHeight()), enemyTank)) {
            tank.setY(tank.getY() - SINGLE_COORDINATE_UNIT);
        }
    }

    private void moveDown() {
        if (!collisionUtility.tanksCollidesWithBlock(new Rectangle(tank.getX(), tank.getY() + SINGLE_COORDINATE_UNIT, tank.getWidth(), tank.getHeight())) && !collisionUtility.tanksCollide(new Rectangle(tank.getX(), tank.getY() + SINGLE_COORDINATE_UNIT, tank.getWidth(), tank.getHeight()), enemyTank)) {
            tank.setY(tank.getY() + SINGLE_COORDINATE_UNIT);
        }
    }

    private void moveLeft() {
        if (!collisionUtility.tanksCollidesWithBlock(new Rectangle(tank.getX() - SINGLE_COORDINATE_UNIT, tank.getY(), tank.getWidth(), tank.getHeight())) && !collisionUtility.tanksCollide(new Rectangle(tank.getX() - SINGLE_COORDINATE_UNIT, tank.getY(), tank.getWidth(), tank.getHeight()), enemyTank)) {
            tank.setX(tank.getX() - SINGLE_COORDINATE_UNIT);
        }
    }

    private void moveRight() {
        if (!collisionUtility.tanksCollidesWithBlock(new Rectangle(tank.getX() + SINGLE_COORDINATE_UNIT, tank.getY(), tank.getWidth(), tank.getHeight())) && !collisionUtility.tanksCollide(new Rectangle(tank.getX() + SINGLE_COORDINATE_UNIT, tank.getY(), tank.getWidth(), tank.getHeight()), enemyTank)) {
            tank.setX(tank.getX() + SINGLE_COORDINATE_UNIT);
        }
    }
}
