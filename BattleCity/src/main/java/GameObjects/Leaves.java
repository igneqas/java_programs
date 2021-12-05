package GameObjects;

import javax.swing.*;

import static ExtraUtilities.Constants.BULLET_DAMAGE_TO_LEAVES;

public class Leaves extends EntityWithHealth {

    public Leaves(int y, int x) {
        super(y, x);
        image = new ImageIcon("trees.png").getImage();
        setObjectSize();
    }

    @Override
    public void decreaseHealth() {
        health -= BULLET_DAMAGE_TO_LEAVES;
        checkHealth();
    }
}
