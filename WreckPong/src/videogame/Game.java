/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

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
    private Building building1;         // the player1's building of the game
    private Building building2;         // the player2's building of the game
    private boolean paused;             // pause status
    private boolean death;              // death status
    private Bird bird1;                 // first bird
    private Bird bird2;                 // second bird
    private Elevator player1;           // the main player of the game
    private Elevator player2;           // the secondary player of the game
    private KeyManager keyManager;      // to manage the keyboard
    private MouseManager mouseManager; // to manage the mouse
    private int livesP1;                // amount of lives left for player1
    private int livesP2;                // amount of lives left for player2
    private int score;                  // score of the player - COMPLETE SOLO MODE
    private boolean solo;               // solo game status
    final private int LIVES;            // initial amount of lives
    private ArrayList<Perk> perks;      // array of perks
    private boolean intro;              // to validate if the game is in the intro
    private boolean start;              // to validate if the game is in the intro
    private boolean game;               // to know if we are playing
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private int SpeedX;                     // the speed of the bird
    
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
        mouseManager = new MouseManager();
        running = false;
        death = false;
        paused = false;
        perks = new ArrayList<Perk>();
        intro = false;
        start = true;
        game = false;
        LIVES = 5;
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

    public int getScore() {
        return score;
    }
    
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

    public Ball getBall() {
        return ball;
    }
    
    /**
     * Getter for the death status of the game
     * @return the death status of the game
     */
    public boolean isDeath() {
        return death;
    }

    /**
     * Setter for the death status of the game
     * @param death the death status of the game
     */
    public void setDeath(boolean death) {
        this.death = death;
    }

    /**
     * Setter for the running status of the game
     * @param running the running status of the game
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    /**
     * initializing the display window of the game
     */
    private void init() {
        Assets.init();
        Assets.birdSpawns.play();
        
        display = new Display(title, getWidth(), getHeight());
        ball = new Ball(800, 45, 57, this); 
        building1 = new Building(12, 0, 120, 640, true, this);
        building2 = new Building(892, 0, 120, 640, false, this);
        player1 = new Elevator(60, 50, 94, 105, true, this, false);
        player2 = new Elevator(870, 50, 94, 105, false, this, false);
        bird1 = new Bird(randomRange(0, 5000, false), randomRange(50, getHeight() - 50, true), 50, 30, true, 1, this);
        bird2 = new Bird(randomRange(0, 5000, true), randomRange(50, getHeight() - 50, true), 50, 30, true, 1, this);
        livesP1 = LIVES;
        livesP2 = LIVES;
        btn1 = new Button((this.getWidth()/2)-(336/2)-180,400,336,80,1,this);
        btn3 = new Button((this.getWidth()/2)-(336/2)+200,400,336,80,3,this);
        btn2 = new Button((this.getWidth()/2)-(456/2),this.getHeight()-120,456,80,2,this);
        display.getJframe().addKeyListener(keyManager);
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager); 
    }
    
    private void reset(){
        ball = new Ball(800, 45, 57, this); 
        building1 = new Building(12, 0, 120, 640, true, this);
        building2 = new Building(892, 0, 120, 640, false, this);
        player1 = new Elevator(60, 50, 94, 105, true, this, false);
        player2 = new Elevator(870, 50, 94, 105, false, this, true);
        bird1 = new Bird(randomRange(0, 5000, false), randomRange(50, getHeight() - 50, true), 50, 30, true, 1, this);
        bird2 = new Bird(randomRange(0, 5000, true), randomRange(50, getHeight() - 50, true), 50, 30, true, 1, this);
        perks.clear();
    }
    
    private int randomRange(int min, int max, boolean isPositive){ 
        int num = (int)(Math.random() * (max - min)) + min;
        if(!isPositive){
            num -= (-1);
        }
        return num;
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
            // if delta is bigger than 1 we tick the game
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
    
    public MouseManager getMouseManager(){
        return mouseManager;
    }
    
    /**
     * Updates the elements of the game
     */
    private void tick() {
        if(intro){
            // nothing here
        }
        else if(start){
            if(this.getMouseManager().isLeft())
            {
                if(btn1.contains(this.getMouseManager().getX(), this.getMouseManager().getY())){
                    start = false;
                    game = true;
                    solo = true;
                    player2.setAutomatic(true);
                }
                if(btn3.contains(this.getMouseManager().getX(), this.getMouseManager().getY())){
                    start = false;
                    game = true;
                    solo = false;
                    player2.setAutomatic(false);
                }
            }                
        }
        else if(game){
            getKeyManager().tick();
            if(getKeyManager().isPause()){
                getKeyManager().setPause(false);
                paused = !paused;
            }
            if(!paused){
                player1.tick();
                player2.tick();
                ball.tick();
                bird1.tick();
                bird2.tick();
                // check for ball vs building1 collision
                if(ball.intersects(building1)){
                    ball.turnAround();
                    Assets.collisionBallBuilding.play();
                    while(ball.intersects(player1)){
                        ball.tick();
                    }
                    building1.damage();
                }
                // check for ball vs building2 collision
                else if(ball.intersects(building2)){
                    ball.turnAround();
                    Assets.collisionBallBuilding.play();
                    while(ball.intersects(player2)){
                        ball.tick();
                    }
                    building2.damage();
                }
                else{
                    // check for ball vs player collision
                    if(ball.intersects(player1) || ball.intersects(player2)){
                        Assets.collisionBallElevator.play();
                        ball.turnAround();
                    }      
                }
                // check collision bird1 vs building2
                if(bird1.intersects(building2)){
                    bird1.respawn();
                }
                // check collision bird2 vs building1
                if(bird2.intersects(building1)){
                    bird2.respawn();
                }
                // check collision bird1 vs player2
                if(bird1.intersects(player2)){
                    // add the perk
                    int perkID = bird1.getPower();
                    if(perkID % 2 == 1){
                        Assets.goodBirdElevator.play();
                    }
                    else{
                        Assets.evilBirdElevator.play();
                    }
                    if(perkID <= 4){
                        perks.add(new Perk(perkID, 0, 250, player2));
                    }
                    else{
                        if(perkID == 5 && building2.getStrength() < 7){
                            building2.setStrength(building2.getStrength() + 1);
                        }
                        else if(building2.getStrength() > 0){
                            building2.damage();
                        }
                    }
                    bird1.respawn();
                }
                // check collision bird2 vs player1
                if(bird2.intersects(player1)){
                    // add the perk
                    int perkID = bird2.getPower();
                    if(perkID % 2 == 1){
                        Assets.goodBirdElevator.play();
                    }
                    else{
                        Assets.evilBirdElevator.play();
                    }
                    if(perkID <= 4){
                        perks.add(new Perk(perkID, 0, 250, player1));
                    }
                    else{
                        if(perkID == 5 && building1.getStrength() < 7){
                            building1.setStrength(building1.getStrength() + 1);
                        }
                        else if(building1.getStrength() > 0){
                            building1.damage();
                        }
                    }
                    bird2.respawn();
                }
                // Respawn birds if necessary
                if(bird1.getX() > getWidth()){
                    // respawn it
                    bird1.respawn();
                }
                if(bird2.getX() + bird2.getWidth() < 0){
                    // respawn it
                    bird2.respawn();
                }

                // tick the perks
                for(int i = 0; i < perks.size(); i++){
                    if(perks.get(i).timesTicked > perks.get(i).lifeTime){
                        perks.remove(i);
                        i--;
                    }
                    else{
                        perks.get(i).execute();
                    }
                }

                // check if a player has died
                if(building1.getStrength() == 0){
                    // remove a life and reset everything
                    livesP1--;
                    reset();
                }
                if(building2.getStrength() == 0){
                    // remove a life and reset everything
                    livesP2--;
                    reset();
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
            if(running){
                if(intro)
                {                                        
                    g.drawImage(Assets.background, 0, 0, width, height, null);
                }
                else if(start){
                    g.drawImage(Assets.startBackground, 0, 0, width, height, null);                    
                    btn1.render(g);
                    btn2.render(g);
                    btn3.render(g);                  
                }
                else if(game){
                    g.drawImage(Assets.background, 0, 0, getWidth(), getHeight(), null); // 2 0 1
                    if(ball.getY() < getHeight() / 3){
                        g.drawImage(Assets.craneSprites[2], 400, 50, 512, 640, null);
                    }
                    else if(ball.getY() < getHeight() * 2 / 3){
                        g.drawImage(Assets.craneSprites[0], 400, 50, 512, 640, null);
                    }
                    else{
                        g.drawImage(Assets.craneSprites[1], 400, 50, 512, 640, null);
                    }
                    g.drawImage(Assets.health1Sprites[5 - livesP1], 225, 10, 256, 40, null);
                    g.drawImage(Assets.health2Sprites[5 - livesP2], getWidth() - 225 - 256, 10, 256, 40, null);
                    ball.render(g);
                    building1.render(g);
                    building2.render(g);
                    player1.render(g);
                    player2.render(g);
                    bird1.render(g);
                    bird2.render(g);
                }
                bs.show();
                g.dispose();
            }
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