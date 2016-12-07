package gameObjects;

/**
 *
 * @author Maciej Żak
 */
public class GameTilePassage extends GameTile{
    private GameRoom destination;
    private int destiantionX;
    private int destiantionY;
    public static int TYPE_DOOR=0;
    public static int TYPE_STAIRS_UP=1;
    public static int TYPE_STAIRS_DOWN=2;
    
    public GameTilePassage(int x, int y,int passageType,GameRoom destination,int destiantionX,int destinationY) {
        super(x, y, GameTile.OTHER, passageType);
        this.destination=destination;
        this.destiantionX=destiantionX;
        this.destiantionY=destinationY;
    }

    public GameRoom getDestination() {
        return destination;
    }

    public int getDestiantionX() {
        return destiantionX;
    }

    public int getDestiantionY() {
        return destiantionY;
    }

    
    
}
