/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */
public class Game implements Runnable{
    private BufferStrategy bs;          // to have several buffers when displaying
    private Graphics g;                 // to paint objects
    private Display display;            // to display in the game
    String title;                       // title of the window
    private int width;                  // width of the window
    private int height;                 // height of the window
    private Thread thread;              // thread to create the game
    private boolean running;            // to set the game
    private Ball ball;                  // the wrecking ball
    private Building building1;         //the player1 building of the game
    private Building building2;         //the player2 building of the game
    private boolean paused;             // pause status
//  private boolean death;            // death status
    private Bird bird1;                 //first bird
    private Bird bird2;                 //second bird
    private Elevator player1;           // the main player of the game
    private Elevator player2;           // the secondary player of the game
    private KeyManager keyManager;      // to manage the keyboard
    private int SpeedX;                     // the speed of the bird
//  private FileManager fileManager;  // to load the file manager
//  private int lives;                // amount of lives left
//  private int score;                // score of the player
//  final private int LIVES;          // initial amount of lives
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        running = false;
    }
    
    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

//    public int getScore() {
//        return score;
//    }
    
    /**
     * Getter for player 1
     * @return the player 1
     */
    public Elevator getPlayer1() {
        return player1;
    }
    
    /**
     * Getter for player 2
     * @return the player 2
     */
    public Elevator getPlayer2() {
        return player2;
    }


    /**
     * Getter for the death status of the game
     * @return the death status of the game
     */
//    public boolean isDeath() {
//        return death;
//    }

    /**
     * Setter for the death status of the game
     * @param death the death status of the game
     */
//    public void setDeath(boolean death) {
//        this.death = death;
//    }

    /**
     * Setter for the running status of the game
     * @param running the running status of the game
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

//    public void setScore(int score) {
//        this.score = score;
//    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
        
                    
         display = new Display(title, getWidth(), getHeight());
         ball = new Ball(800, 50, 50, this); 
         // Assets.init();
         building1 = new Building(12, 0, 120, 640, this);
         building2 = new Building(892, 0, 120, 640, this);
         player1 = new Elevator(126, 50, 36, 120, true, this);
         player2 = new Elevator(886, 50, 36, 120, false, this);
         bird1 = new Bird(randomnessX(),randomnessY(),50,30,this);
         bird2 = new Bird(randomnessX(),randomnessY(),50,30,this);
         Assets.init();
         display.getJframe().addKeyListener(keyManager);
    }
    
    private int randomnessY(){
        
        return (int) (Math.random() * 540) + 50;
    }
    
    private int randomnessX(){
        
        int rand = (int) (Math.random() * 2);
        int rand2 = (int) (Math.random() * 924) + 50;
        
        if(rand > 0) {
                       
            return rand2;
            
        } else {
            return rand2 * -1;
        }
    }
    
   
    public int setSpeedX(){
       return this.SpeedX = SpeedX;
    }
    
    public int getSpeedX(){
        return SpeedX;
    }
    
    
    
    /**
     * Runs the game
     */
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        render(); // in case we want to display a losing or winning picture
        // stop(); we should use something like thread.sleep() and then close
    }

    /**
     * Getter for the key manager
     * @return the key manager of the game
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    /**
     * Updates the elements of the game
     */
    private void tick() {
        getKeyManager().tick();
        if(getKeyManager().isPause()){
            getKeyManager().setPause(false);
            paused = !paused;
        }
        if(!paused){
            // tick the elements of the game
            if(getKeyManager().isLoad()){
                FileManager.loadFile(this);
            }
            player1.tick();
            player2.tick();
            ball.tick();
            bird1.tick();
            bird2.tick();
            // check for ball vs building1 collision
            if(ball.intersects(building1)){
                ball.setXvel(ball.getXvel() * (-1));
                building1.damage();
            }
            // check for ball vs building2 collision
            else if(ball.intersects(building2)){
                ball.setXvel(ball.getXvel() * (-1));
                building2.damage();
            } else if(bird1.intersects(building1)){
                
            }
            else{
                // check for ball vs player collision
                if(ball.intersects(player1) || ball.intersects(player2)){
                    ball.setXvel(ball.getXvel() * (-1));
                }      
            }
        }
    }
    
     /**
     * Paints the elements of the game
     */
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else{
            g = bs.getDrawGraphics();          
            // render the elements of the game
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);
            if(running){
                ball.render(g);
                g = bs.getDrawGraphics();  
                building1.render(g);
                building2.render(g);
                player1.render(g);
                player2.render(g);
                bird1.render(g);
                bird2.render(g);
                bs.show();
                g.dispose();
            }
            bs.show();
            g.dispose();
        }
    }
    
    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }
}