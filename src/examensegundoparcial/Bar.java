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
 *
 * @author Rodrigo Torres
 */
public class Bar extends Item {

    private int direction;
    private Game game;
 //   private int frames;
    private Animation barIdle;
    private Animation barRight;
    private Animation barLeft;
    private int moving;

    public Bar(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.barIdle = new Animation(Assets.barIdle, 100);
        this.barRight = new Animation(Assets.barRight, 100);
        this.barLeft = new Animation(Assets.barLeft, 100);
        this.moving = 0;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void tick() {
        if (game.getKeyManager().right) {
            setX(getX() + 2);
            this.barRight.tick();
            moving = 1;
        }
        if (game.getKeyManager().left) {
            setX(getX() - 2);
            this.barLeft.tick();
            moving = -1;
        }

        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        } else if (getX() <= -30) {
            setX(-30);
        }
        
        if( game.getKeyManager().left == false && game.getKeyManager().right == false){
            barIdle.tick();
            moving = 0;
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
