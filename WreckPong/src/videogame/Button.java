/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author sergiosanchez
 */
public class Button extends Item{

    private final Game game;  // Reference to the game
    private final int type;  
    
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
     * @param g the grphics to paint the player
     */
    @Override
    public void render(Graphics g) {
        
        if(type==1)
        {
         g.drawImage(Assets.button1, getX(), getY(), getWidth(), getHeight(), null);
        }else if(type==2)
        {
         g.drawImage(Assets.button2, getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}