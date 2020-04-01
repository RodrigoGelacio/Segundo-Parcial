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
    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage sprite;
    public static BufferedImage pause;
    public static BufferedImage gameOver;
    public static BufferedImage spriteBasket;
    public static BufferedImage rotation1[];
    public static BufferedImage building;
    public static SoundClip score;
    public static SoundClip music;
    
public static BufferedImage portal[];
    /**
     * initializing the images of the game
     */
    public static void init() {
        music = new SoundClip("/sounds/guitarMusic.wav");
        building = ImageLoader.loadImage("/images/building.png");
        score = new SoundClip("/sounds/score.wav");
        background = ImageLoader.loadImage("/images/park.png");
        player = ImageLoader.loadImage("/images/ball.png");
        pause = ImageLoader.loadImage("/images/pause.jpg");
        gameOver = ImageLoader.loadImage("/images/gameover.jpg");
        sprite = ImageLoader.loadImage("/images/coin1.png");
        spriteBasket = ImageLoader.loadImage("/images/fountain.png");
        Spritesheet spritesheet = new Spritesheet(sprite);
        Spritesheet spritesheet2 = new Spritesheet(spriteBasket);
        rotation1 = new BufferedImage[6];
        portal = new BufferedImage[4];
        for(int i=0; i < 6; i++){
            rotation1[i] = spritesheet.crop(i * 116, 0, 116, 146);
        }
        for(int i = 0; i < 4; i++){
            portal[i] = spritesheet2.crop(i * 170, 0, 170, 130);
        }
    }
    
}


