package GameObjects;

import javax.swing.*;

public class Border extends EntityWithHealth {

    public Border(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("edge.png").getImage();
        this.health = Integer.MAX_VALUE;
        super.setObjectSize();
    }

    @Override
    public void decreaseHealth() {
        health--;
        super.checkHealth();
    }

}
