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
    public static BufferedImage background; // to store background image
    public static BufferedImage player1;     // to store the player1 image
    public static BufferedImage player2;     // to store the player2 image
    public static BufferedImage building1;
    public static BufferedImage building2;

    public static BufferedImage ball;     // to store the wrecking ball image

    public static BufferedImage pause; // to store a background image
    public static BufferedImage win; // to store a background image
    public static BufferedImage lose; // to store a background image
    public static BufferedImage restart; // to store a background image
    
<<<<<<< HEAD
    public static BufferedImage button1; // to store a background image
    public static BufferedImage button2; // to store a background image

=======
    public static BufferedImage[] player1Sprites;
    public static BufferedImage[] player2Sprites;
    public static BufferedImage[] building1Sprites;
    public static BufferedImage[] building2Sprites;
>>>>>>> 2e8b3f547ea5c711021c37ff30f910a749d0bf9d
    
    
        
    /**
     * initializing the images of the game
     */
    public static void init() {
<<<<<<< HEAD

        background = ImageLoader.loadImage("/images/intro_game.png");
        button1 = ImageLoader.loadImage("/images/SOLO_Button.png");
        button2 = ImageLoader.loadImage("/images/OPTIONS_Button.png");
=======
        background = ImageLoader.loadImage("/images/Background.png");
        player1 = ImageLoader.loadImage("/images/p1.png");
        player2 = ImageLoader.loadImage("/images/p2.png");
        building1 = ImageLoader.loadImage("/images/Building P1.png");
        building2 = ImageLoader.loadImage("/images/Building P1.png");
        ball = ImageLoader.loadImage("/images/Wrecking Ball.png");
>>>>>>> 2e8b3f547ea5c711021c37ff30f910a749d0bf9d
        
        player1Sprites = new BufferedImage[2];
        player2Sprites = new BufferedImage[2];
        
        building1Sprites = new BufferedImage[10];
        building2Sprites = new BufferedImage[10];
        
        // creating array of images before animations
        SpriteSheet spritesheetP1 = new SpriteSheet(player1);
        SpriteSheet spritesheetP2 = new SpriteSheet(player2);
        SpriteSheet spritesheetB1 = new SpriteSheet(building1);
        SpriteSheet spritesheetB2 = new SpriteSheet(building2);
        // croping the pictures from the sheet into the array
        for(int i = 0; i < 2; i++){
            player1Sprites[i] = spritesheetP1.crop(0, i*384, 384, 384);
            player2Sprites[i] = spritesheetP2.crop(0, i*384, 384, 384);
        }
        for(int i = 0; i < 10; i++){
            building1Sprites[i] = spritesheetB1.crop(80 * i, 0, 80, 640);
            building2Sprites[i] = spritesheetB2.crop(80 * i, 0, 80, 640);
        }
    }
}
