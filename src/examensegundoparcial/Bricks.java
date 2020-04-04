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
 *
 * @author Locon
 */
public class Bricks extends Item {

    private int direction;
    private int width;
    private int height;
    private Game game;
    private Animation personIdle; // saves animation for the bricks
    private boolean destroyed; // flag that controls if a block has been destroyed

    public Bricks(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.personIdle = new Animation(Assets.personIdle, 100);
        destroyed = false;
        
    }
    
    /**
     * 
     * @return boolean value destroyed
     */
    boolean isDestroyed() {
        
        return destroyed;
    }
    
    /**
     * 
     * @param val 
     * sets destroyed a boolean value
     */
    void setDestroyed(boolean val) {
        
        destroyed = val;
    }
    
    public int getDirection() {
        return direction;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setWidth(int width) {
        this.width = width;
    }

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
           // g.drawImage(Assets.brick, getX(), getY(), getWidth(), getHeight(), null);
           g.drawImage(personIdle.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }

}
