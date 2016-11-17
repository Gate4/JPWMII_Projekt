package gameSingletons;

/**
 *
 * @author Maciej Żak
 */
public class GameGraphics {
    private java.util.List<gameGUI.GameImage> listGameImages;
    private static GameGraphics instance = null;
    
    public final static int DEFAULT_TILE_SIZE=32;

    private GameGraphics() {
        this.listGameImages=new java.util.ArrayList<>();
        gameGUI.GameImage hero=new gameGUI.GameImage(64, 64, 1, "hero.png");
        this.listGameImages.add(hero);
    }

    public static GameGraphics getInstance() {
        if (instance == null) {
            instance = new GameGraphics();
        }
        return instance;
    }
    
    public gameGUI.GameImage getGameImage(int imageIndex){
        return null;
    }

}
