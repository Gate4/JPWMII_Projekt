package gameObjects.gameEnemies;

/**
 *
 * @author Maciej Żak
 */
public class EnemyMage extends gameObjects.GameEnemy{

    public EnemyMage(int x, int y, int level) {
        super(50*(level+1),2, "Mag", 60*(level+1), 60*(level+1), 1*(level+1), 5*(level+1), 40*(level+1), 40*(level+1), (level+1), x, y, 5, 0, x*32, y*32, 0);
    }

}