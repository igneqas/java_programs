package GameObjects;

import javax.swing.*;

public class Bullet extends Entity {

    private char direction;

    public Bullet(int y, int x, char direction) {
        super(y, x);
        this.direction = direction;
        setBulletImageByDirection();
        super.setObjectSize();
    }

    private void setBulletImageByDirection(){
        switch(direction){
            case 'u':
                image = new ImageIcon("bullet_up.png").getImage();
                break;
            case 'd':
                image = new ImageIcon("bullet_down.png").getImage();
                break;
            case 'l':
                image = new ImageIcon("bullet_left.png").getImage();
                break;
            case 'r':
                image = new ImageIcon("bullet_right.png").getImage();
                break;
            default:
                break;
        }
    }

    public void move(){
        switch(direction){
            case 'u':
                y-=3;
                break;
            case 'd':
                y+=3;
                break;
            case 'l':
                x-=3;
                break;
            case 'r':
                x+=3;
                break;
            default:
                break;
        }
    }
}
