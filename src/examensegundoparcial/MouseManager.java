/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examensegundoparcial;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Rodrigo
 */
public class MouseManager implements MouseListener, MouseMotionListener{
    private boolean izquierdo;
    private boolean derecho;
    private Game game;
    private int x;
    private int y;
    
    public MouseManager(Game game){
        this.game = game;
    }
    
    public boolean isIzquierdo(){
        return izquierdo;
    }
    
    public boolean isDerecho(){
        return derecho;
    }
    
    public void setIzquierdo(boolean izquierdo){
        this.izquierdo = izquierdo;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            izquierdo = false;
            x = e.getX();
            y = e.getY();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
            x = e.getX();
            y = e.getY();
        
        if(x >= game.getBall().getX() && x <= game.getBall().getX()+50
                && y >= game.getBall().getY() && y <= game.getBall().getY()+50 && SwingUtilities.isLeftMouseButton(e)){
            izquierdo = true;
            x = e.getX();
            y = e.getY();
        }
         
         
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

    
         
    

    

