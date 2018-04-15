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
public class Building extends Item{
    
    private int width;
    private int height;
    private Game game;
    
    public Building(int x, int y, int width, int height, Game game) {
        
        super(x,y,width,height);
        this.game = game;
    
    }
    
    void damage(){
        // TODO: Damage the building
    }
    
    @Override
    public void tick() {
        
    }
    
    @Override
    public void render(Graphics g) {
        
        g.setColor(Color.blue);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
                     
          
          
        
    }
}
