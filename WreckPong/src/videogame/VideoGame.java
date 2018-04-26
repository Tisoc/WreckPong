/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

/**
 * @author Jaime Eugenio Garza Garza (A01193887)
 * @author Alvaro Márquez Cervantes (A01193509)
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */
public class VideoGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game g = new Game("Juego", 1024, 640);
        g.start();
    }
    
}
