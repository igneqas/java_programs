package GameObjects;

import javax.swing.*;

public class Leaves extends Entity {
    public Leaves(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("trees.png").getImage();
        this.health = 1;
        super.setObjectSize();
    }
}
