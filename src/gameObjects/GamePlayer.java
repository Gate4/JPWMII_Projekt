package gameObjects;

import gameSingletons.GameLogic;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 *
 * @author Maciej Żak
 */
public class GamePlayer extends GameCharacter{
    //private boolean canMove;
    private int experience;
    private int skillPoints;
    public static final int EXP_FOR_LEVEL[]={100,300,700,1500,3100,6300,12700};
    public static final int MAX_LEVEL=6;
    
    private int strength;
    private int intelligence;
    
    public void addExperience(int experience){
        if(this.experience>=0){
            this.experience+=experience;
            int level=this.getLevel();
            if((this.experience>=EXP_FOR_LEVEL[level])&&(level<MAX_LEVEL)){
                setLevel(level+1);
                this.skillPoints+=15;
            }
        }
        notifyObservers();
    }

    /*public boolean CanMove() {
        return canMove;
    }*/
    
    public GamePlayer(String name, int x, int y) {
        super(name, 150, 150, 20, 0, 15,0, 0, x, y, 0, 0, x*gameSingletons.GameGraphics.DEFAULT_TILE_SIZE, y*gameSingletons.GameGraphics.DEFAULT_TILE_SIZE, 1);
        this.experience=0;
        this.setHealth(10);
        notifyObservers();
    }

    @Override
    public void rest() {
        int health=getHealth();
        int maxHealth=getMaxHealth();
        if(health<maxHealth){
            setHealth(health+15);
            if(getHealth()>maxHealth)setHealth(maxHealth);
            GameLogic.getInstance().writelnInConsole("Odpoczywasz i regenerujesz część punktów życia");
            notifyObservers();
        }else{
            GameLogic.getInstance().writelnInConsole("Odpoczywasz, lecz nie regenerujesz punktów życia");
        }
    }

    public int getExperience() {
        return experience;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }
    
    public void incrStrength(int val){
        strength+=val;
        notifyObservers();
    }
    
    public void incrIntelligence(int val){
        strength+=val;
        notifyObservers();
    }
    
    public void removeSkillPoints(int val){
        if(skillPoints>0)skillPoints--;
    }
    
}
