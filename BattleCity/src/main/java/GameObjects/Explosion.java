package GameObjects;

import javax.swing.*;

import static ExtraUtilities.Constants.*;

public class Explosion extends Entity {

    private long timeSinceStartOfExplosion;

    public Explosion(int y, int x) {
        super(y, x);
        image = new ImageIcon("bullet_explosion_1.png").getImage();
        timeSinceStartOfExplosion = System.nanoTime();
    }

    public void updateImage() {
        if (System.nanoTime() / MICROSECONDS_IN_SECOND - timeSinceStartOfExplosion / MICROSECONDS_IN_SECOND >= BULLET_DELAY_FOR_THIRD_ANIMATION_UPDATE) {
            setVisible(false);
        } else if (System.nanoTime() / MICROSECONDS_IN_SECOND - timeSinceStartOfExplosion / MICROSECONDS_IN_SECOND >= BULLET_DELAY_FOR_SECOND_ANIMATION_UPDATE) {
            image = new ImageIcon("bullet_explosion_3.png").getImage();
        } else if (System.nanoTime() / MICROSECONDS_IN_SECOND - timeSinceStartOfExplosion / MICROSECONDS_IN_SECOND >= BULLET_DELAY_FOR_FIRST_ANIMATION_UPDATE) {
            image = new ImageIcon("bullet_explosion_2.png").getImage();
        }
    }
}
