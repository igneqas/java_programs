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
