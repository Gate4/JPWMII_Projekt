package gameAI;

/**
 *
 * @author Maciej Żak
 */
public class AIWarrior implements GameAI{ 
    @Override
    public void takeTurn(gameObjects.GameCharacter character) {
        System.out.println(character.toString()+", a warrior, takes turn");
    }
    
}
