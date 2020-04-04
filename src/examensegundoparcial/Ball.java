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
public class Ball extends Item {

    private Game game;
    private int yVelocity;
    private int xVelocity;

    public Ball(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        xVelocity = 2;
        yVelocity = 2;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public void tick() {
        // moving player depending on flags and velocity

        
        setX(getX() + xVelocity);
        setY(getY() + yVelocity);

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
