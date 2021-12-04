package GameObjects;

import java.awt.*;

public abstract class Entity {
    protected int x,y, health;
    protected Image image;
    protected boolean visible;
    protected int width,height;

    public Entity(int y, int x) {
        this.x = x;
        this.y = y;
        this.visible = true;
        this.health = 10;
    }

    protected void setObjectSize(){
        width = image.getWidth(null);
        height = image.getHeight(null);
    }



    public int getHealth() {
        return health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getHitbox(){
        return new Rectangle(x, y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
