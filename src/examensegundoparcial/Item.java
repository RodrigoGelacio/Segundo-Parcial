package examensegundoparcial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author antoniomejorado
 */
public abstract class Item {

    protected int x;        // to store x position
    protected int y;        // to store y position
    protected int width;
    protected int height;

    /**
     * Set the initial values to create the item
     *
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Get x value
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Get y value
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Set x value
     *
     * @param x to modify
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set y value
     *
     * @param y to modify
     */
    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * To update positions of the item for every tick
     */
    public abstract void tick();

    /**
     * To paint the item
     *
     * @param g <b>Graphics</b> object to paint the item
     */
    public abstract void render(Graphics g);

    /**
     *
     * @param o
     * @return boolean This method checks the collisio of the bar and the ball,
     * it was modifies to special conditions in the width and height
     */
    public boolean collision(Object o) {
        boolean bStatus = false;  //assuming not collision
        if (o instanceof Item) {
            Rectangle rThis = new Rectangle(getX(), getY(), getWidth() + 20, getHeight() - 40); // Made Rectangle smaller so that coin has to touch only the upper part
            Item i = (Item) o;
            Rectangle rOther = new Rectangle(i.getX(), i.getY(), i.getWidth(), i.getHeight());
            bStatus = rThis.contains(rOther);
        }
        return bStatus;
    }

    /**
     *
     * @param o
     * @return boolean Normal implementation of collision method, taking by
     * parameter an object
     */
    public boolean collision3(Object o) {
        boolean bStatus = false;  //assuming not collision
        if (o instanceof Item) {
            Rectangle rThis = new Rectangle(getX(), getY(), getWidth(), getHeight()); // Made Rectangle smaller so that coin has to touch only the upper part
            Item i = (Item) o;
            Rectangle rOther = new Rectangle(i.getX(), i.getY(), i.getWidth(), i.getHeight());
            bStatus = rThis.contains(rOther);
        }
        return bStatus;
    }

    /**
     *
     * @param o
     * @param point
     * @return boolean Special implementation of this method, it is used to
     * check collisions between the bricks and the ball
     */
    public boolean collision2(Object o, Point point) {
        boolean bStatus = false;  //assuming not collision
        if (o instanceof Item) {
            Rectangle rThis = new Rectangle(getX(), getY(), getWidth(), getHeight()); // Made Rectangle smaller so that coin has to touch only the upper part
            Item i = (Item) o;
            Rectangle rOther = new Rectangle(i.getX(), i.getY(), i.getWidth(), i.getHeight());
            bStatus = rThis.contains(point);
        }
        return bStatus;
    }
}
