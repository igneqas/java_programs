package GameObjects;

import GameMechanics.CollisionService;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TankAI extends Tank{

    private int directionChangeTimer = 0;
    private final int DIRECTIONUPDATEINTERVAL = 30;
    private int sinceLastFireTimer = 0;
    private final int FIREUPDATEINTERVAL = 170;

    public TankAI(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("tank_basic.png").getImage();
        this.direction = 'u';
        super.setObjectSize();
    }

    public void decideMove(Tank enemyTank) {
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
            super.move(direction);

        if (sinceLastFireTimer >= FIREUPDATEINTERVAL) {
            super.fire(direction, y, x);
            sinceLastFireTimer = 0;
        } else {
            sinceLastFireTimer++;
        }
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
        changeDirection();
    }

    private void pickDirectionToEnemyTank(Tank enemyTank){
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
        changeDirection();
    }

    private void changeDirection(){
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
        super.move(direction);
    }
}
