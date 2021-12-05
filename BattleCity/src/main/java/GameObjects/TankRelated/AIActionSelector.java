package GameObjects.TankRelated;

import java.util.Random;

import static ExtraUtilities.Constants.*;

public class AIActionSelector {
    private int directionChangeTimer;
    private final int directionChangeInterval;
    private Tank tank;

    public AIActionSelector(Tank tank, int directionChangeInterval) {
        this.tank = tank;
        this.directionChangeInterval = directionChangeInterval;
        directionChangeTimer = 0;
    }

    public char decideMove(Tank enemyTank) {
        Random randomGenerator = new Random();
        if (directionChangeTimer >= directionChangeInterval) {
            int random = randomGenerator.nextInt(AI_TANK_FIRST_RANDOMIZER_BOUND);
            directionChangeTimer = 0;
            return newDirection(enemyTank, random);
        } else {
            directionChangeTimer++;
        }
        if (randomGenerator.nextInt(AI_TANK_SECOND_RANDOMIZER_BOUND) % AI_TANK_MOVE_FREQUENCY == 0)
            return tank.getDirection();

        return TANK_FIRE;
    }

    private char newDirection(Tank enemyTank, int random) {
        if (random % AI_TANK_RANDOM_DIRECTION_FREQUENCY == 0) {
            return pickRandomDirection();
        } else {
            return pickDirectionToEnemyTank(enemyTank);
        }
    }

    private char pickRandomDirection() {
        Random randomGenerator = new Random();
        switch (randomGenerator.nextInt(4)) {
            case 0:
                return DIRECTION_UP;
            case 1:
                return DIRECTION_DOWN;
            case 2:
                return DIRECTION_LEFT;
            case 3:
                return DIRECTION_RIGHT;
            default:
                break;
        }
        return ' ';
    }

    private char pickDirectionToEnemyTank(Tank enemyTank) {
        Random randomGenerator = new Random();
        if (randomGenerator.nextInt(2) == 0) {
            return getVerticalDirectionToEnemyTank(enemyTank);
        } else {
            return getHorizontalDirectionToEnemyTank(enemyTank);
        }
    }

    private char getHorizontalDirectionToEnemyTank(Tank enemyTank) {
        if (tank.getX() < enemyTank.getX()) {
            return DIRECTION_RIGHT;
        } else {
            return DIRECTION_LEFT;
        }
    }

    private char getVerticalDirectionToEnemyTank(Tank enemyTank) {
        if (tank.getY() < enemyTank.getY()) {
            return DIRECTION_DOWN;
        } else {
            return DIRECTION_UP;
        }
    }
}
