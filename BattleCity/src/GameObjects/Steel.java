package GameObjects;

import javax.swing.*;

public class Steel extends Entity {
    public Steel(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("steel.png").getImage();
        this.health = 5;
        super.setObjectSize();
    }
}
