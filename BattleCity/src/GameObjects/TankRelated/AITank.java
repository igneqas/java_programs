package GameObjects.TankRelated;

import javax.swing.*;

public class AITank extends Tank {

    private final AIActionSelector aiActionSelector;

    public AITank(int y, int x, int directionChangeInterval) {
        super(y, x);
        this.image = new ImageIcon("tank_basic.png").getImage();
        super.setObjectSize();
        aiActionSelector = new AIActionSelector(this, directionChangeInterval);
    }

    public void decideAction() {
        performTankAction(aiActionSelector.decideMove(enemyTank));
    }

    /*public void decideMove(PlayerTank enemyTank) {
        Random randomGenerator = new Random();
        if (directionChangeTimer >= DIRECTIONUPDATEINTERVAL) {
            int random = randomGenerator.nextInt(60);
            if (random % 31 == 0) {
                pickRandomDirection();
            } else {
                pickDirectionToEnemyTank(enemyTank);
            }
            directionChangeTimer = 0;
        } else {
            directionChangeTimer++;
        }
        if (randomGenerator.nextInt(10) % 3 == 0)
            super.move();

        fire(direction,y,x);
        *//*if (sinceLastFireTimer >= FIREUPDATEINTERVAL) {
            super.fire(direction, y, x);
            sinceLastFireTimer = 0;
        } else {
            sinceLastFireTimer++;
        }*//*
    }

    private void pickRandomDirection(){
        Random randomGenerator = new Random();
        switch (randomGenerator.nextInt(4)){
            case 0:
                direction = 'u';
                break;
            case 1:
                direction = 'd';
                break;
            case 2:
                direction = 'l';
                break;
            case 3:
                direction = 'r';
                break;
            default:
                break;
        }
        performTankAction();
    }

    private void pickDirectionToEnemyTank(PlayerTank enemyTank){
        Random randomGenerator = new Random();
        if(randomGenerator.nextInt(2)==0){
            if(y<enemyTank.getY()){
                direction = 'd';
            }
            else{
                direction = 'u';
            }
        }
        else{
            if(x<enemyTank.getX()){
                direction = 'r';
            }
            else{
                direction = 'l';
            }
        }
        performTankAction();
    }

    public void performTankAction(){
        changeDirection(direction);
        updateImage();
        move();
    }*/

    @Override
    protected void updateImage(){
        switch(direction){
            case 'u':
                this.image = new ImageIcon("tank_basic.png").getImage();
                break;
            case 'l':
                this.image = new ImageIcon("tank_basic_left.png").getImage();
                break;
            case 'd':
                this.image = new ImageIcon("tank_basic_down.png").getImage();
                break;
            case 'r':
                this.image = new ImageIcon("tank_basic_right.png").getImage();
                break;
            default:
                break;
        }
    }
}
