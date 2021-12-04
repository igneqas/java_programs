package GameObjects;

import javax.swing.*;

public class Leaves extends EntityWithHealth {


    public Leaves(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("trees.png").getImage();
        super.setObjectSize();
    }

    @Override
    public void decreaseHealth() {
        health-=10;
        super.checkHealth();
    }
}
