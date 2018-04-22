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
    //public static BufferedImage player1Cut[]; //player sheet

    public static BufferedImage ball;     // to store the wrecking ball image

    public static BufferedImage pause; // to store a background image
    public static BufferedImage win; // to store a background image
    public static BufferedImage lose; // to store a background image
    public static BufferedImage restart; // to store a background image
    
    public static BufferedImage[] player1Sprites;
    public static BufferedImage[] player2Sprites;
    
    
        
    /**
     * initializing the images of the game
     */
    public static void init() {
        player1 = ImageLoader.loadImage("/images/p1.png");
        player2 = ImageLoader.loadImage("/images/p2.png");
        ball = ImageLoader.loadImage("/images/Wrecking Ball.png");
        
        player1Sprites = new BufferedImage[2];
        player2Sprites = new BufferedImage[2];
        
        // creating array of images before animations
        SpriteSheet spritesheetP1 = new SpriteSheet(player1);
        SpriteSheet spritesheetP2 = new SpriteSheet(player2);
        // croping the pictures from the sheet into the array
        for(int i = 0; i < 2; i++){
            player1Sprites[i] = spritesheetP1.crop(0, i*384, 384, 384);
            player2Sprites[i] = spritesheetP2.crop(0, i*384, 384, 384);
        }
    }
}
