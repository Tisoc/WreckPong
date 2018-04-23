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
 * @author Alvaro Marquez
 */
public class Bird extends Item {
    
    private Game game;
    
     public Bird(int x, int y, int width, int height, Game game) {
        
        super(x,y,width,height);
        this.game = game;
    
    }
     
      @Override
    public void tick() {
               
        if(getX() < 1025) {
            setX(getX() + 10);
        } else {
             setX(getX() - 10);
        }
        
        
        
    }
    
    @Override
    public void render(Graphics g) {
        
        g.setColor(Color.red);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
                                             
    }
    
}
