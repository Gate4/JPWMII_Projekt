package gameSingletons;

/**
 *
 * @author Maciej Żak
 */
public class GameLogic {

    private static GameLogic instance;

    public static final byte NORTH = 0;
    public static final byte NORTHEAST = 1;
    public static final byte EAST = 2;
    public static final byte SOUTHEAST = 3;
    public static final byte SOUTH = 4;
    public static final byte SOUTHWEST = 5;
    public static final byte WEST = 6;
    public static final byte NORTHWEST = 7;

    public GameLogic() {
    }

    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

}
