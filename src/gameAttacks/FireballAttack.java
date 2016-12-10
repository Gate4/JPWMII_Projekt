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
public class FireballAttack extends GameAttack{
    public static final String NAME="Kula ognia";
    private int xDirection;
    private int yDirection;
    private int attack;

    public FireballAttack(int x, int y, int attack,int xDirection,int yDirection) {
        super(x, y, 30, 0, attack,150);
        this.xDirection=xDirection;
        this.yDirection=yDirection;
        this.attack=attack;
        GameSounds.getInstance().playSound("fire1.wav");
        GameLogic.getInstance().setBusy(true);
    }

    @Override
    public synchronized void nextFrame() {
        int width=gameSingletons.GameGraphics.DEFAULT_TILE_SIZE;
        int height=gameSingletons.GameGraphics.DEFAULT_TILE_SIZE;
        int predictedX=getX()*width;
        int predictedY=getY()*height;
        if(getGraphicX()!=predictedX||getGraphicY()!=predictedY){
            if(isAnimating()){
                int xDirection=getX()*width-getGraphicX();
                if(xDirection!=0)xDirection/=Math.abs(xDirection);
                int yDirection=getY()*height-getGraphicY();
                if(yDirection!=0)yDirection/=Math.abs(yDirection);
                this.setGraphicX(getGraphicX()+xDirection*8);
                this.setGraphicY(getGraphicY()+yDirection*8);
            }else{
                setAnimating(true);
            }
        }else{
            if(isAnimating()){
                setAnimating(false);
            }
        }
        int length=GameGraphics.getInstance().getGameImage(getImageIndex()).getLength();
        super.setFrameIndex(super.getFrameIndex()+1);
        if(super.getFrameIndex()>=length){
            setFrameIndex(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(GameLogic.getInstance().isCollisionAtXY(getX(), getY())){
            GameSounds.getInstance().playSound("fire2.wav");
            GameLogic.getInstance().dealMagicDamageAtXY(attack, 50, getX(), getY());
            setMarkedToRemove(true);
            GameLogic.getInstance().nextTurn();
            GameLogic.getInstance().setBusy(false);
        }else{
            startTimer();
            move(xDirection, yDirection);
        }
        
    }
    
}
