/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Arturo Arenas Esparza (A00820982)
 * @author Sergio Sanchez Martinez (A00809693)
 */

public class FileManager {
    private static String fileName;

    public static void setFileName(String fileName) {
        FileManager.fileName = fileName;
    }

    public static String getFileName() {
        return fileName;
    }
    
    public static boolean saveFile(Game game){
        // define objects
        PrintWriter printWriter;
        try{
            // creating a file
            printWriter = new PrintWriter(new FileWriter(getFileName()));
            // save game lives and score
//            printWriter.println("" + game.getLives() + " " + game.getScore());
//            // save players's position (x, y)
//            printWriter.println("" + game.getPlayer().getX() + " " + game.getPlayer().getY());
            printWriter.close();
            // saving the data
        } catch(IOException ioe){
            System.out.println("Your hard disk is full" + ioe.toString());
            return false;
        }
        return true;
    }
    public static boolean loadFile(Game game){
        String line;
        try{
            FileReader fileReader = new FileReader(getFileName());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int lives, score;
            String[] firstLine = bufferedReader.readLine().split(" ");
            lives = Integer.parseInt(firstLine[0]);
            score = Integer.parseInt(firstLine[1]);
            String[] playerPos = bufferedReader.readLine().split(" ");
            int playerPosX = Integer.parseInt(playerPos[0]);
            int playerPosY = Integer.parseInt(playerPos[1]);
                        
            
//            game.loadingGame(lives, score, playerPosX, playerPosY, bulletsSize, BulletsPosX, BulletsPosY, BulletsFalling,
//                    barriersSize, BarriersPosX, BarriersPosY, BarriersPower, enemiesSize, EnemiesPosX, EnemiesPosY, EnemiesType, EnemiesFront);
//            bufferedReader.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("The loading file has not been found");
            return false;
        }
        catch(IOException ioe){
            System.out.println("Error reading loading file");
            return false;
        }
        return true;
    }
}
