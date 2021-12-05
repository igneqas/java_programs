package GameObjects.TankRelated;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static ExtraUtilities.Constants.*;

public class PlayerTank extends Tank {

    public PlayerTank(int y, int x) {
        super(y, x);
        image = new ImageIcon("playerTank_up.png").getImage();
        super.setObjectSize();
    }

    public void processUserCommand(KeyEvent e) {
        int userCommand = e.getKeyChar();
        char tankCommand = ' ';
        switch(userCommand){
            case W_KEY_CODE:
                tankCommand = DIRECTION_UP;
                break;
            case A_KEY_CODE:
                tankCommand = DIRECTION_LEFT;
                break;
            case S_KEY_CODE:
                tankCommand = DIRECTION_DOWN;
                break;
            case D_KEY_CODE:
                tankCommand = DIRECTION_RIGHT;
                break;
            case SPACE_KEY_CODE:
                tankCommand = TANK_FIRE;
                break;
            default:
                break;
        }
        performTankAction(tankCommand);
    }

    @Override
    protected void updateImage(){
        switch(direction){
            case DIRECTION_UP:
                image = new ImageIcon("playerTank_up.png").getImage();
                break;
            case DIRECTION_LEFT:
                image = new ImageIcon("playerTank_left.png").getImage();
                break;
            case DIRECTION_DOWN:
                image = new ImageIcon("playerTank_down.png").getImage();
                break;
            case DIRECTION_RIGHT:
                image = new ImageIcon("playerTank_right.png").getImage();
                break;
            default:
                break;
        }
    }
}
