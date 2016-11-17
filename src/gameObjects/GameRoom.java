package gameObjects;

/**
 *
 * @author Maciej Żak
 */
public class GameRoom implements java.io.Serializable{
    private java.util.List<GameTile> layout;
    private int x;
    private int y;
    private int floor;
    
    public static final int DEFAULT_WIDTH=11;
    public static final int DEFAULT_HEIGHT=11;
    public static final int DEFAULT_MIDDLE_X=5;
    public static final int DEFAULT_MIDDLE_Y=5;
    
    public static final int TYPE_EMPTY=0;

    public GameRoom(int x, int y, int floor, int type) {
        this.x = x;
        this.y = y;
        this.floor = floor;
        this.layout=this.createLayout(type);
    }

    public boolean equals(GameRoom gm){
        return (this.x==gm.x)&&(this.y==gm.y)&&(this.floor==gm.floor);
    }
    
    public java.util.List createLayout(int type){
        if(type==TYPE_EMPTY){
            
        }
        return null;
    }
    
    private void createEmptyLayout(){
        
    }
    
}
