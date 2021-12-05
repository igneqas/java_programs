package GameObjects;

import javax.swing.*;

import static ExtraUtilities.Constants.*;

public class Bullet extends Entity {

    private char direction;

    public Bullet(int y, int x, char direction) {
        super(y, x);
        this.direction = direction;
        setBulletImageByDirection();
        setObjectSize();
    }

    private void setBulletImageByDirection() {
        switch (direction) {
            case DIRECTION_UP:
                image = new ImageIcon("bullet_up.png").getImage();
                break;
            case DIRECTION_DOWN:
                image = new ImageIcon("bullet_down.png").getImage();
                break;
            case DIRECTION_LEFT:
                image = new ImageIcon("bullet_left.png").getImage();
                break;
            case DIRECTION_RIGHT:
                image = new ImageIcon("bullet_right.png").getImage();
                break;
            default:
                break;
        }
    }

    public void move() {
        switch (direction) {
            case DIRECTION_UP:
                y -= BULLET_MOVEMENT_UNIT;
                break;
            case DIRECTION_DOWN:
                y += BULLET_MOVEMENT_UNIT;
                break;
            case DIRECTION_LEFT:
                x -= BULLET_MOVEMENT_UNIT;
                break;
            case DIRECTION_RIGHT:
                x += BULLET_MOVEMENT_UNIT;
                break;
            default:
                break;
        }
    }
}
