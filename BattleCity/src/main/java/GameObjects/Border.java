package GameObjects;

import javax.swing.*;

import static ExtraUtilities.Constants.BULLET_DAMAGE_TO_BORDER;

public class Border extends EntityWithHealth {

    public Border(int y, int x) {
        super(y, x);
        image = new ImageIcon("edge.png").getImage();
        health = Integer.MAX_VALUE;
        setObjectSize();
    }

    @Override
    public void decreaseHealth() {
        health -= BULLET_DAMAGE_TO_BORDER;
        checkHealth();
    }

}
