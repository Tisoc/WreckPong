/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */
public class KeyManager implements KeyListener {
    public boolean p1up;    // flag to move palyer1 top
    public boolean p1down;   // flag to move palyer1 down
    public boolean p2up;    // flag to move palyer2 top
    public boolean p2down;   // flag to move palyer1 down
    public boolean space;   // flag to space
    private boolean pause;  // flag to pause the game
    private boolean pauseEnabled; // flag to know the pause
    private boolean restart; // flag to restart the game
    private boolean saveEnabled;
    private boolean save;
    private boolean load;
    private boolean keys[];  // to store all the flags for every key
    
    /**
     * Constructor of the key manager
     */
    public KeyManager() {
        keys = new boolean[256];
        pauseEnabled = true;
        saveEnabled = true;
    }

    /**
     * Setter for the pause status
     * @param pause to set the pause
     */
    public void setPause(boolean pause) {
        keys[KeyEvent.VK_P] = pause;
    }

    public boolean isSave() {
        return save;
    }

    public boolean isLoad() {
        return load;
    }

    public void setSave(boolean save) {
        keys[KeyEvent.VK_S] = save;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public void setSaveEnabled(boolean saveEnabled) {
        this.saveEnabled = saveEnabled;
    }

    public boolean isSaveEnabled() {
        return saveEnabled;
    }

    /**
     * Determines the pause status
     * @return the pause status
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * Determines the restart status
     * @return the restart status
     */
    public boolean isRestart() {
        return restart;
    }
    
    /**
     * Event of typing a key
     * @param e the event of the key typed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Event of pressing a key
     * @param e the event of a key pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_P){
            if(pauseEnabled){
                keys[KeyEvent.VK_P] = true;
                pauseEnabled = false;
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            if(saveEnabled){
                keys[KeyEvent.VK_S] = true;
                saveEnabled = false;
            }
        }
        else{
            // set true to every key pressed
            keys[e.getKeyCode()] = true;
        }
    }
    

    /**
     * Event of releasing a key
     * @param e the event of releasing a key
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        if(e.getKeyCode() == KeyEvent.VK_P){
            pauseEnabled = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            saveEnabled = true;
        }
        keys[e.getKeyCode()] = false;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        p1up = keys[KeyEvent.VK_UP];
        p2up = keys[KeyEvent.VK_DOWN];
        p1down = keys[KeyEvent.VK_A];
        p2down = keys[KeyEvent.VK_Z];
        
        space = keys[KeyEvent.VK_SPACE];
        pause = keys[KeyEvent.VK_P];
        save = keys[KeyEvent.VK_S];
        load = keys[KeyEvent.VK_L];
        restart = keys[KeyEvent.VK_R];
    }
}