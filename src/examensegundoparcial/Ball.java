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

    private int direction;
    private Game game;
    private int counter;
    private int velocityX;
    private double velocityYInitial;
    private double velocityYFinal;
    private double gravity;
    private int barrier;
    private int frames;
    private int rotationNumber;
    private boolean control;
    private long initialTime;
    private long nowTime;
    private long trueTime;
    private boolean controlGravity;
    private int enter;
    private Animation animationRotation1;

    public Ball(int x, int y, int direction, int width, int height, Game game, int counter) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.counter = counter;
        velocityX = 0;
        barrier = 300;
        initialTime = 0;
        velocityYInitial = 0.0;
        velocityYFinal = 0.0;
        gravity = .0981;
        nowTime = 0;
        trueTime = 0;
        control = false;
        controlGravity = false;
        enter = 0;
        frames = 0;
        rotationNumber = 1;
        this.animationRotation1 = new Animation(Assets.rotation1, 100);
    }

    public void setBarrier(int barrier) {
        this.barrier = barrier;
    }
    

    public void setControl(boolean control) {
        this.control = control;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void tick() {
        // moving player depending on flags and velocity
       
        
        if(!game.getMouseManager().isIzquierdo()){
            if(getX() <= barrier){
                if(getX() <= 300){
                    velocityX = (300 - getX())/50;
                }
                
                initialTime = System.nanoTime() - 1000000000;
                velocityYInitial = velocityX * (Math.tan(45));
                barrier = velocityX;
                control = true;
                
            }
            if(!game.getMouseManager().isIzquierdo()){
                this.animationRotation1.tick();
            }
        }
        
        
        if (game.getMouseManager().isIzquierdo()) {
            setX(game.getMouseManager().getX() -50 );
            setY(game.getMouseManager().getY()- 50);
            
        }
       
        
        if (control) {
            setX(getX() + velocityX);
            
            
//            System.out.println((System.nanoTime() - initialTime) / 1000000000);
            velocityYFinal = velocityYInitial - gravity* ((System.nanoTime() - initialTime)/1000000000);
            velocityYInitial = velocityYFinal;
            
            setY(getY() - (int)velocityYInitial);
            
            
            if(velocityYInitial < 1 && velocityYInitial >-7 && !controlGravity){
                initialTime = System.nanoTime() - 1000000000;
                controlGravity = true;
            }
            
        }
        

        // if it hits a wall, it bounces in the opposite direction by pressing 
        //virtually a button.
        if (getX() + 40 >= game.getWidth()) {
            setX(game.getWidth()/2 - (getWidth()/2));
            setY(game.getHeight()/2);
            control = false;
            barrier = 300;
        }
        else if(getX() < 0){
            setX(0);
        }
            
        if (getY() + 40 >= game.getHeight()) {
            setX(game.getWidth()/2 - (getWidth()/2));
            setY(game.getHeight()/2);
            control = false;
            barrier = 300;
            game.setCounterVidas(game.getCounterVidas() + 1);
        } else if (getY() <= 0) {
            setX(game.getWidth()/2 - (getWidth()/2));
            setY(game.getHeight()/2);
            control = false;
            barrier = 300;
            game.setCounterVidas(game.getCounterVidas() + 1);
        }
        
    }

    @Override
    public void render(Graphics g) {
                g.drawImage(animationRotation1.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}

