package examensegundoparcial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author antoniomejorado
 */
public class Game implements Runnable {

    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Bar bar;                // to use a player
    private LinkedList<Bricks> brick;
    private KeyManager keyManager;  // to manage the keyboard
    private MouseManager mouseManager;
    private Ball ball;
    private Timer timer;
    private TimerTask task;
    private int score;
    private int vidas;
    private int counterVidas;
    private boolean extraVida;
    private boolean vidaAsignada;

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager(this);
        mouseManager = new MouseManager(this);
        score = 0;
        vidas = 5;
        extraVida = false;
        vidaAsignada = false;
    }

    public int getCounterVidas() {
        return counterVidas;
    }

    /**
     *
     * @return ball
     */
    public Bar getBar() {
        return bar;
    }

    public int getVidas() {
        return vidas;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public void setCounterVidas(int counterVidas) {
        this.counterVidas = counterVidas;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        //(getWidth() / 2, getHeight() -120, 100, 70, this)
        bar = new Bar(getWidth() / 2, getHeight() -120, 100, 70, this);
        ball = new Ball(getWidth() / 2, 50, 30, 30, this);
        brick = new LinkedList<Bricks>();
        for (int row = 0; row < 6; row++) { 
            for (int column = 1; column < 7; column++) {
                Bricks brickAux = new Bricks(50 * column, row * 50, 50, 50, this);
                brick.add(brickAux);
            }
        }
        display.getJframe().addKeyListener(keyManager);
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.music.setLooping(true);
        Assets.music.play();
    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 100;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                if (keyManager.isPaused()) {
                        tick();
                    
                }
                render();
                delta--;

            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    private void tick() {
        keyManager.tick();
        bar.tick();
        ball.tick();
        /*
        if(bar.collision(ball)){
            ball.setyVelocity(-ball.getyVelocity());
        }*/
        for(Bricks b : brick){
            b.tick();
        }
        if (counterVidas == 3) {
            vidas--;
            counterVidas = 0;
        }

        if (score % 50 == 0 && score != 0 && !vidaAsignada) {
            extraVida = true;
        }

        if (extraVida) {
            vidas++;
            extraVida = false;
            vidaAsignada = true;
        }

        if (score % 50 != 0) {
            vidaAsignada = false;
        }

    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height - 50, null);
            g.drawImage(Assets.littleBar, 0, height-50, width, 50, null);
            bar.render(g);
            ball.render(g);
            for(Bricks b: brick){
                b.render(g);
            }
            g.setFont(new Font("Tahoma", Font.BOLD, 20));
            g.setColor(Color.GREEN);
            g.drawString("Vidas: " + String.valueOf(vidas), 10, height-10);
            g.drawString("Score: " + String.valueOf(score), 10, height-30);
            if (!keyManager.isPaused()) {
                g.drawImage(Assets.pause, 0, 0, width, height, null);
            }
            bs.show();
            g.dispose();
        }

    }

    public void scoreSound() {
        Assets.score.play();
    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public void Save(String strFileName) {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(strFileName));
            int x = bar.getX();
            int y = bar.getY();
            //save player data
            writer.println("" + vidas + "/" + score + "/" + x + "/" + y);
            writer.close();

        } catch (IOException ioe) {
            System.out.println("File Not found CALL 911");
        }

    }

    //Save Enemies
    public void Load(String strFileName) {
        try {
            FileReader file = new FileReader(strFileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            String datos[];
            line = reader.readLine();
            datos = line.split("/");
            vidas = Integer.parseInt(datos[0]);
            score = Integer.parseInt(datos[1]);
            //Load Player
            int x = Integer.parseInt(datos[2]);
            int y = Integer.parseInt(datos[3]);
            System.out.println("Se leyo  vidas = " + vidas + " y score = " + score + " y X = " + x + " y Y = " + y);
            bar.setX(x);
            bar.setY(y);
            reader.close();
        } catch (IOException e) {
            System.out.println("File Not found CALL 911");
        }
    }

}
