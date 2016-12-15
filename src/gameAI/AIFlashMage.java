package gameAI;

import gameSingletons.GameLogic;

/**
 *
 * @author Maciej Żak
 */
public class AIFlashMage implements GameAI{ 
    @Override
    public void takeTurn(gameObjects.GameCharacter character) {
        if(GameLogic.getInstance().canHitWithFlash(character)){
            GameLogic.getInstance().flashAttackInPlayerDirection(character.getMagicAttack(), character.getX(), character.getY());
        }else if(!GameLogic.getInstance().moveToRangedAttack(character)){
            if(GameLogic.getInstance().isInMeleeRange(character)){
                GameLogic.getInstance().fistAttackAtPlayerPos(character.getAttack());
            }
        }
        GameLogic.getInstance().nextTurn();

    }
    
}
