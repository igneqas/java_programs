package GameObjects.TankRelated;

import GameMechanics.CollisionUtility;
import GameObjects.EntityWithHealth;

import static ExtraUtilities.Constants.*;


public abstract class Tank extends EntityWithHealth {

    private TankMovement tankMovement;
    private TankFiring tankFiring;
    private long timeSinceShooting;
    protected char direction;
    protected Tank enemyTank;


    public Tank(int y, int x) {
        super(y, x);
        direction = DIRECTION_UP;
        timeSinceShooting = System.nanoTime();
        tankFiring = new TankFiring(this);
    }

    @Override
    public void decreaseHealth() {
        health-=5;
        checkHealth();
    }

    private void changeDirection(char newDirection){
        direction = newDirection;
        updateImage();
    }

    protected void performTankAction(char tankCommand){
        if(tankCommand == TANK_FIRE) {
            tankFiring.fire(direction, y, x);
        } else {
            changeDirection(tankCommand);
            tankMovement.move();
        }
    }

    protected abstract void updateImage();

    protected boolean isEligibleToShoot(){
        return System.nanoTime()/NANOSECONDS_IN_SECOND - timeSinceShooting/NANOSECONDS_IN_SECOND >= TANK_SHOOT_DELAY;
    }

    public void initializeTankMovement(CollisionUtility collisionUtility, Tank enemyTank) {
        tankMovement = new TankMovement(this, enemyTank, collisionUtility);
        setEnemyTank(enemyTank);
    }

    public void setEnemyTank(Tank enemyTank) {
        this.enemyTank = enemyTank;
    }

    public Tank getEnemyTank() {
        return enemyTank;
    }

    public long getTimeSinceShooting() {
        return timeSinceShooting;
    }

    public void setTimeSinceShooting(long timeSinceShooting) {
        this.timeSinceShooting = timeSinceShooting;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public TankMovement getTankMovement() {
        return tankMovement;
    }
}
