package gameAI;

import gameObjects.gameEnemies.EnemyGhul;
import gameObjects.gameEnemies.EnemySkeleton;
import gameObjects.gameEnemies.EnemyZombie;
import gameSingletons.GameLogic;

/**
 *
 * @author Maciej Żak
 */
public class AIWarrior implements GameAI{ 
    @Override
    public void takeTurn(gameObjects.GameCharacter character) {
        if(GameLogic.getInstance().isInMeleeRange(character)){
            if(character instanceof EnemySkeleton){
                GameLogic.getInstance().swordAttackAtPlayerPos(character.getAttack());
            }else{
                if((character instanceof EnemyZombie)||(character instanceof EnemyGhul))GameLogic.getInstance().fistAttackAtPlayerPos(character.getAttack());
            }
        }else{
            GameLogic.getInstance().moveTowardPlayer(character);
        }
        GameLogic.getInstance().nextTurn();
    }
    
}
