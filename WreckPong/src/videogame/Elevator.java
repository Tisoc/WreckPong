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
public class Elevator extends Item{

    private final Game game;  // Reference to the game
    private boolean type; // Reference if is player1 or player2
    private Animation animation; // animation of the player
    
    /**
     * Constructor of the player
     * @param x the <b>x</b> position of the player
     * @param y the <b>y</b> position of the player
     * @param width the width of the player
     * @param type the type of the player
     * @param height the height of the player
     * @param game the copy of the game
     */
    public Elevator(int x, int y, int width, int height, boolean type, Game game) {
        super(x, y, width, height);
        this.type = type;
        this.game = game;
        if(type){
            this.animation = new Animation(Assets.player1Sprites, 100);
        }
        else{
            this.animation = new Animation(Assets.player2Sprites, 100);
        }
    }

    /**
     * Get <b>TYpe</b> value
     * @return <b>type of</b> the player betwen player1 and player2
     */
    public boolean isType() {
        return type;
    }

    /**
     * Set <b>type</b> value
     * @param type to modify
     */
    public void setType(boolean type) {
        this.type = type;
    }

    /**
     * Update the sttributes of the player
     */
    @Override
    public void tick() {  
        this.animation.tick();
        // moving players depending on keys
        if(type){
            if (game.getKeyManager().p1up) {
               setY(getY() - 6);
            }
            if (game.getKeyManager().p1down) {
               setY(getY() + 6);
            }
        }
        else{
            if (game.getKeyManager().p2up) {
               setY(getY() - 6);
            }
            if (game.getKeyManager().p2down) {
               setY(getY() + 6);
            }
        }

        // collision with walls
        if (getY() + 100 >= game.getWidth()) {
            setY(game.getWidth() - 100);
        }
        else if (getY() <= 0) {
            setY(0);
        }
    }

    /**
     * Paints the player
     * @param g the grphics to paint the player
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
