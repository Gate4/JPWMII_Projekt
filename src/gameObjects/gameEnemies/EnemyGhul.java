package gameObjects.gameEnemies;

/**
 *
 * @author Maciej Żak
 */
public class EnemyGhul extends gameObjects.GameEnemy{
    public EnemyGhul(int x, int y, int level) {
        super(50*(level+1),0, "Ghul", 125*(level+1), 125*(level+1), 40*(level+1), 40*(level+1), 0, 3*(level+1), (level+1), x, y, 3, 0, x*32, y*32, 0);
    }

}