package GameObjects;

import javax.swing.*;

import static ExtraUtilities.Constants.BULLET_DAMAGE_TO_STEEL;

public class Steel extends EntityWithHealth {

    public Steel(int y, int x) {
        super(y, x);
        image = new ImageIcon("steel.png").getImage();
        setObjectSize();
    }

    @Override
    public void decreaseHealth() {
        health -= BULLET_DAMAGE_TO_STEEL;
        checkHealth();
    }
}
