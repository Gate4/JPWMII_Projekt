package gameSingletons;

import javax.imageio.ImageIO;


/**
 *
 * @author Maciej Żak
 */
public class GameGraphics {
    private final java.util.List<gameGUI.GameImage> listGameImages;
    private final int indexTileFloor[]={8,10,12,14,16};
    private final int indexTileWall[]={9,11,13,15,17};
    private final int indexTileOther[]={18,19,20};
    private final int indexItemArmor[]={21,22,23};
    private final int indexItemWeapon[]={24,25,26};
    
    private static GameGraphics instance = null;
    
    public final static int DEFAULT_TILE_SIZE=32;

    private GameGraphics() {
        this.listGameImages=new java.util.ArrayList<>();
        try{
            
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/hero.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/enemy0.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/enemy1.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/enemy2.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/enemy3.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/enemy4.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/enemy5.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/enemy6.png"))));
            
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
        
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/armor.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/armor1.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/armor2.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/sword.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/sword1.png"))));
            this.listGameImages.add(new gameGUI.GameImage(64, 64, 1, ImageIO.read(getClass().getResource("/gameSingletons/sword2.png"))));
            
            this.listGameImages.add(new gameGUI.GameImage(192, 192, 5, ImageIO.read(getClass().getResource("/gameSingletons/anim0.png"))));
            this.listGameImages.add(new gameGUI.GameImage(192, 192, 2, ImageIO.read(getClass().getResource("/gameSingletons/anim1.png"))));
            this.listGameImages.add(new gameGUI.GameImage(96, 96, 4, ImageIO.read(getClass().getResource("/gameSingletons/anim2.png"))));
            this.listGameImages.add(new gameGUI.GameImage(96, 96, 4, ImageIO.read(getClass().getResource("/gameSingletons/anim3.png"))));
            
            
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
    
    public int getRandomArmor(){
        return indexItemArmor[(int)Math.floor(Math.random()*indexItemArmor.length)];
    }
    
    public int getRandomWeapon(){
        return indexItemWeapon[(int)Math.floor(Math.random()*indexItemWeapon.length)];
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
