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
 * @author Arturo Arenas Esparza | A00820982
 */
public class Ball extends Item{
    private int xvel;           // x-axis velocity
    private int ropeLength;     // The length of the rope
    private int ropeLengthChange;
    private int difficulty = 4; // Defines the randomness of the rope length
    private Game game;          // Reference to the game
    private int zoneSize;       // size of each zone
    private int curZone;

    /**
     * Constructor of the ball
     * @param ropeLength the length of the rope
     * @param width the width of the ball
     * @param height the height of the ball
     * @param game a reference to the game
     */
    public Ball(int ropeLength, int width, int height, Game game) {
        super(512 - width / 2, ropeLength - 292 - height, width, height);
        this.ropeLength = ropeLength;
        this.game = game;
        xvel = 5;
        zoneSize = game.getWidth() / 2;
        curZone = 0;
        ropeLengthChange = 0;
    }

    /**
     * Getter for the x-axis velocity
     * @return the x-axis velocity
     */
    public int getXvel() {
        return xvel;
    }

    /**
     * Getter for the difficulty
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Getter for the game
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Getter for the length of the rope
     * @return the length of the rope
     */
    public int getRopeLength() {
        return ropeLength;
    }

    /**
     * Setter got the x-axis velocity
     * @param xvel the x-axis velocity
     */
    public void setXvel(int xvel) {
        this.xvel = xvel;
    }

    /**
     * Setter for the difficulty
     * @param difficulty the difficulty
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Setter for the game
     * @param game the reference to the game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Setter for the length of the rope
     * @param ropeLength the length of the rope
     */
    public void setRopeLength(int ropeLength) {
        this.ropeLength = ropeLength;
    }
    
    public void turnAround(){
        setXvel(getXvel() * (-1));
        // we will also reset the zone size
        zoneSize = game.getWidth() / ((int)(Math.random() * 4) + 1);
        curZone = getX() / zoneSize;
    }

    /**
     * Updates the attributes of the ball
     */
    @Override
    public void tick() {
        // check what zone are we in
        int newZone = getX() / zoneSize;
        if(newZone != curZone){
            curZone = newZone;
            // change the rope length change rate
            int newRopeLengthChange = (int)(Math.random() * 9 - 4);
            ropeLengthChange = newRopeLengthChange;
        }
        setRopeLength(getRopeLength() + ropeLengthChange);
        if(getY() + getHeight() + 5 >= game.getHeight()){
            ropeLengthChange = -3;
        }
        else if(getY() <= 10){
            ropeLengthChange = 3;
        }
        // update x-axis coordinate
        setX( getX() + getXvel() );
        double l = (double)(getRopeLength());
        // calculate y-axis coordinate with respect to the x-axis one
        double dy = Math.sqrt(l * l - Math.pow(Math.abs(512.0 - (double)getX()), 2));
        // adjust y-axis coordinate
        setY((int)dy - 292);
    }

    /**
     * Paints the ball
     * @param g the graphics to paint the ball
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawLine(getGame().getWidth() / 2, getGame().getHeight() - 932, getX() + getWidth() / 2 - 2, getY() + getHeight() / 2 - 25);
        g.drawImage(Assets.ball, getX(), getY(), getWidth(), getHeight(), null);
    }
}
