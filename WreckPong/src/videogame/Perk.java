/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

/**
 *
 * @author Arturo Arenas Esparza (A00820982)
 */
public class Perk {
    
    int ID;
    int timesTicked;
    int lifeTime;
    Object obj;

    public Perk(int ID, int timesTicked, int lifeTime, Object obj) {
        this.ID = ID;
        this.timesTicked = timesTicked;
        this.lifeTime = lifeTime;
        this.obj = obj;
    }

    public int getID() {
        return ID;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public int getTimesTicked() {
        return timesTicked;
    }
    
    void execute(){
        switch(ID){
            case 1:
                // bigger
                if(timesTicked == 0){
                    Elevator e = (Elevator) obj;
                    e.setHeight(e.getHeight() * 2);
                    e.setWidth(e.getWidth() * 2);
                }
                else if(timesTicked == lifeTime){
                    Elevator e = (Elevator) obj;
                    e.setHeight(e.getHeight() / 2);
                    e.setWidth(e.getWidth() / 2);
                }
            case 3:
                // faster
                if(timesTicked == 0){
                    Elevator e = (Elevator) obj;
                    e.setyVel(e.getyVel() * 2);
                }
                else if(timesTicked == lifeTime){
                    Elevator e = (Elevator) obj;
                    e.setyVel(e.getyVel() / 2);
                }
            case 5:
                // health
            case 2:
                // smaller
                if(timesTicked == 0){
                    Elevator e = (Elevator) obj;
                    e.setHeight(e.getHeight() / 2);
                    e.setWidth(e.getWidth() / 2);
                }
                else if(timesTicked == lifeTime){
                    Elevator e = (Elevator) obj;
                    e.setHeight(e.getHeight() * 2);
                    e.setWidth(e.getWidth() * 2);
                }
            case 4:
                // slower
                if(timesTicked == 0){
                    Elevator e = (Elevator) obj;
                    if(e.getyVel() > 1){
                        e.setyVel(e.getyVel() / 2);
                    }
                    else{
                        timesTicked = lifeTime;
                    }
                }
                else if(timesTicked == lifeTime){
                    Elevator e = (Elevator) obj;
                    e.setyVel(e.getyVel() * 2);
                }
            case 6:
                // take a hit
        }
        timesTicked++;
    }
}
