package gameObjects;

import gameSingletons.GameLogic;

/**
 *
 * @author Maciej Żak
 */
public abstract class GameEnemy extends GameCharacter{
    private int expReward;
    private int aiIndex;

    public GameEnemy(int expReward, int aiIndex, String name, int health, int maxHealth, int attack,
            int defense, int magicAttack, int magicDefense, int level, int x, int y,
            int imageIndex, int frameIndex, int graphicX, int graphicY, double graphicRotation) {
        super(name, health, maxHealth, attack, defense, magicAttack, magicDefense, level, x, y, imageIndex, frameIndex, graphicX, graphicY, graphicRotation);
        this.expReward=expReward;
        this.aiIndex = aiIndex;
    }
    
    public void takeTurn(){
        gameAI.GameAI ai=null;
        if(this.aiIndex==0){
            ai=new gameAI.AIWarrior();
        }
        if(ai!=null){
            ai.takeTurn(this);
        }
    }
    
    @Override
    public void rest() {
        int health=getHealth();
        int maxHealth=getMaxHealth();
        if(health<maxHealth){
            setHealth(health+15);
            if(getHealth()>maxHealth)setHealth(maxHealth);
        }
    }

    public int getExpReward() {
        return expReward;
    }
    
    
    
}
