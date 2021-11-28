package GameObjects;

import javax.swing.*;

public class Brick extends Entity {


    public Brick(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("brick.png").getImage();
        this.health = 3;
        super.setObjectSize();
    }
}
