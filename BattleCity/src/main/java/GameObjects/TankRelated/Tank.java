package GameObjects.TankRelated;

import GameMechanics.CollisionService;
import GameObjects.EntityWithHealth;
import GameObjects.Factories.AbstractEntityFactory;
import GameObjects.Factories.BulletFactory;


public abstract class Tank extends EntityWithHealth {

    protected char direction;
    protected long timeSinceShooting;
    protected Tank enemyTank;
    AbstractEntityFactory factory;
    private TankMovement tankMovement;
    private TankFiring tankFiring;

    public Tank(int y, int x) {
        super(y, x);
        this.direction = 'u';
        this.timeSinceShooting = System.nanoTime();
        tankFiring = new TankFiring(this);
        factory = new BulletFactory();
    }

    @Override
    public void decreaseHealth() {
        super.health-=5;
        super.checkHealth();
    }

    protected void changeDirection(char newDirection){
        direction = newDirection;
        updateImage();
    }

    public void performTankAction(char tankCommand){
        if(tankCommand == 'f') {
            tankFiring.fire(direction, y, x);
        } else {
            changeDirection(tankCommand);
            tankMovement.move();
        }
    }

    protected abstract void updateImage();

    protected boolean isEligibleToShoot(){
        return System.nanoTime()/1000000000 - timeSinceShooting/1000000000 >= 3;
    }

    public void initializeTankMovement(CollisionService collisionService, Tank enemyTank) {
        tankMovement = new TankMovement(this, enemyTank, collisionService);
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
}
