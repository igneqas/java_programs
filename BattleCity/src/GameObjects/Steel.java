package GameObjects;

import javax.swing.*;

public class Steel extends EntityWithHealth {

    public Steel(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("steel.png").getImage();
        super.setObjectSize();
    }

    @Override
    public void decreaseHealth() {
        super.health-=2;
        super.checkHealth();
    }
}
