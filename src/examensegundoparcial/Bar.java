package examensegundoparcial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Timer;

/**
 * @author Sergio Tapia
 * @author Rodrigo Torres
 */
public class Bar extends Item {

    private Game game;
    private Animation barIdle;
    private Animation barRight;
    private Animation barLeft;
    private int moving;

    /**
     * Bar constructor
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game
     */
    public Bar(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        this.barIdle = new Animation(Assets.barIdle, 100);
        this.barRight = new Animation(Assets.barRight, 100);
        this.barLeft = new Animation(Assets.barLeft, 100);
        this.moving = 0;
    }

    @Override
    public void tick() {
        if (game.getKeyManager().right) {
            setX(getX() + 2);
            this.barRight.tick();   //tick Right animation so when rendered it actually moves
            moving = 1;             //set moving to 1 so render identifies it should use Right animation
        }
        if (game.getKeyManager().left) { // If pressing left, bar moves to the left
            setX(getX() - 2);
            this.barLeft.tick();    //tick Left animation so when rendered it actually moves
            moving = -1;            //set moving to -1 so render identifies it should use Left animation
        }
        // Setting boundaries to both sides of the screen
        if (getX() + 60 >= game.getWidth()) { // Right side boundary
            setX(game.getWidth() - 60);
        } else if (getX() <= -30) { // Left side boundary
            setX(-30);
        }

        if (game.getKeyManager().left == false && game.getKeyManager().right == false) { // Idle animation when no key is pressed
            barIdle.tick();     //tick Idle animation so when rendered it actually moves
            moving = 0;         //set moving to 0 so render identifies it should use Idle animation
        }

    }

    @Override
    public void render(Graphics g) {
        switch (moving) {
            case 0:
                g.drawImage(barIdle.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 1:
                g.drawImage(barRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case -1:
                g.drawImage(barLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
        }
    }
}
