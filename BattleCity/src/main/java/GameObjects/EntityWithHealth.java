package GameObjects;

public abstract class EntityWithHealth extends Entity{

    public EntityWithHealth(int y, int x) {
        super(y, x);
    }

    public abstract void decreaseHealth();

    protected void checkHealth(){
        if(health <= 0){
            super.setVisible(false);
        }
    }


}
