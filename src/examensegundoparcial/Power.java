
package examensegundoparcial;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Sergio Tapia
 */
public class Power extends Item {
    private BufferedImage sprite;   // the power's sprite
    private int speed;              // the power's speed
    private boolean generated;        // to check if the power is spawned
    private Game game;              // the running game
    
    /**
     * Constructor for power
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game 
     */
    public Power (int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.sprite = Assets.power;
        this.speed = 2;
        this.generated = true;
        this.game = game;
    }
    
    @Override
    public void tick() {
        
        if (isGenerated()) {
            
            // Move down
            setY( getY() + speed );


            if (getY() + getHeight() / 2 >= game.getHeight()) {
                setGenerated(false);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (isGenerated())
            g.drawImage(sprite, getX(), getY(), getWidth(), getHeight(), null);
    }

    /**
     * Get spawned
     * @return spawned
     */
    public boolean isGenerated() {
        return generated;
    }

    public int getHeight() {
        return height;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getSpeed() {
        return speed;
    }
    
    /**
     * Set spawned
     * @param generated 
     */
    public void setGenerated(boolean val) {
        val = generated;
    }  

    public void setY(int y) {
        this.y = y;
    }
    
    
}
