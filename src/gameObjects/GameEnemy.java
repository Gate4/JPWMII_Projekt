package gameObjects;

/**
 *
 * @author Maciej Żak
 */
public abstract class GameEnemy extends GameCharacter{
    private String name;
    private byte aiIndex;
    
    public void takeTurn(){
        gameAI.GameAI ai=null;
        if(this.aiIndex==0){
            ai=new gameAI.AIWarrior();
        }
        ai.takeTurn(this);
    }
    
}
