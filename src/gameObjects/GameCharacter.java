package gameObjects;

import gameGUI.GameImage;
import gameSingletons.GameGraphics;
import gameSingletons.GameLogic;

/**
 *
 * @author Maciej Żak
 */
public abstract class GameCharacter extends GameObject{
    private String name;
    
    private int health;
    private int maxHealth;
    private int attack;
    private int defense;
    private int magicAttack;
    private int magicDefense;
    private int level;

    public GameCharacter(String name, int health, int maxHealth, int attack, int defense, int magicAttack, int magicDefense, int level, int x, int y, int imageIndex, int frameIndex, int graphicX, int graphicY, double graphicRotation) {
        super(x, y, imageIndex, frameIndex, graphicX, graphicY, graphicRotation,false,true);
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.magicAttack = magicAttack;
        this.magicDefense = magicDefense;
        this.level = level;
    }

    public void nextFrame() {
        int width=gameSingletons.GameGraphics.DEFAULT_TILE_SIZE;
        int height=gameSingletons.GameGraphics.DEFAULT_TILE_SIZE;
        int predictedX=getX()*width;
        int predictedY=getY()*height;
        if(getGraphicX()!=predictedX||getGraphicY()!=predictedY){
            if(isAnimating()){
                int xDirection=getX()*width-getGraphicX();
                if(xDirection!=0)xDirection/=Math.abs(xDirection);
                int yDirection=getY()*height-getGraphicY();
                if(yDirection!=0)yDirection/=Math.abs(yDirection);
                this.setGraphicX(getGraphicX()+xDirection*8);
                this.setGraphicY(getGraphicY()+yDirection*8);
            }else{
                setAnimating(true);
                gameSingletons.GameSounds.getInstance().playSound("move.wav");
                GameLogic.getInstance().setBusy(true);
            }
            
        }else{
            if(isAnimating()){
                setAnimating(false);
                GameLogic.getInstance().setBusy(false);
            }else{
                
            }
        }
    }
    
    public void giveNormalDamage(int attack){
        int damage=attack-(int)(Math.round((0.01 *(getDefense()/2))*attack));
        if (damage <= 0) {
            damage = 1;
        }
        this.health-=damage;
        GameLogic.getInstance().writelnInConsole(name+" otrzymuje "+damage+" punktów obrazeń fizycznych!");
        if(!isAlive())GameLogic.getInstance().writelnInConsole(name+" ginie!");
        notifyObservers();
    }
    
    public void giveMagicDamage(int magicAttack,int spellPower){
        int damage = (int) (Math.round((spellPower + magicAttack)) - ((spellPower + magicAttack) * (getMagicDefense()/2) * 0.01));
        if (damage <= 0) {
            damage = 1;
        }
        this.health-=damage;
        GameLogic.getInstance().writelnInConsole(name+" otrzymuje "+damage+" punktów obrazeń magicznych!");
        if(!isAlive())GameLogic.getInstance().writelnInConsole(name+" ginie!");
        notifyObservers();
    }
    
    public boolean isAlive(){
        return this.health>0;
    }
    
    public void rest(){
        if(health<maxHealth){
            setHealth(health+15);
            if(getHealth()>maxHealth)setHealth(maxHealth);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyObservers();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        notifyObservers();
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        notifyObservers();
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
        notifyObservers();
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
        notifyObservers();
    }

    public int getMagicAttack() {
        return magicAttack;
    }

    public void setMagicAttack(int magicAttack) {
        this.magicAttack = magicAttack;
        notifyObservers();
    }

    public int getMagicDefense() {
        return magicDefense;
    }

    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;
        notifyObservers();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        notifyObservers();
    }
    
    
    
}
