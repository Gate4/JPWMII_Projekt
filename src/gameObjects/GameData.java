package gameObjects;

import gameObjects.gameEnemies.EnemyGhul;
import gameObjects.gameEnemies.EnemySkeleton;
import gameObjects.gameEnemies.EnemyZombie;
import gameSingletons.GameLogic;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maciej Żak
 */
public class GameData implements java.io.Serializable{
    private boolean worldGenerated=false;
    private List<GameRoom> world;
    
    private GameRoom currentRoom;
    private GamePlayer player;
    
    public static final int FLOORS=5;
    public static final int ROOMS_PER_FLOOR=10;//8
    public static final int MIN_X_ROOM=0;
    public static final int MIN_Y_ROOM=0;
    public static final int MAX_X_ROOM=9;
    public static final int MAX_Y_ROOM=9;
    
    public static final int GENERATOR_START_X=4;
    public static final int GENERATOR_START_Y=4;
    
    public static final int WEAPONS_PER_FLOOR=3;
    public static final int ARMORS_PER_FLOOR=3;
    
    public static final int SKELETONS_PER_FLOOR=3;
    public static final int ZOMBIES_PER_FLOOR=2;
    public static final int GHULS_PER_FLOOR=1;
    
    private List<GameAttack> attacks;

    
    public GameData() {
        generateWorld();
        this.currentRoom=this.world.get(0);
        this.player=new GamePlayer("Gracz", 5, 9);
        this.attacks=new ArrayList<>();
    }
    
    
    
    
    public final void generateWorld(){
        this.world=new ArrayList<>(FLOORS*ROOMS_PER_FLOOR);
        if(!this.worldGenerated){
            this.generateWorldLayout();
            this.generateItems();
            this.generateEnemies();
            this.worldGenerated=true;
        }else{
            javax.swing.JOptionPane.showMessageDialog(null, "Świat został już wygenerowany",
                    "Błąd "+toString(), javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void generateWorldLayout(){
        int index=0;
        int type=GameRoom.TYPE_EMPTY;
        for(int floor=0;floor<FLOORS;floor++){
            if(floor>0){
                this.world.add(new GameRoom(GENERATOR_START_X, GENERATOR_START_Y, floor,GameRoom.TYPE_STAIRS));
                this.world.get(floor*ROOMS_PER_FLOOR).connectRoom(this.world.get(floor*ROOMS_PER_FLOOR-1));
            }else{
                this.world.add(new GameRoom(GENERATOR_START_X, GENERATOR_START_Y, floor,GameRoom.TYPE_EMPTY));
            }
            for(int room=1;room<ROOMS_PER_FLOOR;room++){
                do{
                    index=(floor*ROOMS_PER_FLOOR)+(int)(Math.round(Math.random()*(room-1)));
                    if(room==ROOMS_PER_FLOOR-1){
                        type=GameRoom.TYPE_STAIRS;
                    }else{
                        type=GameRoom.TYPE_EMPTY;
                    }
                }while(!addRoom(createRandomConnectedRoom(this.world.get(index),type)));
                this.world.get(index).connectRoom(this.world.get(floor*ROOMS_PER_FLOOR+room));
            }
        }
        
        
        for(int f=0;f<FLOORS;f++){
            System.out.println("Piętro "+f);
            for(int y=0;y<MAX_Y_ROOM;y++){
                for(int x=0;x<MAX_X_ROOM;x++){
                    if(findRoomXY(f, x, y)){
                        System.out.print("X");
                    }else{
                        System.out.print("_");
                    }
                }
                System.out.println();
            }
        }
        
        
        
    }
    
    private void generateItems(){
        for(int floor=0;floor<FLOORS;floor++){
            for(int i=0;i<WEAPONS_PER_FLOOR;i++){
                int index=(floor*ROOMS_PER_FLOOR)+1+(int)Math.floor(Math.random()*(ROOMS_PER_FLOOR-2));
                int x=1+(int)Math.floor(Math.random()*(GameRoom.DEFAULT_WIDTH-2));
                int y=1+(int)Math.floor(Math.random()*(GameRoom.DEFAULT_HEIGHT-2));
                world.get(index).getItems().add(new GameItem(x, y, GameItem.TYPE_WEAPON, floor));
            }
            for(int i=0;i<ARMORS_PER_FLOOR;i++){
                int index=(floor*ROOMS_PER_FLOOR)+1+(int)Math.floor(Math.random()*(ROOMS_PER_FLOOR-2));
                int x=1+(int)Math.floor(Math.random()*(GameRoom.DEFAULT_WIDTH-2));
                int y=1+(int)Math.floor(Math.random()*(GameRoom.DEFAULT_HEIGHT-2));
                world.get(index).getItems().add(new GameItem(x, y, GameItem.TYPE_ARMOR, floor));
            }
        }
    }
    
    private void generateEnemies(){
        for(int floor=0;floor<FLOORS;floor++){
            for(int i=0;i<SKELETONS_PER_FLOOR;i++){
                int index=(floor*ROOMS_PER_FLOOR)+1+(int)Math.floor(Math.random()*(ROOMS_PER_FLOOR-2));
                int x=2+(int)Math.floor(Math.random()*(GameRoom.DEFAULT_WIDTH-4));
                int y=2+(int)Math.floor(Math.random()*(GameRoom.DEFAULT_HEIGHT-4));
                world.get(index).getEnemies().add(new EnemySkeleton(x, y, floor));
            }
            for(int i=0;i<ZOMBIES_PER_FLOOR;i++){
                int index=(floor*ROOMS_PER_FLOOR)+1+(int)Math.floor(Math.random()*(ROOMS_PER_FLOOR-2));
                int x=2+(int)Math.floor(Math.random()*(GameRoom.DEFAULT_WIDTH-4));
                int y=2+(int)Math.floor(Math.random()*(GameRoom.DEFAULT_HEIGHT-4));
                world.get(index).getEnemies().add(new EnemyZombie(x, y, floor));
            }
            for(int i=0;i<GHULS_PER_FLOOR;i++){
                int index=(floor*ROOMS_PER_FLOOR)+1+(int)Math.floor(Math.random()*(ROOMS_PER_FLOOR-2));
                int x=2+(int)Math.floor(Math.random()*(GameRoom.DEFAULT_WIDTH-4));
                int y=2+(int)Math.floor(Math.random()*(GameRoom.DEFAULT_HEIGHT-4));
                world.get(index).getEnemies().add(new EnemyGhul(x, y, floor));
            }
        }
    }
    
    private boolean findRoomXY(int f,int x,int y){
        for(GameRoom g:this.world){
            if(g.getFloor()==f&&g.getX()==x&&g.getY()==y){
                return true;
            }
        }
        return false;
    }
    
    private GameRoom createRandomConnectedRoom(GameRoom gR,int type){
        int newX=0;
        int newY=0;
        do{
            newX=(int)Math.round(Math.random()*2)-1;
            newY=(int)Math.round(Math.random()*2)-1;
        }while((newX!=0)&&(newY!=0));
        newX+=gR.getX();
        newY+=gR.getY();
        GameRoom newGR=new GameRoom(newX, newY, gR.getFloor(), type);
        return newGR;
    }
    
    private boolean addRoom(GameRoom gR){
        if(gR.getX()<MIN_X_ROOM||gR.getY()<MIN_Y_ROOM){
            return false;
        }else{
            for(GameRoom g:this.world){
                if(gR.getX()==g.getX()&&gR.getY()==g.getY()&&gR.getFloor()==g.getFloor()){
                    return false;
                }
            }
            this.world.add(gR);
            return true;
        }
    }
 
    public boolean generateRoom(int floor){
        return true;
    }
    
    public GameRoom getCurrentRoom(){
        return this.currentRoom;
    }
    
    public void setCurrentRoom(GameRoom gR,int nX,int nY){
        System.out.println("NOWY POKOJ\nX="+gR.getX()+" Y="+gR.getY()+" PIETRO="+gR.getFloor());
        gR.setVisited(true);
        this.currentRoom=gR;
        GameLogic.getInstance().setEnemiesIterator(gR.getEnemies().iterator());
        this.player.moveTo(nX, nY);
    }

    public List<GameRoom> getWorld() {
        return world;
    }

    public synchronized List<GameAttack> getAttacks() {
        return attacks;
    }
    
    
    
    
    //LISTY albo indeks do losowego generrowania

    public GamePlayer getPlayer() {
        return player;
    }
}
