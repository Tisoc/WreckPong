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
public class Perk {
    
    int ID;
    int timesTicked;
    int lifeTime;
    Object obj;
    Game game;

    public Perk(int ID, int timesTicked, int lifeTime, Object obj, Game game) {
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public void setTimesTicked(int timesTicked) {
        this.timesTicked = timesTicked;
    }
    
    void execute(){
        switch(getID()){
            case 1:
                // bigger
                if(getTimesTicked() == 0){
                    Elevator e = (Elevator) obj;
                    e.setHeight(e.getHeight() + 30);
                    e.setWidth(e.getWidth() + 30);
                    if(e.isType()){
                        e.setX(e.getX() - 30);
                    }
                }
                else if(getTimesTicked() == getLifeTime()){
                    Elevator e = (Elevator) obj;
                    e.setHeight(e.getHeight() - 30);
                    e.setWidth(e.getWidth() - 30);
                    if(e.isType()){
                        e.setX(e.getX() + 30);
                    }
                }
                break;
            case 3:
                // faster
                if(getTimesTicked() == 0){
                    Elevator e = (Elevator) obj;
                    e.setyVel(e.getyVel() + 2);
                }
                else if(getTimesTicked() == getLifeTime()){
                    Elevator e = (Elevator) obj;
                    e.setyVel(e.getyVel() - 2);
                }
                break;
            case 2:
                // smaller
                if(getTimesTicked() == 0){
                    Elevator e = (Elevator) obj;
                    if(e.getHeight() > 30 && e.getWidth() > 30){
                        e.setHeight(e.getHeight() - 30);
                        e.setWidth(e.getWidth() - 30);
                        if(e.isType()){
                            e.setX(e.getX() + 30);
                        }
                    }
                    else{
                        setTimesTicked(getLifeTime());
                    }
                }
                else if(getTimesTicked() == getLifeTime()){
                    Elevator e = (Elevator) obj;
                    e.setHeight(e.getHeight() + 30);
                    e.setWidth(e.getWidth() + 30);
                    if(e.isType()){
                        e.setX(e.getX() - 30);
                    }
                }
                break;
            case 4:
                // slower
                if(getTimesTicked() == 0){
                    Elevator e = (Elevator) obj;
                    if(e.getyVel() > 1){
                        e.setyVel(e.getyVel() - 2);
                    }
                    else{
                        setTimesTicked(getLifeTime());
                    }
                }
                else if(getTimesTicked() == getLifeTime()){
                    Elevator e = (Elevator) obj;
                    e.setyVel(e.getyVel() + 2);
                }
                break;
        }
        setTimesTicked(getTimesTicked() + 1);
    }
}
