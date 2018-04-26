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
public class Building extends Item{
    private int width;              // the width of the building
    private int height;             // the height of the building
    private int strength;           // the quantity of colitions
    private boolean mainPlayer;     // define the type of building
    private Game game;              // Reference to the game
    
     /**
     * Constructor of the bird
     */    
    public Building(int x, int y, int width, int height, boolean mainPlayer, Game game) {
        super(x,y,width,height);
        this.mainPlayer = mainPlayer;
        this.game = game;
        this.strength = 7;
    }

    /**
     * Setter the strength value
     * @param strength the power
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

     /**
     * Getter for the strength
     * @return the strength of the building
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Getter for the main player
     * @return the object for the main player
     */
    public boolean isMainPlayer() {
        return mainPlayer;
    }
    
    
 
    void damage(){
        strength--;
    }

    /**
     * Update the atributes of the building
     */    
    @Override
    public void tick() {
        // Not much to do here
    }

    /**
     * Paints the Building
     * @param g the grphics to paint the building
     */    
    @Override
    public void render(Graphics g) {
        if(isMainPlayer()){
            g.drawImage(Assets.building1Sprites[7 - strength], getX(), getY(), getWidth(), getHeight(), null);
        }
        else{
            g.drawImage(Assets.building2Sprites[7 - strength], getX(), getY(), getWidth(), getHeight(), null);
        }
    }
}
