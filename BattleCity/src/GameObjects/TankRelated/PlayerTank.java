package GameObjects.TankRelated;

import GameObjects.TankRelated.Tank;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class PlayerTank extends Tank {

    public PlayerTank(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("playerTank_up.png").getImage();
        super.setObjectSize();
    }

    public void processUserCommand(KeyEvent e) {
        int userCommand = e.getKeyChar();
        char tankCommand = ' ';
        switch(userCommand){
            case 119:
                tankCommand = 'u';
                break;
            case 97:
                tankCommand = 'l';
                break;
            case 115:
                tankCommand = 'd';
                break;
            case 100:
                tankCommand = 'r';
                break;
            case 32:
                tankCommand = 'f';
                /*if(isEligibleToShoot()) {
                    fire(direction, y, x);
                    timeSinceShooting = System.nanoTime();
                }*/
                break;
            default:
                break;
        }
        performTankAction(tankCommand);
    }



    /*public void performTankCommand(KeyEvent e){
        int userCommand = e.getKeyChar();
        switch(userCommand){
            case 119:
                //this.image = new ImageIcon("playerTank_up.png").getImage();
                changeDirection('u');
                break;
            case 97:
                //this.image = new ImageIcon("playerTank_left.png").getImage();
                changeDirection('l');
                break;
            case 115:
                //this.image = new ImageIcon("playerTank_down.png").getImage();
                changeDirection('d');
                break;
            case 100:
                //this.image = new ImageIcon("playerTank_right.png").getImage();
                changeDirection('r');
                break;
            case 32:
                if(isEligibleToShoot()) {
                    fire(direction, y, x);
                    timeSinceShooting = System.nanoTime();
                }
                return;
            default:
                break;
        }
        updateImage();
        move();
    }*/

    @Override
    protected void updateImage(){
        switch(direction){
            case 'u':
                this.image = new ImageIcon("playerTank_up.png").getImage();
                break;
            case 'l':
                this.image = new ImageIcon("playerTank_left.png").getImage();
                break;
            case 'd':
                this.image = new ImageIcon("playerTank_down.png").getImage();
                break;
            case 'r':
                this.image = new ImageIcon("playerTank_right.png").getImage();
                break;
            default:
                break;
        }
    }
}
