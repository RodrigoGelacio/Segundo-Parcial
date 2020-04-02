package examensegundoparcial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {
    public static BufferedImage background;
    public static BufferedImage brick;     // to store the player image
    public static BufferedImage sprite;
    public static BufferedImage littleBar;
    public static BufferedImage pause;
    public static BufferedImage gameOver;
    public static BufferedImage spriteBasket;
    public static BufferedImage barIdle[];
    public static BufferedImage barRight[];
    public static BufferedImage barLeft[];
    public static SoundClip score;
    public static SoundClip music;
    
public static BufferedImage portal[];
    /**
     * initializing the images of the game
     */
    public static void init() {
        music = new SoundClip("/sounds/guitarMusic.wav");
        littleBar = ImageLoader.loadImage("/images/littleBar.jpg");
        score = new SoundClip("/sounds/score.wav");
        background = ImageLoader.loadImage("/images/backgroundRocks.jpg");
        brick = ImageLoader.loadImage("/images/brick.png");
        pause = ImageLoader.loadImage("/images/pause.jpg");
        gameOver = ImageLoader.loadImage("/images/gameover.jpg");
        sprite = ImageLoader.loadImage("/images/bat.png");
        Spritesheet spritesheet = new Spritesheet(sprite);
        barIdle = new BufferedImage[4];
        barRight = new BufferedImage[4];
        barLeft = new BufferedImage[4];
         
        for(int i=0; i < 4; i++){
            barIdle[i] = spritesheet.crop(i * 32, 0, 32, 32);
            barRight[i] = spritesheet.crop(i * 32, 32, 32, 32);
            barLeft[i] = spritesheet.crop(i * 32, 96, 32, 32);
        }
    }
    
}


