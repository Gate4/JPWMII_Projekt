package gameObjects.gameEnemies;

/**
 *
 * @author Maciej Żak
 */
public class EnemySkeleton extends gameObjects.GameEnemy{

    public EnemySkeleton(int x, int y, int level) {
        super(level,0, "Skeleton", 60*level, 60*level, 20*level, 20*level, 0, 1*level, level, x, y, 128, 0, x, y, 0);
            
    }

    
    
}
