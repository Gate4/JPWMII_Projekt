package gameObjects.gameEnemies;

/**
 *
 * @author Maciej Żak
 */
public class EnemyWraith extends gameObjects.GameEnemy{

    public EnemyWraith(int x, int y, int level) {
        super(25*(level+1),1, "Zjawa", 40*(level+1), 40*(level+1), 2*(level+1), 3*(level+1), 35*(level+1), 35*(level+1), (level+1), x, y, 4, 0, x*32, y*32, 0);
    }

}