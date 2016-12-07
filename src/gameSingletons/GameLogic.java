package gameSingletons;

import gameGUI.GameConsole;
import gameObjects.GameCharacter;
import gameObjects.GameData;
import gameObjects.GameObject;
import gameObjects.GamePlayer;
import gameObjects.GameTile;
import gameObjects.GameTilePassage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Maciej Żak
 */
public class GameLogic {

    private static GameLogic instance;
    private gameGUI.GameConsole gameConsole;
    private GameData gameData;
    
    //Dirtection array for moving objects
    public static final int[] xDirection={0,1,0,-1,1,1,-1,-1};
    public static final int[] yDirection={-1,0,1,0,-1,1,1,-1};
    //Indexes for direction array:
    public static final byte NORTH = 0;
    public static final byte EAST = 1;
    public static final byte SOUTH = 2;
    public static final byte WEST = 3;
    
    public static final byte NORTHEAST = 4;
    public static final byte SOUTHEAST = 5;
    public static final byte SOUTHWEST = 6;
    public static final byte NORTHWEST = 7;
    
    private java.util.Iterator enemiesIterator;
    private boolean enemiesTurn;
    
    private boolean busy;

    private GameLogic() {
        this.busy=false;
    }

    
    
    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    public void setGameConsole(GameConsole gameConsole) {
        this.gameConsole = gameConsole;
    }

    
    
    public boolean writeInConsole(String message){
        if(this.gameConsole!=null){
                gameConsole.write(message);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean writelnInConsole(String message){
        if(this.gameConsole!=null){
                gameConsole.writeln(message);
            return true;
        }else{
            return false;
        }
    }
    
    public GameTilePassage getPassageAtXY(int x,int y){
        GameTilePassage gTP=null;
        if(gameData!=null){
            for(GameTilePassage g:gameData.getCurrentRoom().getPassages())if(g.getX()==x&&g.getY()==y)gTP=g;
        }
        return gTP;
    }
    
    public boolean isCollisionAtXY(int x,int y){
        boolean collision=false;
        if(gameData!=null){
            for(GameTile gT:gameData.getCurrentRoom().getRoomLayout())if(gT.isCollidingAtXY(x,y))collision=true;
            if(!collision)for(GameCharacter gC:gameData.getCurrentRoom().getEnemies())if(gC.isCollidingAtXY(x,y))collision=true;
        }
        return collision;
    }
    
    public void moveObject(GameObject o,int direction){
        if(gameData!=null&&!busy){
            int nX=o.getX()+xDirection[direction];
            int nY=o.getY()+yDirection[direction];
            GameTilePassage gTP=getPassageAtXY(nX, nY);
            if(gTP!=null&&o instanceof GamePlayer){
                gameData.setCurrentRoom(gTP.getDestination(), gTP.getDestiantionX(), gTP.getDestiantionY());
                gameSingletons.GameSounds.getInstance().playSound("door.wav");
                o.updateGraphicPosition();
            }else{
                if(!isCollisionAtXY(nX, nY))o.move(this.xDirection[direction], this.yDirection[direction]);
            }
        }
    }
    
    public void rest(){
        if(gameData!=null){
            if(gameData.getPlayer().isAlive())gameData.getPlayer().rest();
            for(GameCharacter gC:gameData.getCurrentRoom().getEnemies())if(gC.isAlive())gC.rest();
        }
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
    
    
    
}
