package examensegundoparcial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
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
    private Bar bar;                // to bar of the game
    private LinkedList<Bricks> brick; //list of blocks in the game
    private KeyManager keyManager;  // to manage the keyboard
    private Ball ball; // ball of the game
    private int score; // score of the game
    private int vidas; // lives of the game
    private int counterVidas; //counter of lives
    private boolean extraVida; //bonus lives
    private boolean vidaAsignada; 
    private MouseManager mouseManager;
    private int blocksNum = 36;

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
        score = 0;
        vidas = 5;
        extraVida = false;
        vidaAsignada = false;
    }
    
    /**
     * 
     * @return contador de vidas
     */
    public int getCounterVidas() {
        return counterVidas;
    }

    /**
     *
     * @return bar
     */
    public Bar getBar() {
        return bar;
    }
    
    /**
     * 
     * @return vidas
     */
    public int getVidas() {
        return vidas;
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
    
    /**
     * 
     * @param vidas 
     * Modify value of lives
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
    
    /**
     * 
     * @param counterVidas
     * Modify counter of lives
     */
    public void setCounterVidas(int counterVidas) {
        this.counterVidas = counterVidas;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        bar = new Bar(getWidth() / 2 - 50, getHeight() -120, 100, 70, this);
        ball = new Ball(getWidth() / 2, 400, 20, 20, this);
        brick = new LinkedList<Bricks>();
        
        //loading position of the bricks in the screen
        for (int row = 0; row < 6; row++) { 
            for (int column = 2; column < 8; column++) {
                Bricks brickAux = new Bricks(50 * column, row * 50, 50, 50, this);
                brick.add(brickAux);
            }
        }
        display.getJframe().addKeyListener(keyManager);
        Assets.music.setLooping(true);
        Assets.music.play();
    }
    
    /**
     * Method that plays the sound when the ball hits the bar
     */
    public void pop(){
        Assets.pop.play();
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
                if (keyManager.isPaused()) { //checks if the game is on pause
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
        if(vidas != 0 && blocksNum != 0){
        keyManager.tick();
        bar.tick();
        ball.tick();
        
        //checks if the ball has collisiones with the bar
        if(bar.collision(ball)){
            pop(); //plays bo sound
            
            int paddleLPos = bar.getX();
            int ballLPos = ball.getX();
            
            int first = paddleLPos + 20;
            int second = paddleLPos + 40;
            int third = paddleLPos + 60;
            int fourth = paddleLPos + 80;
            
            //modifies the direction of the ball if it lands in the first section of the bar
            if (ballLPos < first) {

                ball.setxVelocity(-2);
                ball.setyVelocity(-2);
            }

            //modifies the direction of the ball if it lands in the second section of the bar
            if (ballLPos >= first && ballLPos < second) {

                ball.setxVelocity(-2);
                ball.setyVelocity(-2);
            }
            
            //modifies the direction of the ball if it lands in the third section of the bar
            if (ballLPos >= second && ballLPos < third) {

                ball.setxVelocity(0);
                ball.setyVelocity(-2);
            }

            //modifies the direction of the ball if it lands in the fourth section of the bar
            if (ballLPos >= third && ballLPos < fourth) {
                
                ball.setxVelocity(2);
                ball.setyVelocity(-2);
            }
            
            //modifies the direction of the ball if it lands in the last section of the bar
            if (ballLPos > fourth) {
                ball.setxVelocity(2);
                ball.setyVelocity(-2);
            }
            
        }
        
        //updates the bricks status
        for(int i=0; i < brick.size();i++){
            Bricks b = (Bricks) brick.get(i); //saving in a new object in order to function properly
            b.tick();
            
            //checks if the ball has hit a brick
            if (b.collision3(ball)) { 
                int ballLeft = ball.getX(); // saves the x value of the ball at all moments
                int ballHeight = ball.getHeight(); // saves the height of the ball
                int ballWidth = (int) ball.getWidth(); // saves width of the ball
                int ballTop = (int) ball.getY(); // saves the y position at all times
                
                //creates a Point object for evry corner of the ball
                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
                
                //checks if the brick has already been destroyed
                if (!b.isDestroyed()) {
                    
                    //Uses a different collisioin to know where has the ball hit the brick
                    if (b.collision2(ball,pointRight)) {
                        ball.setxVelocity(-2);
                    } else if (b.collision2(ball,pointLeft)) {
                        ball.setxVelocity(2);
                    }

                    if (b.collision2(ball,pointTop)) {
                        ball.setyVelocity(2);
                    } else if (b.collision2(ball,pointBottom)) {
                        ball.setyVelocity(-2);
                    }

                    b.setDestroyed(true); // sets the brick destroyed
                    scoreSound();
                    score += 20;
                    blocksNum--;
                }
            }
        }
        
        
        if (counterVidas == 3) {
            vidas--;
            counterVidas = 0;
        }

        if (score % 120 == 0 && score != 0 && !vidaAsignada) {
            extraVida = true;
        }

        if (extraVida) {
            vidas++;
            extraVida = false;
            vidaAsignada = true;
        }

        if (score % 120 != 0) {
            vidaAsignada = false;
        }
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
            g.drawImage(Assets.background, 0, 0, width, height - 50, null); //draws background
            g.drawImage(Assets.littleBar, 0, height-50, width, 50, null); // draws the wooden bar at the bottom
            bar.render(g);
            ball.render(g);
            for(int i=0 ; i< brick.size(); i++){
                Bricks b = (Bricks) brick.get(i);
                if(!b.isDestroyed()){ //in case that the brick is destryoed, it doesnt render
                    b.render(g);
                }
            }
            g.setFont(new Font("Tahoma", Font.BOLD, 20));
            g.setColor(Color.GREEN);
            g.drawString("Vidas: " + String.valueOf(vidas), 10, height-10);
            g.drawString("Score: " + String.valueOf(score), 10, height-30);
            if (!keyManager.isPaused()) {
                g.drawImage(Assets.pause, 0, 0, width, height, null);
            }
            if (vidas == 0) {
                g.drawImage(Assets.gameOver, 0, 0, width, height, null);    // If no more lives are left, displays game over screen
                Assets.music.stop();                                        // Stops the music
            }
             if (blocksNum == 0) {
                g.drawImage(Assets.win, 0, 0, width, height, null);    // If no more lives are left, displays game over screen
                Assets.music.stop();                                        // Stops the music
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

    /**
     * 
     * @param strFileName 
     * Saves the position of the bar and the ball in a text document.
     */
    public void Save(String strFileName) {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(strFileName));
            int xP = bar.getX();
            int yP = bar.getY();
            int xB = ball.getX();
            int yB = ball.getY();
            int xVelP = ball.getxVelocity();
            int yVelP = ball.getyVelocity();
            //save player data
            writer.println("" + vidas + "/" + score + "/" + xP + "/" + yP + "/" + xB + "/" + yB + "/" + xVelP + "/" + yVelP); 
            writer.close();

        } catch (IOException ioe) {
            System.out.println("File Not found CALL 911");
        }

    }

    //Load the saved positions of the bar and ball
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
            int xP = Integer.parseInt(datos[2]);
            int yP = Integer.parseInt(datos[3]);
            int xB = Integer.parseInt(datos[4]);
            int yB = Integer.parseInt(datos[5]);
            int xVelP = Integer.parseInt(datos[6]);
            int yVelP = Integer.parseInt(datos[7]);
            System.out.println("Se leyo  vidas = " + vidas + " y score = " + score + " y X = " + xP + " y Y = " + yP);
            bar.setX(xP);
            bar.setY(yP);
            ball.setX(xB);
            ball.setY(yB);
            ball.setxVelocity(xVelP);
            ball.setyVelocity(yVelP);
            reader.close();
        } catch (IOException e) {
            System.out.println("File Not found CALL 911");
        }
    }

}
