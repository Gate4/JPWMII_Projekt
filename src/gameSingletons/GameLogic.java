package gameSingletons;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Maciej Żak
 */
public class GameLogic {

    private static GameLogic instance;
    private java.util.List<gameGUI.GameConsole> listGameConsoles;
    
    //Dirtection array for moving objects
    public static final int[] xDirection={0,1,1,1,0,-1,-1,-1};
    public static final int[] yDirection={-1,-1,0,1,1,1,0,-1};
    //Indexes for direction array:
    public static final byte NORTH = 0;
    public static final byte NORTHEAST = 1;
    public static final byte EAST = 2;
    public static final byte SOUTHEAST = 3;
    public static final byte SOUTH = 4;
    public static final byte SOUTHWEST = 5;
    public static final byte WEST = 6;
    public static final byte NORTHWEST = 7;

    public GameLogic() {
        this.listGameConsoles=new ArrayList<>();
    }

    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }
    
    public void addGameConsole(gameGUI.GameConsole gameConsole){
        if(!this.containsGameConsole(gameConsole)){
            this.listGameConsoles.add(gameConsole);
        }else{
            JOptionPane.showMessageDialog(null, this.listGameConsoles.toString()
                    +"już zawiera "+gameConsole.toString(), "Błąd w "+this.toString(),
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void removeGameConsole(gameGUI.GameConsole gameConsole){
        if(this.containsGameConsole(gameConsole)){
            this.listGameConsoles.remove(gameConsole);
        }else{
            JOptionPane.showMessageDialog(null, this.listGameConsoles.toString()
                    +"nie zawiera "+gameConsole.toString(), "Błąd w "+this.toString(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean containsGameConsole(gameGUI.GameConsole gameConsole){
        return this.listGameConsoles.contains(gameConsole);
    }

}
