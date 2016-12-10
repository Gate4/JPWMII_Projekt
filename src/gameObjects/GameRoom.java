package gameObjects;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Maciej Żak
 */
public class GameRoom implements java.io.Serializable{
    private java.util.List<GameTile> roomLayout;
    private java.util.List<GameTilePassage> passages;
    
    private java.util.List<GameEnemy> enemies;
    private java.util.List<GameItem> items;
    private int x;
    private int y;
    private int floor;
    private boolean visited;
    
    public static final int DEFAULT_WIDTH=11;
    public static final int DEFAULT_HEIGHT=11;
    public static final int DEFAULT_MIDDLE_X=5;
    public static final int DEFAULT_MIDDLE_Y=5;
    
    public static final int TYPE_EMPTY=0;
    public static final int TYPE_STAIRS=1;

    public GameRoom(int x, int y, int floor, int type) {
        this.x = x;
        this.y = y;
        this.floor = floor;
        this.enemies=new java.util.ArrayList<>();
        this.passages=new java.util.ArrayList<>();
        this.items=new java.util.ArrayList<>();
        this.roomLayout=this.createLayout(type,floor);
    }

    public boolean equals(GameRoom gm){
        return (this.x==gm.x)&&(this.y==gm.y)&&(this.floor==gm.floor);
    }
    
    public final java.util.List createLayout(int type,int floor){
        if(type==TYPE_EMPTY){
            return createEmptyLayout(floor);
        }else if(type==TYPE_STAIRS){
            return createStairsRoomLayout(floor);
        }
        return null;
    }
    
    private java.util.List createStairsRoomLayout(int floor){
        java.util.List<GameTile> layout=createEmptyLayout(floor);
        layout.add(new GameTile(DEFAULT_MIDDLE_X-1, DEFAULT_MIDDLE_Y, GameTile.WALL, floor));
        layout.add(new GameTile(DEFAULT_MIDDLE_X-1, DEFAULT_MIDDLE_Y-1, GameTile.WALL, floor));
        layout.add(new GameTile(DEFAULT_MIDDLE_X, DEFAULT_MIDDLE_Y-1, GameTile.WALL, floor));
        layout.add(new GameTile(DEFAULT_MIDDLE_X+1, DEFAULT_MIDDLE_Y-1, GameTile.WALL, floor));
        layout.add(new GameTile(DEFAULT_MIDDLE_X+1, DEFAULT_MIDDLE_Y, GameTile.WALL, floor));
        return layout;
    }
    
    private java.util.List createEmptyLayout(int floor){
        java.util.List<GameTile> layout=new ArrayList<>();
        for(int y=0;y<DEFAULT_HEIGHT;y++){
            for(int x=0;x<DEFAULT_WIDTH;x++){
                GameTile gT=null;
                if(y==0||y==DEFAULT_HEIGHT-1||x==0||x==DEFAULT_WIDTH-1){
                    gT=new GameTile(x, y, GameTile.WALL, floor);
                }else{
                    gT=new GameTile(x, y, GameTile.FLOOR, floor);
                }
                layout.add(gT);
            }
        }
        return layout;
    }
    
    public void connectRoom(GameRoom gR){
        GameTilePassage passA=null;
        GameTilePassage passB=null;
        if(this.floor==gR.floor){
            if(this.x<gR.x){
                passA=new GameTilePassage(DEFAULT_WIDTH-1, DEFAULT_MIDDLE_Y, GameTilePassage.TYPE_DOOR, gR,1,DEFAULT_MIDDLE_Y);
                passB=new GameTilePassage(0, DEFAULT_MIDDLE_Y, GameTilePassage.TYPE_DOOR, this,DEFAULT_WIDTH-2,DEFAULT_MIDDLE_Y);
            }else if(this.x>gR.x){
                passA=new GameTilePassage(0, DEFAULT_MIDDLE_Y, GameTilePassage.TYPE_DOOR, gR,DEFAULT_WIDTH-2,DEFAULT_MIDDLE_Y);
                passB=new GameTilePassage(DEFAULT_WIDTH-1, DEFAULT_MIDDLE_Y, GameTilePassage.TYPE_DOOR, this,1,DEFAULT_MIDDLE_Y);
            }else if(this.y<gR.y){
                passA=new GameTilePassage(DEFAULT_MIDDLE_X, DEFAULT_HEIGHT-1, GameTilePassage.TYPE_DOOR, gR,DEFAULT_MIDDLE_X,1);
                passB=new GameTilePassage(DEFAULT_MIDDLE_X, 0, GameTilePassage.TYPE_DOOR, this,DEFAULT_MIDDLE_X,DEFAULT_HEIGHT-2);
            }else{
                passA=new GameTilePassage(DEFAULT_MIDDLE_X, 0, GameTilePassage.TYPE_DOOR, gR,DEFAULT_MIDDLE_X,DEFAULT_HEIGHT-2);
                passB=new GameTilePassage(DEFAULT_MIDDLE_X, DEFAULT_HEIGHT-1, GameTilePassage.TYPE_DOOR, this,DEFAULT_MIDDLE_X,1);
            }
        }else if(this.floor<gR.floor){
            passA=new GameTilePassage(DEFAULT_MIDDLE_X, DEFAULT_MIDDLE_Y, GameTilePassage.TYPE_STAIRS_UP, gR,DEFAULT_MIDDLE_X,DEFAULT_MIDDLE_Y+1);
            passB=new GameTilePassage(DEFAULT_MIDDLE_X, DEFAULT_MIDDLE_Y, GameTilePassage.TYPE_STAIRS_DOWN, this,DEFAULT_MIDDLE_X,DEFAULT_MIDDLE_Y+1);
        }else{
            passA=new GameTilePassage(DEFAULT_MIDDLE_X, DEFAULT_MIDDLE_Y, GameTilePassage.TYPE_STAIRS_DOWN, gR,DEFAULT_MIDDLE_X,DEFAULT_MIDDLE_Y+1);
            passB=new GameTilePassage(DEFAULT_MIDDLE_X, DEFAULT_MIDDLE_Y, GameTilePassage.TYPE_STAIRS_UP, this,DEFAULT_MIDDLE_X,DEFAULT_MIDDLE_Y+1);
        }
        this.passages.add(passA);
        gR.passages.add(passB);
    }
    
    public void paint(Graphics2D g2D){
        for(GameObject ob:this.roomLayout){
            ob.paint(g2D);
        }
        for(GameObject ob:this.passages){
            ob.paint(g2D);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<GameTile> getRoomLayout() {
        return roomLayout;
    }

    public List<GameTilePassage> getPassages() {
        return passages;
    }

    public List<GameEnemy> getEnemies() {
        return enemies;
    }

    public List<GameItem> getItems() {
        return items;
    }
    
    
    
}
