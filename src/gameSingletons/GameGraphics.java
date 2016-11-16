package gameSingletons;

/**
 *
 * @author Maciej Żak
 */
public class GameGraphics {
    private java.util.List<gameGUI.GameImage> listGameImages;

    private static GameGraphics instance = null;

    private GameGraphics() {
        this.listGameImages=new java.util.ArrayList<>();

    }

    public static GameGraphics getInstance() {
        if (instance == null) {
            instance = new GameGraphics();
        }
        return instance;
    }
    
    public gameGUI.GameImage getGameImage(byte imageIndex){
        return null;
    }

}
