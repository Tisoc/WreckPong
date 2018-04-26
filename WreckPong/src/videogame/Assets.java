/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */
public class Assets {
    public static BufferedImage startBackground; // to store background image
    public static BufferedImage background; // to store background image

    public static BufferedImage player1;     // to store the player1 image
    public static BufferedImage player2;     // to store the player2 image
    public static BufferedImage building1;
    public static BufferedImage building2;
    public static BufferedImage bird1;
    public static BufferedImage bird2;
    public static BufferedImage health1;
    public static BufferedImage health2;
    public static BufferedImage crane;

    public static BufferedImage ball;     // to store the wrecking ball image

    public static BufferedImage pause; // to store a background image
    public static BufferedImage win; // to store a background image
    public static BufferedImage lose; // to store a background image
    public static BufferedImage restart; // to store a background image
    
    public static BufferedImage button1; // to store a background image
    public static BufferedImage button2; // to store a background image
    public static BufferedImage button3; // to store a background image

    public static BufferedImage[] player1Sprites;
    public static BufferedImage[] player2Sprites;
    public static BufferedImage[] building1Sprites;
    public static BufferedImage[] building2Sprites;
    public static BufferedImage[] bird1Sprites;
    public static BufferedImage[] bird2Sprites;
    public static BufferedImage[] health1Sprites;
    public static BufferedImage[] health2Sprites;
    public static BufferedImage[] craneSprites;
    
        
    /**
     * initializing the images of the game
     */
    public static void init() {

        startBackground = ImageLoader.loadImage("/images/intro_game.png");
        button1 = ImageLoader.loadImage("/images/SOLO_Button.png");
        button2 = ImageLoader.loadImage("/images/OPTIONS_Button.png");
        button3 = ImageLoader.loadImage("/images/MULTI_Button.png");
        background = ImageLoader.loadImage("/images/Background.png");
        player1 = ImageLoader.loadImage("/images/p1.png");
        player2 = ImageLoader.loadImage("/images/p2.png");
        building1 = ImageLoader.loadImage("/images/buildingP1.png");
        building2 = ImageLoader.loadImage("/images/buildingP2.png");
        ball = ImageLoader.loadImage("/images/wreckingBall.png");
        bird1 = ImageLoader.loadImage("/images/blueBird.png");
        bird2 = ImageLoader.loadImage("/images/greenBird.png");
        health1 = ImageLoader.loadImage("/images/healthP1.png");
        health2 = ImageLoader.loadImage("/images/healthP2.png");
        crane = ImageLoader.loadImage("/images/Crane.png");
                
        player1Sprites = new BufferedImage[2];
        player2Sprites = new BufferedImage[2];
        
        building1Sprites = new BufferedImage[10];
        building2Sprites = new BufferedImage[10];
        
        bird1Sprites = new BufferedImage[2];
        bird2Sprites = new BufferedImage[2];
        
        health1Sprites = new BufferedImage[6];
        health2Sprites = new BufferedImage[6];
        
        craneSprites = new BufferedImage[3];
        
        // creating array of images before animations
        SpriteSheet spritesheetP1 = new SpriteSheet(player1);
        SpriteSheet spritesheetP2 = new SpriteSheet(player2);
        SpriteSheet spritesheetB1 = new SpriteSheet(building1);
        SpriteSheet spritesheetB2 = new SpriteSheet(building2);
        SpriteSheet spritesheetBird1 = new SpriteSheet(bird1);
        SpriteSheet spritesheetBird2 = new SpriteSheet(bird2);
        SpriteSheet spritesheetH1 = new SpriteSheet(health1);
        SpriteSheet spritesheetH2 = new SpriteSheet(health2);
        SpriteSheet spritesheetCrane = new SpriteSheet(crane);
        
        // croping the pictures from the sheet into the array
        for(int i = 0; i < 2; i++){
            player1Sprites[i] = spritesheetP1.crop(0, i * 128, 115, 128);
            player2Sprites[i] = spritesheetP2.crop(0, i * 128, 115, 128);
            bird1Sprites[i] = spritesheetBird1.crop(0, i * 25, 31, 25);
            bird2Sprites[i] = spritesheetBird2.crop(0, i * 25, 31, 25);
        }
        for(int i = 0; i < 6; i++){
            health1Sprites[i] = spritesheetH1.crop(0, i * 40, 256, 40);
            health2Sprites[i] = spritesheetH2.crop(0, i * 40, 256, 40);
        }
        for(int i = 0; i < 10; i++){
            building1Sprites[i] = spritesheetB1.crop(80 * i, 0, 80, 640);
            building2Sprites[i] = spritesheetB2.crop(80 * i, 0, 80, 640);
        }
        craneSprites[0] = spritesheetCrane.crop(0, 0, 512, 640);
        craneSprites[1] = spritesheetCrane.crop(512, 0, 512, 640);
        craneSprites[2] = spritesheetCrane.crop(0, 640, 512, 640);
    }
}
