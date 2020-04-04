package examensegundoparcial;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Sergio Tapia
 */
public class Power extends Item {

    private BufferedImage sprite;   // Sprite used for the power
    private int speed;              // Power's falling speed
    private boolean generated;      // Boolean that is true when power is generated
    private Game game;              // Actual game

    /**
     * Constructor for power
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game
     */
    public Power(int x, int y, int width, int height, Game game) {
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
            setY(getY() + speed);

            if (getY() + getHeight() / 2 >= game.getHeight()) {
                setGenerated(false);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (isGenerated()) {
            g.drawImage(sprite, getX(), getY(), getWidth(), getHeight(), null);
        }
    }

    /**
     * get generated
     *
     * @return generated
     */
    public boolean isGenerated() {
        return generated;
    }

    /**
     * get height
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * get y
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * get x
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * get speed
     *
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * set generated
     *
     * @param generated
     */
    public void setGenerated(boolean val) {
        val = generated;
    }

    /**
     * set y
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

}
