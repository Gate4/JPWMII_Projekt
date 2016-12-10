package gameAttacks;

import gameObjects.GameAttack;
import gameSingletons.GameGraphics;
import gameSingletons.GameLogic;
import gameSingletons.GameSounds;
import java.awt.event.ActionEvent;

/**
 *
 * @author Maciej Żak
 */
public class HeavyFistAttack extends GameAttack{

    public HeavyFistAttack(int x, int y, int attack) {
        super(x, y, 29, 0, attack,150);
        GameLogic.getInstance().dealDamageAtXY(attack, x, y);
        GameSounds.getInstance().playSound("attack2.wav");
        
    }

    @Override
    public void nextFrame() {
        int length=GameGraphics.getInstance().getGameImage(getImageIndex()).getLength();
        super.setFrameIndex(super.getFrameIndex()+1);
        if(super.getFrameIndex()>=length){
            setFrameIndex(0);
            setFinished(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        setMarkedToRemove(true);
        GameLogic.getInstance().nextTurn();
    }
    
    
    
}
