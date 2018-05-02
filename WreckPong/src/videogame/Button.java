/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Jaime Eugenio Garza Garza (A01193887)
 * @author Alvaro Márquez Cervantes (A01193509)
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */
public class Button extends Item{

    private final Game game;    // Reference to the game
    private final int type;     // The kind of button
    
    /**
     * Constructor of the player
     * @param x the <b>x</b> position of the player
     * @param y the <b>y</b> position of the player
     * @param width the width of the player
     * @param type the type of the player
     * @param height the height of the player
     * @param game the copy of the game
     */
    public Button(int x, int y, int width, int height, int type, Game game) {
        super(x, y, width, height);
        this.type = type;
        this.game = game;
    }


    /**
     * Update the sttributes of the buttons
     */  
    @Override
    public void tick() {  
     
        if(game.getMouseManager().isLeft())
        {
            if(type==1)
            {
               if(this.contains(game.getMouseManager().getX(), game.getMouseManager().getY()))
               {
                   
               }
            }
            else if(type==2){

            }
        }
                
    }

    /**
     * Paints the buttons
     * @param g the grphics to paint the buttons
     */
    @Override
    public void render(Graphics g) {
        
        if(type==1)
        {
         g.drawImage(Assets.button1, getX(), getY(), getWidth(), getHeight(), null);
        }else if(type==2)
        {
         g.drawImage(Assets.button2, getX(), getY(), getWidth(), getHeight(), null);
        }else if(type==3)
        {
         g.drawImage(Assets.button3, getX(), getY(), getWidth(), getHeight(), null);
        }
        else if(type==4)
        {
         g.drawImage(Assets.button4, getX(), getY(), getWidth(), getHeight(), null);
        }
        else if(type==5)
        {
            g.drawImage(Assets.button5, getX(), getY(), getWidth(), getHeight(), null);
        }

    }
}