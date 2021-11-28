package GameObjects;

import javax.swing.*;

public class Explosion extends Entity {

    private long timeSinceStartOfExplosion;
    public Explosion(int y, int x) {
        super(y, x);
        this.image = new ImageIcon("bullet_explosion_1.png").getImage();
        timeSinceStartOfExplosion = System.nanoTime();
    }
    public void updateImage(){
        if(System.nanoTime()/100000000 - timeSinceStartOfExplosion/100000000 >= 25)
            image = new ImageIcon("bullet_explosion_2.png").getImage();
        else if (System.nanoTime()/100000000 - timeSinceStartOfExplosion/100000000 >= 50)
            image = new ImageIcon("bullet_explosion_3.png").getImage();
        else if (System.nanoTime()/10000000 - timeSinceStartOfExplosion/10000000 >= 75)
            setVisible(false);
    }
}
