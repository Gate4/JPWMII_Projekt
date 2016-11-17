package gameObjects;

/**
 *
 * @author Maciej Żak
 */
public class GameData implements java.io.Serializable{
    private boolean worldGenerated=false;
    private GameRoom[] world;
    
    private int currentEnemy=0;
    private int currentPlayer=0;
    
    public static final int FLOORS=5;
    public static final int ROOMS_PER_FLOOR=8;
    public static final int MIN_X_ROOM=0;
    public static final int MIN_Y_ROOM=0;
    public static final int MAX_X_ROOM=9;
    public static final int MAX_Y_ROOM=9;
    
    public static final int GENERATOR_START_X=4;
    public static final int GENERATOR_START_Y=4;
    
    
    public void generateWorld(){
        this.world=new GameRoom[FLOORS*ROOMS_PER_FLOOR];
        if(!this.worldGenerated){
            this.generateWorldLayout();
            this.worldGenerated=true;
        }else{
            javax.swing.JOptionPane.showMessageDialog(null, "Świat został już wygenerowany",
                    "Błąd "+toString(), javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void generateWorldLayout(){
        for(int floor=0;floor<FLOORS;floor++){
                this.world[floor*ROOMS_PER_FLOOR]=new GameRoom(GENERATOR_START_X, GENERATOR_START_Y, floor,GameRoom.TYPE_EMPTY);
                for(int room=(floor*ROOMS_PER_FLOOR)+1;room<(floor+1)*ROOMS_PER_FLOOR;room++){
                    
                }
            }
    }
    
    public boolean generateRoom(int floor){
        return true;
    }
    
    //LISTY albo indeks do losowego generrowania
}
