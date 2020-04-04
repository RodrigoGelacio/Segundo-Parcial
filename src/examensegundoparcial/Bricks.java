/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examensegundoparcial;

import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Rodrigo Torres
 * @author Sergio Tapia
 */
public class Bricks extends Item {

    private int width;
    private int height;
    private Game game;
    private Animation personIdle; // saves animation for the bricks
    private boolean destroyed; // flag that controls if a block has been destroyed

    /**
     * Bricks constructor
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game
     */
    public Bricks(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.width = width;
        this.height = height;
        this.game = game;
        this.personIdle = new Animation(Assets.personIdle, 100);
        destroyed = false;

    }

    /**
     * provides Destroyed's value
     *
     * @return boolean value destroyed
     */
    boolean isDestroyed() {

        return destroyed;
    }

    /**
     * sets Destroyed's value
     *
     * @param val sets destroyed a boolean value
     */
        
    public void setDestroyed(boolean val) {

        destroyed = val;
    }

    /**
     * gets Width's value
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * gets Height's value
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * sets Width's value
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * sets Height's value
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     */
    @Override
    public void tick() {
        personIdle.tick(); // updates animation
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(personIdle.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }

}
