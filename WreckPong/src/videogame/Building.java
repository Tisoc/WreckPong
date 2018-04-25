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
    private int strength;
    private Game game;
    
    public Building(int x, int y, int width, int height, Game game) {
        
        super(x,y,width,height);
        this.game = game;
        this.strength = 7;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }
 
    void damage(){
        strength--;
    }
    
    @Override
    public void tick() {
        // Not much to do here
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.building1Sprites[7 - strength], getX(), getY(), getWidth(), getHeight(), null);
    }
}
