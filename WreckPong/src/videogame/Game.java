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
 * @author Jaime Eugenio Garza Garza (A01193887)
 * @author Alvaro Márquez Cervantes (A01193509)
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */
public class Game implements Runnable{
    private Ball ball;                  // The wrecking ball
    private Bird bird1;                 // First bird
    private Bird bird2;                 // Second bird
    private BufferStrategy bs;          // To have several buffers when displaying
    private Button btn1;                // The one player button
    private Button btn2;                // The option button
    private Button btn3;                // The multiplayer button
    private Button btn4;                // The multiplayer button
    private Button btn5;                // To start the game
    private Building building1;         // The player1's building of the game
    private Building building2;         // The player2's building of the game
    private boolean death;              // Death status
    private Display display;            // To display in the game
    private Graphics g;                 // To paint objects
    private boolean game;               // To know if we are playing
    private int height;                 // Height of the window
    private boolean instructions;       // To know if we are at the instruction
    private boolean intro;              // To validate if the game is in the intro
    private KeyManager keyManager;      // To manage the keyboard
    final private int LIVES;            // Initial amount of lives
    private int livesP1;                // Amount of lives left for player1
    private int livesP2;                // Amount of lives left for player2
    private MouseManager mouseManager;  // To manage the mouse
    private boolean paused;             // Pause status
    private ArrayList<Perk> perks;      // Array of perks
    private Elevator player1;           // The main player of the game
    private Elevator player2;           // The secondary player of the game
    private boolean pregame;            // TO teach how to play
    private boolean running;            // To set the game
    private int score;                  // Score of the player - COMPLETE SOLO MODE
    private boolean solo;               // Solo game status
    private int SpeedX;                 // The speed of the bird
    private boolean start;              // To validate if the game is in the intro
    private String title;               // Title of the window
    private Thread thread;              // Thread to create the game
    private int width;                  // Width of the window
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        death = false;
        game = false;
        instructions = false;
        intro = false;
        keyManager = new KeyManager();
        LIVES = 1;
        mouseManager = new MouseManager();
        paused = false;
        perks = new ArrayList<Perk>();
        pregame = false;
        running = false;
        start = true;
        this.height = height;
        this.title = title;
        this.width = width;
    }
    
    /**
     * To get the ball object of the game
     * @return an <code>ball</code> object
     */
    public Ball getBall() {
        return ball;
    }
    
    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for the key manager
     * @return an <code>key manager</code> object
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * To get the mouse manager object
     * @return an <code>mouse Manager</code> object
     */    
    public MouseManager getMouseManager(){
        return mouseManager;
    }

    /**
     * To get the value of the lives for P1
     * @return an <code>int</code> value of the lives of P1
     */    
    public int getLivesP1() {
        return livesP1;
    }

    /**
     * To get the value of the lives for P2
     * @return an <code>int</code> value of the lives of P2
     */    
    public int getLivesP2() {
        return livesP2;
    }

    /**
     * To get the player 1 object of the game
     * @return an <code>Elevator</code> object 
     */
    public Elevator getPlayer1() {
        return player1;
    }

    /**
     * To get the player 2 object of the game
     * @return an <code>Elevator</code> object 
     */
    public Elevator getPlayer2() {
        return player2;
    }

    /**
     * To get the score of the game
     * @return an <code>int</code> value with the score
     */
    public int getScore() {
        return score;
    }
    /**
     * To get the title of the game
     * @return an <code>String</code> value with the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the running status of the game
     * @return an <code>boolean</code> value of the status
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * To get the death satus of the game
     * @return an <code>boolean</code> value of the status
     */
    public boolean isDeath() {
        return death;
    }

    /**
     * To know if the game is starting
     * @return an <code>boolean</code> value
     */
    public boolean isGame() {
        return game;
    }

    /**
     * To know if the game is on the intro
     * @return an <code>boolean</code> value
     */
    public boolean isIntro() {
        return intro;
    }

    /**
     * To know if the game is on pause
     * @return an <code>boolean</code> value
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * To know if the game is for one player
     * @return an <code>boolean</code> value
     */
    public boolean isSolo() {
        return solo;
    }
    
    /**
     * To know if the game is on the start
     * @return an <code>boolean</code> value
     */
    public boolean isStart() {
        return start;
    }
    
    /**
     * Setter for the death status of the game
     * @param death the death status of the game
     */
    public void setDeath(boolean death) {
        this.death = death;
    }

    /**
     * Setter for the status of the game
     * @param game the status of the game
     */
    public void setGame(boolean game) {
        this.game = game;
    }

    /**
     * Setter for the status of the game
     * @param intro the status of the game
     */
    public void setIntro(boolean intro) {
        this.intro = intro;
    }

    /**
     * Setter for the lives of the player 1e
     * @param livesP1 the value of p1
     */    
    public void setLivesP1(int livesP1) {
        this.livesP1 = livesP1;
    }

    /**
     * Setter for the lives of the player 1e
     * @param livesP2 the value of p2
     */    
    public void setLivesP2(int livesP2) {
        this.livesP2 = livesP2;
    }

    /**
     * Setter for the status of the game
     * @param paused the status of the game
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * Setter for the running status of the game
     * @param running the running status of the game
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Setter for the score status of the game
     * @param score the final score of the game
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Setter for the status of the game
     * @param solo the status of the game
     */
    public void setSolo(boolean solo) {
        this.solo = solo;
    }

    /**
     * Setter for the status of the game
     * @param start the status of the game
     */
    public void setStart(boolean start) {
        this.start = start;
    }

    /**
     * Setter for the tile of the game
     * @param title the title of the game
     */
    public void setTitle(String title) {
        this.title = title;
    }    
    
    /**
     * initializing the first elements of the game
     */
    private void init() {
        //To initializate the assets
        Assets.init();
        //Create for the first time all the game objects
        display = new Display(title, getWidth(), getHeight());
        ball = new Ball(800, 45, 57, this); 
        bird1 = new Bird(randomRange(0, 5000, false), randomRange(50, getHeight() - 50, true), 50, 30, true, 1, this);
        bird2 = new Bird(randomRange(0, 5000, true), randomRange(50, getHeight() - 50, true), 50, 30, false, 1, this);
        btn1 = new Button((this.getWidth()/2)-(336/2)-180,400,336,80,1,this);
        btn3 = new Button((this.getWidth()/2)-(336/2)+200,400,336,80,3,this);
        btn2 = new Button((this.getWidth()/2)-(549/2),this.getHeight()-120,549,60,2,this);
        btn4 = new Button((this.getWidth())-(227),this.getHeight()-90,177,60,4,this);
        btn5 = new Button((this.getWidth())-(227),this.getHeight()-90,177,60,5,this);
        building1 = new Building(12, 0, 120, 640, true, this);
        building2 = new Building(892, 0, 120, 640, false, this);
        player1 = new Elevator(60, 50, 94, 105, true, this, false);
        player2 = new Elevator(870, 50, 94, 105, false, this, false);
        livesP1 = LIVES;
        livesP2 = LIVES;

        //To start the key and mouse manager
        display.getJframe().addKeyListener(keyManager);
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager); 
    }
    
    /**
     * Reset the game
     */
    private void resetGame(){
        ball = new Ball(800, 45, 57, this); 
        bird1 = new Bird(randomRange(0, 5000, false), randomRange(50, getHeight() - 50, true), 50, 30, true, 1, this);
        bird2 = new Bird(randomRange(0, 5000, true), randomRange(50, getHeight() - 50, true), 50, 30, false, 1, this);
        btn1 = new Button((this.getWidth()/2)-(336/2)-180,400,336,80,1,this);
        btn3 = new Button((this.getWidth()/2)-(336/2)+200,400,336,80,3,this);
        btn2 = new Button((this.getWidth()/2)-(549/2),this.getHeight()-120,549,60,2,this);
        btn4 = new Button((this.getWidth())-(227),this.getHeight()-90,177,60,4,this);
        building1 = new Building(12, 0, 120, 640, true, this);
        building2 = new Building(892, 0, 120, 640, false, this);
        player1 = new Elevator(60, 50, 94, 105, true, this, false);
        player2 = new Elevator(870, 50, 94, 105, false, this, false);
        livesP1 = LIVES;
        livesP2 = LIVES;
    }

    /**
    * Reset all the objects of the game
    */
    private void reset(){

        //Create all the game objects for the reset

        ball = new Ball(800, 45, 57, this); 
        bird1 = new Bird(randomRange(0, 5000, false), randomRange(50, getHeight() - 50, true), 50, 30, true, 1, this);
        bird2 = new Bird(randomRange(0, 5000, true), randomRange(50, getHeight() - 50, true), 50, 30, false, 1, this);
        building1 = new Building(12, 0, 120, 640, true, this);
        building2 = new Building(892, 0, 120, 640, false, this);
        player1 = new Elevator(60, 50, 94, 105, true, this, false);
        player2 = new Elevator(870, 50, 94, 105, false, this, isSolo());
        perks.clear();
    }

    /**
     * To asing a random range for the position of the birds 
     * @return an <code>int</code> whit the value of the range position 
     */
    private int randomRange(int min, int max, boolean isPositive){ 
        // define the random range
        int num = (int)(Math.random() * (max - min)) + min;
        //If the movement is negative, change to positive
        if(!isPositive){
            num -= (-1);
        }
        //return the range
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
        stop();
    }
    
    /**
     * Updates the elements of the game
     */
    private void tick() {
        if(isDeath()){
            System.out.println("Acknowledged death");
            // tick the key manager
            if(this.getMouseManager().isLeft())
            {
                if(btn4.contains(this.getMouseManager().getX(), this.getMouseManager().getY())){
                    resetGame();
                    setStart(true);
                    setGame(false);
                    setDeath(false);
                    setIntro(false);
                    instructions = false;
                    
                }
            }
        }
        else if(isIntro()){
            // nothing here
        }
        else if(instructions){
            if(this.getMouseManager().isLeft())
               {
                   if(btn4.contains(this.getMouseManager().getX(), this.getMouseManager().getY())){
                       instructions = false;
                       start = true;
                   }
               }
        }
        else if(isStart()){
            getKeyManager().tick();
            if(this.getMouseManager().isLeft())
            {
                if(btn1.contains(this.getMouseManager().getX(), this.getMouseManager().getY())){
                    setStart(false);
                    pregame = true;
                }
                if(btn3.contains(this.getMouseManager().getX(), this.getMouseManager().getY())){
                    setStart(false);
                    setGame(true);
                    setSolo(false);
                    player2.setAutomatic(false);
                }
                if(btn2.contains(this.getMouseManager().getX(), this.getMouseManager().getY())){
                    start = false;
                    instructions = true;
                }
            }                
        }
        else if(pregame){
            if(this.getMouseManager().isLeft())
            {
                if(btn4.contains(this.getMouseManager().getX(), this.getMouseManager().getY())){
                    pregame = false;
                    setGame(true);
                    setSolo(true);
                    player2.setAutomatic(true);
                }

            }                            
        }
        else if(isGame()){
            getKeyManager().tick();
            if(getKeyManager().isPause()){
                getKeyManager().setPause(false);
                setPaused(!isPaused());
            }
            if(!isPaused()){
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
                    System.out.println("" + perkID);
                    if(perkID % 2 == 1){
                        Assets.goodBirdElevator.play();
                    }
                    else{
                        Assets.evilBirdElevator.play();
                    }
                    if(perkID <= 4){
                        perks.add(new Perk(perkID, 0, 250, player2, this));
                    }
                    else{
                        if(perkID == 5 && building2.getStrength() < 7){
                            building2.setStrength(building2.getStrength() + 1);
                        }
                        else if(perkID == 6 && building2.getStrength() > 0){
                            building2.damage();
                        }
                    }
                    bird1.respawn();
                }
                // check collision bird2 vs player1
                if(bird2.intersects(player1)){
                    // add the perk
                    int perkID = bird2.getPower();
                    System.out.println("" + perkID);
                    if(perkID % 2 == 1){
                        Assets.goodBirdElevator.play();
                    }
                    else{
                        Assets.evilBirdElevator.play();
                    }
                    if(perkID <= 4){
                        perks.add(new Perk(perkID, 0, 250, player1, this));
                    }
                    else{
                        if(perkID == 5 && building1.getStrength() < 7){
                            building1.setStrength(building1.getStrength() + 1);
                        }
                        else if(perkID == 6 && building1.getStrength() > 0){
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
                    setLivesP1(getLivesP1() - 1);
                    reset();
                }
                if(building2.getStrength() == 0){
                    // remove a life and reset everything
                    setLivesP2(getLivesP2() - 1);
                    reset();
                }
                if(getLivesP1() == 0 || getLivesP2() == 0){
                    setDeath(true);
                    setGame(false);
                    instructions = false;
                    setStart(false);
                    setIntro(false);
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
            if(isRunning()){
                if(isIntro()){          
                    g.drawImage(Assets.background, 0, 0, getWidth(), getHeight(), null);
                }
                else if(instructions){
                    g.drawImage(Assets.howToPlayBackground, 0, 0, width, height, null);                    
                    btn4.render(g);                        
                }
                else if(isStart() && !instructions){
                    g.drawImage(Assets.startBackground, 0, 0, width, height, null);                    
                    btn1.render(g);
                    btn2.render(g);
                    btn3.render(g);                  
                }
                else if(pregame)
                {
                    g.drawImage(Assets.howToPlayBackground, 0, 0, width, height, null);                    
                    btn5.render(g);                                            
                }
                else if(isGame()){
                    g.drawImage(Assets.background, 0, 0, getWidth(), getHeight(), null); // 2 0 1
                    if(ball.getX() < getWidth() / 3){
                        g.drawImage(Assets.craneSprites[1], 300, -10, 512, 640, null);
                    }
                    else if(ball.getX() < getWidth() * 2 / 3){
                        g.drawImage(Assets.craneSprites[0], 300, -10, 512, 640, null);
                    }
                    else{
                        g.drawImage(Assets.craneSprites[2], 300, -10, 512, 640, null);
                    }
                    g.drawImage(Assets.health1Sprites[5 - getLivesP1()], 225, 10, 256, 40, null);
                    g.drawImage(Assets.health2Sprites[5 - getLivesP2()], getWidth() - 225 - 256, 10, 256, 40, null);
                    ball.render(g);
                    building1.render(g);
                    building2.render(g);
                    player1.render(g);
                    player2.render(g);
                    bird1.render(g);
                    bird2.render(g);
                }
                else if(isDeath()){
                    if(getLivesP1() == 0){
                        g.drawImage(Assets.p2Won, 0, 0, getWidth(), getHeight(), null);
                        btn4.render(g);                        
                    }
                    else{
                        g.drawImage(Assets.p1Won, 0, 0, getWidth(), getHeight(), null);
                        btn4.render(g);                        
                    }
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
        if (!isRunning()) {
            setRunning(true);
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (isRunning()) {
            setRunning(false);
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }    
}