package GameObjects;

import javax.swing.*;

public class Border extends Entity {

    public Border(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("edge.png").getImage();
        this.health = 1000000;
        super.setObjectSize();
    }
}
