package gameObjects;

import gameSingletons.GameGraphics;
import gameSingletons.GameLogic;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Maciej Żak
 */
public abstract class GameAttack extends GameObject implements ActionListener{
    public static final byte TYPE_NORMAL=0;
    public static final byte TYPE_MAGIC=1;
    private int attack;
    private boolean finished;
    private boolean markedToRemove;
    private String name;
    private Timer timer;

    public GameAttack(int x, int y, int imageIndex, int frameIndex,int attack,int time) {
        super(x, y, imageIndex, 0, GameGraphics.DEFAULT_TILE_SIZE*x, GameGraphics.DEFAULT_TILE_SIZE*y, 0, true, true);
        this.name=name;
        this.finished=false;
        this.markedToRemove=false;
        this.attack=attack;
        this.timer=new Timer(time, this);
        this.timer.setRepeats(false);
        this.timer.start();
    }
    
    public void startTimer(){
        this.timer.start();
    }
            

    public abstract void nextFrame();

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isMarkedToRemove() {
        return markedToRemove;
    }

    public void setMarkedToRemove(boolean markedToRemove) {
        this.markedToRemove = markedToRemove;
    }
    
    

    public String getName() {
        return name;
    }
    
    
    
    
}
