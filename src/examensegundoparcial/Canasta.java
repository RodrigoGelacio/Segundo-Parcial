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
public class Canasta extends Item {

    private int direction;
    private int width;
    private int height;
    private boolean isBumpingWall = false;  // Booleano para saber si choco con la pared
    int frames = 0;                         // variable para contar frames
    private Game game;
    private Animation animationPortal;

    public Canasta(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.animationPortal = new Animation(Assets.portal, 150);
        
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
        this.animationPortal.tick();
        
    }

    @Override
    public void render(Graphics g) {
            g.drawImage(animationPortal.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        
    }

}
