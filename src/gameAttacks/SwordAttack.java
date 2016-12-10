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
public class SwordAttack extends GameAttack{

    public SwordAttack(int x, int y, int attack) {
        super(x, y, 27, 0, attack,150);
        GameLogic.getInstance().dealDamageAtXY(attack, x, y);
        GameSounds.getInstance().playSound("attack0.wav");
        GameLogic.getInstance().setBusy(true);
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
        GameLogic.getInstance().setBusy(false);
    }
    
    
    
}
