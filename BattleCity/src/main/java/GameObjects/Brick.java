package GameObjects;

import javax.swing.*;

public class Brick extends EntityWithHealth {

    public Brick(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("brick.png").getImage();
        super.setObjectSize();
    }

    @Override
    public void decreaseHealth() {
        health-=3;
        super.checkHealth();
    }
}
