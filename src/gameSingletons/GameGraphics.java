package gameSingletons;

import java.awt.image.BufferedImage;

/**
 *
 * @author Maciej Żak
 */
public class GameGraphics {

    private static GameGraphics instance = null;

    private GameGraphics() {

    }

    public static GameGraphics getInstance() {
        if (instance == null) {
            instance = new GameGraphics();
        }
        return instance;
    }

    public BufferedImage getTileGraphic(byte graphicIndex, byte frameIndex) {
        return null;
    }

    public BufferedImage getItemGraphic(byte graphicIndex, byte frameIndex) {
        return null;
    }

    public BufferedImage getCharacterGraphic(byte graphicIndex, byte frameIndex) {
        return null;
    }

    public BufferedImage getEffectGraphic(byte graphicIndex, byte frameIndex) {
        return null;
    }

    public BufferedImage getMiscGraphic(byte graphicIndex, byte frameIndex) {
        return null;
    }

}
