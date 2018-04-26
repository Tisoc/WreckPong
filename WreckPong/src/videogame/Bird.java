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
    boolean goingRight;
    Animation animation;
    int power;
    
     public Bird(int x, int y, int width, int height, boolean goingRight, int power, Game game) {
        super(x,y,width,height);
        this.game = game;
        this.goingRight = goingRight;
        this.power = power;
        int decision = (int)(Math.random() * 2);
        if(decision == 1){
            if(power % 2 == 1){
                // good bird
                this.animation = new Animation(Assets.bird1Sprites, 100);
            }
            else{
                // evil bird
                this.animation = new Animation(Assets.bird2Sprites, 100);
            }
        }
        else{
            this.animation = new Animation(Assets.bird3Sprites, 100);
        }
    }

    public int getPower() {
        return power;
    }

    public boolean isGoingRight() {
        return goingRight;
    }

    public void setPower(int power) {
        this.power = power;
    }
    
    public void respawn(){
        if(isGoingRight()){
            setX( -(int)(Math.random() * 5000) );
        }
        else{
            setX( game.getWidth() + (int)(Math.random() * 5000) );
        }
        setY( (int)(Math.random() * (game.getHeight() - 100) + 50) );
        // set a new random power
        setPower( (int)( Math.random() * 4 ) + 1 );
        int decision = (int)(Math.random() * 2);
        if(decision == 1){
            if(power % 2 == 1){
                // good bird
                this.animation = new Animation(Assets.bird1Sprites, 100);
            }
            else{
                // evil bird
                this.animation = new Animation(Assets.bird2Sprites, 100);
            }
        }
        else{
            this.animation = new Animation(Assets.bird3Sprites, 100);
        }
    }
     
    @Override
    public void tick() {
        if(isGoingRight()){
            setX( getX() + 10 );
        }
        else{
            setX(getX() - 10);
        }
        if(getX() > game.getWidth() / 2 - 5 && getX() <= game.getWidth() / 2 + 5){
            Assets.birdSpawns.play();
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
