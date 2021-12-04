package GameObjects.TankRelated;

import GameObjects.TankRelated.Tank;

import java.util.Random;

public class AIActionSelector {
    private int directionChangeTimer = 0;
    private final int directionChangeInterval;
    private Tank tank;

    public AIActionSelector(Tank tank, int directionChangeInterval) {
        this.tank = tank;
        this. directionChangeInterval = directionChangeInterval;
    }

    public char decideMove(Tank enemyTank) {
        Random randomGenerator = new Random();
        if (directionChangeTimer >= directionChangeInterval) {
            int random = randomGenerator.nextInt(60);
            directionChangeTimer = 0;
            if (random % 31 == 0) {
                return pickRandomDirection();
            } else {
                return pickDirectionToEnemyTank(enemyTank);
            }
        } else {
            directionChangeTimer++;
        }
        if (randomGenerator.nextInt(10) % 3 == 0)
            return tank.direction;

        return 'f';

    }

    private char pickRandomDirection(){
        Random randomGenerator = new Random();
        switch (randomGenerator.nextInt(4)){
            case 0:
                return 'u';
            case 1:
                return 'd';
            case 2:
                return 'l';
            case 3:
                return 'r';
            default:
                break;
        }
        return ' ';
    }

    private char pickDirectionToEnemyTank(Tank enemyTank){
        Random randomGenerator = new Random();
        if(randomGenerator.nextInt(2)==0){
            if(tank.getY()<enemyTank.getY()){
                return 'd';
            }
            else{
                return 'u';
            }
        }
        else{
            if(tank.getX()<enemyTank.getX()){
                return 'r';
            }
            else{
                return 'l';
            }
        }
    }
}
