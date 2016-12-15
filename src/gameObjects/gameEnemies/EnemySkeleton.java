package gameObjects.gameEnemies;

/**
 *
 * @author Maciej Żak
 */
public class EnemySkeleton extends gameObjects.GameEnemy{

    public EnemySkeleton(int x, int y, int level) {
        super(15*(level+1),0, "Szkielet", 60*(level+1), 60*(level+1), 25*(level+1), 25*(level+1), 0, 1*(level+1), (level+1), x, y, 1, 0, x*32, y*32, 0);
    }

}