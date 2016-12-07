package gameObjects;

/**
 *
 * @author Maciej Żak
 */
public class GameTile extends GameObject{
    public static int FLOOR=0;
    public static int WALL=1;
    public static int OTHER=2;

    public GameTile(int x, int y, int type, int floor) {
        super(x, y, gameSingletons.GameGraphics.getInstance().getTileIndexForFloor(floor, type), 0, x*gameSingletons.GameGraphics.DEFAULT_TILE_SIZE, y*gameSingletons.GameGraphics.DEFAULT_TILE_SIZE, 0,(type==FLOOR),(type!=FLOOR));
    }
    
    
}
