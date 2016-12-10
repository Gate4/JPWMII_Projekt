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
public class FlashAttack extends GameAttack{
    public static final String NAME="Błyskawica";
    private int xDirection;
    private int yDirection;
    private int attack;

    public FlashAttack(int x, int y, int attack,int xDirection,int yDirection) {
        super(x, y, 28, 0, attack,150);
        this.xDirection=xDirection;
        this.yDirection=yDirection;
        this.attack=attack;
        GameLogic.getInstance().dealMagicDamageAtXY(attack, 30, x, y);
        GameSounds.getInstance().playSound("attack1.wav");
        GameLogic.getInstance().setBusy(true);
    }

    @Override
    public synchronized void nextFrame() {
        int length=GameGraphics.getInstance().getGameImage(getImageIndex()).getLength();
        super.setFrameIndex(super.getFrameIndex()+1);
        if(super.getFrameIndex()>=length){
            setFrameIndex(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        createNext(getX()+xDirection, getY()+yDirection);
        setMarkedToRemove(true);
        GameLogic.getInstance().nextTurn();
        
    }
    
    private void createNext(int x,int y){
        if(!GameLogic.getInstance().isOutOfBunds(x, y)){
            if(GameLogic.getInstance().isCollisionAtXY(x, y)){
            System.out.println("create");
            GameLogic.getInstance().flashAttack(attack, x, y, xDirection, yDirection);
        }else{
            System.out.println("try next");
            createNext(x+xDirection,y+yDirection);
            }
        }else{
            GameLogic.getInstance().setBusy(false);
        }
        
    }
    
}
