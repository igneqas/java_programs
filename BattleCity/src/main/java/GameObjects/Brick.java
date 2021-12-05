package GameObjects;

import javax.swing.*;

import static ExtraUtilities.Constants.BULLET_DAMAGE_TO_BRICK;

public class Brick extends EntityWithHealth {

    public Brick(int y, int x) {
        super(y, x);
        image = new ImageIcon("brick.png").getImage();
        setObjectSize();
    }

    @Override
    public void decreaseHealth() {
        health -= BULLET_DAMAGE_TO_BRICK;
        checkHealth();
    }
}
