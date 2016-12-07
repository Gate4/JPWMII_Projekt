package gameSingletons;

import javax.imageio.ImageIO;


/**
 *
 * @author Maciej Żak
 */
public class GameGraphics {
    private final java.util.List<gameGUI.GameImage> listGameImages;
    private final int indexTileFloor[]={2,4,6,8,10};
    private final int indexTileWall[]={3,5,7,9,11};
    private final int indexTileOther[]={12,13,14};
    private static GameGraphics instance = null;
    
    public final static int DEFAULT_TILE_SIZE=32;

    private GameGraphics() {
        this.listGameImages=new java.util.ArrayList<>();
        try{
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/hero.png"))));
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/enemy0.png"))));
        
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/floor0.png"))));
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/wall0.png"))));
        this.listGameImages.add(new gameGUI.GameImage(128, 128, 1, ImageIO.read(getClass().getResource("/gameSingletons/floor1.png"))));
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/wall1.png"))));
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/floor2.png"))));
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/wall2.png"))));
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/floor3.png"))));
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/wall3.png"))));
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/floor4.png"))));
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/wall4.png"))));
        
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/door.png"))));
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/stairsUp.png"))));
        this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/stairsDown.png"))));
        } catch (Exception ex) {

        }
    }

    public static GameGraphics getInstance() {
        if (instance == null) {
            instance = new GameGraphics();
        }
        return instance;
    }
    
    public gameGUI.GameImage getGameImage(int imageIndex){
        return this.listGameImages.get(imageIndex);
    }
    
    public int getTileIndexForFloor(int index, int type){
        if(type==gameObjects.GameTile.FLOOR){
            return indexTileFloor[index];
        }else if(type==gameObjects.GameTile.WALL){
            return indexTileWall[index];
        }else if(type==gameObjects.GameTile.OTHER){
            return indexTileOther[index];
        }
        return 0;
    }
}
