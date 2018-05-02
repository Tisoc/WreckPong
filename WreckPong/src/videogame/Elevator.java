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
public class Elevator extends Item{

    private final Game game;        // Reference to the game
    private boolean type;           // Reference if is player1 or player2
    private Animation animation;    // animation of the player
    private boolean automatic;      // automatic status
    private int yVel;               // y-axis velocity used in auto mode only
    private long lastTime;          // time used in auto mode only
    
    /**
     * Constructor of the player
     * @param x the <b>x</b> position of the player
     * @param y the <b>y</b> position of the player
     * @param width the width of the player
     * @param type the type of the player
     * @param height the height of the player
     * @param game the copy of the game
     * @param automatic flag for solo/multiplayer mode
     */
    public Elevator(int x, int y, int width, int height, boolean type, Game game, boolean automatic) {
        super(x, y, width, height);
        this.type = type;
        this.game = game;
        this.automatic = automatic;
        lastTime = System.nanoTime();
        yVel = 4;
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
     * Getter for the solo mode status
     * @return the solo mode status
     */
    public boolean isAutomatic() {
        return automatic;
    }

    /**
     * Getter for the y-axis velocity
     * @return the y-axis velocity
     */
    public int getyVel() {
        return yVel;
    }
    
    /**
     * Setter for the y-axis velocity
     * @param yVel the y-axis velocity
     */
    public void setyVel(int yVel) {
        this.yVel = yVel;
    }

    /**
     * Setter for the solo mode status
     * @param automatic the solo mode status
     */
    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
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
        // moving players depending on keys if it¿s not automatic
        if(!isAutomatic()){
            if(isType()){
                if (game.getKeyManager().p2up) {
                   setY(getY() - getyVel());
                }
                if (game.getKeyManager().p2down) {
                   setY(getY() + getyVel());
                }
            }
            else{
                if (game.getKeyManager().p1up) {
                   setY(getY() - getyVel());
                }
                if (game.getKeyManager().p1down) {
                   setY(getY() + getyVel());
                }
            }
        }
        else{
            // there's 70% of probability that it will follow the ball
            long curTime = System.nanoTime();
            if(curTime - lastTime > 100000000){
                lastTime = curTime;
                int decision = (int)(Math.random() * 10);
                if(decision < 7){
                    // follow the ball
                    setyVel( 4 * (int)(Math.signum( (double)(game.getBall().getY() - getY()) )) );
                }
                else{
                    // go the other way
                    setyVel( 4 * (int)(Math.signum( (double)(game.getBall().getY() - getY()) )) );
                }
            }
            setY(getY() + getyVel());
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
