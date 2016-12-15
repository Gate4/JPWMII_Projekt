package gameAI;

import gameSingletons.GameLogic;

/**
 *
 * @author Maciej Żak
 */
public class AIFireballMage implements GameAI{ 
    @Override
    public void takeTurn(gameObjects.GameCharacter character) {
        if(GameLogic.getInstance().canHitWithFireball(character)){
            GameLogic.getInstance().fireballAttackInPlayerDirection(character.getMagicAttack(), character.getX(), character.getY());
        }else if(!GameLogic.getInstance().moveToRangedAttack(character)){
            if(GameLogic.getInstance().isInMeleeRange(character)){
                GameLogic.getInstance().fistAttackAtPlayerPos(character.getAttack());
            }
        }
        GameLogic.getInstance().nextTurn();

    }
    
}
