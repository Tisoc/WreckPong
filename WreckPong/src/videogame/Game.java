/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

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
    private Building building1;          //the player1 building of the game
    private Building building2;           //the player2 building of the game
//    private boolean paused;             // pause status
//    private boolean death;              // death status
//    private Player player;              // the player of the game
//    private KeyManager keyManager;      // to manage the keyboard
//    private FileManager fileManager;    // to load the file manager
//    private int lives;                  // amount of lives left
//    private int score;                  // score of the player
//    private TextLoader textloader;      // to print text
//    final private int LIVES;            // initial amount of lives
    
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

    /**
     * Gets the amount of lives left 
     * @return the amount of lives left
     */
//    public int getLives() {
//        return lives;
//    }

//    public int getScore() {
//        return score;
//    }
//
//    public Player getPlayer() {
//        return player;
//    }


    /**
     * Getter for the death status of the game
     * @return the death status of the game
     */
//    public boolean isDeath() {
//        return death;
//    }
    
    

    /**
     * Sets the amount of lives left 
     * @param lives the amount of lives left
     */
//    public void setLives(int lives) {
//        this.lives = lives;
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
//    public void setRunning(boolean running) {
//        this.running = running;
//    }

//    public void setScore(int score) {
//        this.score = score;
//    }
    
    /**
     * Resets initial positions of the elements of the game
     */
//    public void restart(){
//         //
//    }
    
    
//    public void loadingGame(int lives, int score, int playerPosX, int playerPosY, int bulletsSize, int[] BulletsPosX, int[] BulletsPosY, boolean[] BulletsFalling,
//                     int barriersSize, int[] BarriersPosX, int[] BarriersPosY, int[] BarriersPower, int enemiesSize, int[] EnemiesPosX, int[] EnemiesPosY, int[] EnemiesType, boolean[] EnemiesFront){
//        setLives(lives);
//        setScore(score);
//        getPlayer().setX(playerPosX);
//        getPlayer().setY(playerPosY);
//        bullets.clear();
//        for(int i = 0; i < bulletsSize; i++){
//            Bullet b = new Bullet(BulletsPosX[i], BulletsPosY[i], 20, 20, BulletsFalling[i], this);
//            bullets.add(b);
//        }
//        barriers.clear();
//        for(int i = 0; i < barriersSize; i++){
//            Barrier b = new Barrier(BarriersPosX[i], BarriersPosY[i], 100, 125, BarriersPower[i], this);
//            barriers.add(b);
//        }
//        enemies.clear();
//        for(int i = 0; i < enemiesSize; i++){
//            Enemy e = new Enemy(EnemiesPosX[i], EnemiesPosY[i], getHeight() / 3 / 5  + 5, getHeight() / 3 / 5  - 10, 100, EnemiesType[i], EnemiesFront[i], this);
//            enemies.add(e);
//        }
//    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         building1 = new Building(12,0,120,640, this);
         building2 = new Building(892,0,120,640, this);
         Assets.init();
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
            
            tick();
//            // if delta is positive we tick the game
//           if (delta >= 1) {
//                if(!death){
//                    tick();
//                }
//                else{
//                    keyManager.tick();
//                    if(getKeyManager().isRestart()){
//                        setDeath(false);
//                        restart();
//                    }
//                }
//                render();
//                delta --;
//            }
//        }
////        System.out.println("your score was: " + score); // this must be displayed instead
        render(); // in case we want to display a losing or winning picture
        // stop(); we should use something like thread.sleep() and then close
    }
           
    }

    /**
     * Getter for the key manager
     * @return the key manager of the game
     */
//    public KeyManager getKeyManager() {
//        return keyManager;
//    }
    
     /**
     * Getter for the text loader
     * @return the text loader of the game
     */    
//    public TextLoader getTextLoader(){
//        return textloader;
//    }
    
    /**
     * Updates the elements of the game
     */
    private void tick() {
//        keyManager.tick();
//        if(getKeyManager().isPause()){
//            getKeyManager().setPause(false);
//            paused = !paused;
//        }
//        if(!paused){
//            if(getKeyManager().isLoad()){
//                FileManager.loadFile(this);
//            }
//            // ticking the enemies
//            player.tick();
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
        else
         {
           g = bs.getDrawGraphics();  
           building1.render(g);
           building2.render(g);
           bs.show();
           g.dispose();
           
//            // render the elements of the game
//            if(running){
//                if(paused){
//                     g.drawImage(Assets.pause, 0, 0, width, height, null);
//                }
//                else if(this.isDeath()){
//                    g.drawImage(Assets.restart, 0, 0, width, height, null);
//                    
//                }else{
//                    g.drawImage(Assets.background, 0, 0, width, height, null);
//                    // render the player
//                    //player.render(g);
// 
//                    player.render(g);                   
//                }
//            }
//            else{
//                // render lose or win screen
//                if(enemies.size() == 0){
//                    g.drawImage(Assets.win, 0, 0, width, height, null);
//                }
//                else{
//                    g.drawImage(Assets.lose, 0, 0, getWidth() + 100, getHeight(), null);
//                }
//            }
//            bs.show();
//            g.dispose();
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