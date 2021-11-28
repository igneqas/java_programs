package GameObjects;

import GameMechanics.CollisionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Tank extends Entity {
    private ArrayList<Bullet> bullets;
    protected char direction;
    private long timeSinceShooting;
    private Tank enemyTank;

    public Tank(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("playerTank_up.png").getImage();
        this.direction = 'u';
        this.health = 2;
        this.bullets = new ArrayList<>();
        this.timeSinceShooting = System.nanoTime();
        super.setObjectSize();
    }


    protected void move(char moveDirection){
        direction = moveDirection;
            switch (moveDirection) {
                case 'u':
                    if(!CollisionService.checkCollisionBetweenTankAndBlock(new Rectangle(x,y-1,width,height)) && !CollisionService.checkCollisionBetweenTanks(new Rectangle(x,y-1,width,height),enemyTank)) {
                        y--;
                    }
                    break;
                case 'd':
                    if(!CollisionService.checkCollisionBetweenTankAndBlock(new Rectangle(x,y+1,width,height))&& !CollisionService.checkCollisionBetweenTanks(new Rectangle(x,y+1,width,height),enemyTank)){
                        y++;
                    }
                    break;
                case 'l':
                    if(!CollisionService.checkCollisionBetweenTankAndBlock(new Rectangle(x-1,y,width,height)) && !CollisionService.checkCollisionBetweenTanks(new Rectangle(x+1,y,width,height),enemyTank)) {
                        x--;
                    }
                    break;
                case 'r':
                    if(!CollisionService.checkCollisionBetweenTankAndBlock(new Rectangle(x+1,y,width,height)) && !CollisionService.checkCollisionBetweenTanks(new Rectangle(x+1,y,width,height),enemyTank)) {
                        x++;
                    }
                    break;
                default:
                    break;
            }

    }

    public void keyPressed(KeyEvent e){
        int userCommand = e.getKeyChar();
        switch(userCommand){
            case 119:
                this.image = new ImageIcon("playerTank_up.png").getImage();
                move('u');
                break;
            case 97:
                this.image = new ImageIcon("playerTank_left.png").getImage();
                move('l');
                break;
            case 115:
                this.image = new ImageIcon("playerTank_down.png").getImage();
                move('d');
                break;
            case 100:
                this.image = new ImageIcon("playerTank_right.png").getImage();
                move('r');
                break;
            case 32:
                if(isEligibleToShoot()) {
                    fire(direction, y, x);
                    timeSinceShooting = System.nanoTime();
                }
            default:
                break;
        }
    }

    protected void fire(char direction, int y, int x){

        switch(direction){
            case 'u':
                bullets.add(new Bullet(y - 7, x + width/3 + 1, direction));
                break;
            case 'd':
                bullets.add(new Bullet(y + 25, x + width/3 + 1, direction));
                break;
            case 'l':
                bullets.add(new Bullet(y + width/3 + 1, x - 7, direction));
                break;
            case 'r':
                bullets.add(new Bullet(y + width/3 + 1, x + 25, direction));
                break;
            default:
                break;
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    private boolean isEligibleToShoot(){
        return System.nanoTime()/1000000000 - timeSinceShooting/1000000000 >= 3;
    }

    public void setEnemyTank(Tank enemyTank) {
        this.enemyTank = enemyTank;
    }

    public Tank getEnemyTank() {
        return enemyTank;
    }
}
