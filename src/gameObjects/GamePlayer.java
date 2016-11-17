package gameObjects;

/**
 *
 * @author Maciej Żak
 */
public class GamePlayer extends GameCharacter{
    private boolean canMove;
    private int experience;
    private static final int EXP_FOR_LEVEL[]={100,300,700,1500,3100,6300,12700};
    private static final int MAX_LEVEL=6;
    
    public void addExperience(int experience){
        if(this.experience>=0){
            this.experience+=experience;
            int level=this.getLevel();
            if((this.experience>=EXP_FOR_LEVEL[level])&&(level<MAX_LEVEL)){
                
            }
            
        }
    }

    public boolean CanMove() {
        return canMove;
    }
    
    public GamePlayer(String name, int x, int y) {
        super(name, 150, 150, 20, 0, 15,0, 0, x, y, 0, 0, x*gameSingletons.GameGraphics.DEFAULT_TILE_SIZE, y*gameSingletons.GameGraphics.DEFAULT_TILE_SIZE, 1);
        this.experience=0;
    }
    
    
    
}
