package examensegundoparcial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Rodrigo Torres
 */
public class KeyManager implements KeyListener {

    public boolean paused; // flag pause the game
    public boolean save;
    public boolean right;
    public boolean left;
    private Game game;

    private boolean keys[];  // to store all the flags for every key

    public KeyManager(Game game) {
        keys = new boolean[256];
        paused = true;
        this.game = game;
    }

    public boolean isPaused() {
        return paused;
    }

    @Override

    public void keyTyped(KeyEvent e) {
    }

    /**
     * Function: when a key is pressed the other ones are set to false.
     * Function: It also copies the speed if there is a change between hor and
     * vertical speed.
     *
     * @param key object
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if (e.getKeyCode() == KeyEvent.VK_S) {
            game.Save("game");
        }
        if (e.getKeyCode() == KeyEvent.VK_C) {
            game.Load("game");
        }
    }

    /**
     * function: this is a function that serves to virtually click a button.
     *
     * @param char
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        keys[e.getKeyCode()] = false;
        if (e.getKeyCode() == KeyEvent.VK_P) {
            paused = !paused;
        }

    }

    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }
}
