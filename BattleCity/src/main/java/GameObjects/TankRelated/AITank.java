package GameObjects.TankRelated;

import javax.swing.*;

import static ExtraUtilities.Constants.*;

public class AITank extends Tank {

    private final AIActionSelector aiActionSelector;

    public AITank(int y, int x, int directionChangeInterval) {
        super(y, x);
        image = new ImageIcon("tank_basic.png").getImage();
        super.setObjectSize();
        aiActionSelector = new AIActionSelector(this, directionChangeInterval);
    }

    public void decideAction() {
        performTankAction(aiActionSelector.decideMove(enemyTank));
    }

    @Override
    protected void updateImage(){
        switch(direction){
            case DIRECTION_UP:
                image = new ImageIcon("tank_basic.png").getImage();
                break;
            case DIRECTION_LEFT:
                image = new ImageIcon("tank_basic_left.png").getImage();
                break;
            case DIRECTION_DOWN:
                image = new ImageIcon("tank_basic_down.png").getImage();
                break;
            case DIRECTION_RIGHT:
                image = new ImageIcon("tank_basic_right.png").getImage();
                break;
            default:
                break;
        }
    }
}
