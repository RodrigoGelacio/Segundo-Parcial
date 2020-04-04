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
public class Ball extends Item {

    private Game game;
    private int yVelocity;
    private int xVelocity;

    /**
     * Ball constructor
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game
     */
    public Ball(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        xVelocity = 2;
        yVelocity = 2;
    }

    /**
     * get xVelocity
     *
     * @return xVelocity
     */
    public int getxVelocity() {
        return xVelocity;
    }

    /**
     * get yVelocity
     *
     * @return yVelocity
     */
    public int getyVelocity() {
        return yVelocity;
    }

    /**
     * sets xVelocity
     *
     * @param xVelocity
     */
    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * sets yVelocity
     *
     * @param yVelocity
     */
    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public void tick() {

        setX(getX() + xVelocity); //assigns velocity on X axis
        setY(getY() + yVelocity); //assigns velocity on Y axis

        //Bouncing in wall
        
        if(getX()+30 >= game.getWidth()){
            xVelocity = -xVelocity;
        }
        else if(getX() <= 0){
            xVelocity = -xVelocity;
        }
        if(getY() + 30 >= game.getHeight() - 50){
            setX(game.getWidth() / 2);
            setY(game.getHeight() / 2);
            xVelocity = 0;
            game.setVidas(game.getVidas() - 1);
        }
        else if(getY() <= 0){
            yVelocity = -yVelocity;
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ball, getX(), getY(), getWidth(), getHeight(), null);
    }
}
