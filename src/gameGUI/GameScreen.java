package gameGUI;

import gameObjects.GameData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Maciej Żak
 */
public class GameScreen extends javax.swing.JComponent implements ActionListener{
    private gameObjects.GameData gameData;
    private java.awt.image.BufferedImage screenImage;
    private final static int DEFAULT_WIDTH=352;
    private final static int DEFAULT_HEIGHT=352;
    private Timer timer;
    

    
    public GameScreen(GameData gameData) {  
        this.gameData = gameData;
    }

    public GameScreen() {
        super();
        this.screenImage=new java.awt.image.BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, java.awt.image.BufferedImage.TYPE_INT_RGB);
        this.timer=new Timer(10, this);
        this.timer.start();
    }
    
    @Override
    protected void paintComponent(java.awt.Graphics grphcs) {
        int width=getWidth();
        int height=getHeight();
        if(width<1||height<1){
            width=1;
            height=1;
        }
        if(this.gameData!=null){
            java.awt.Graphics2D g2D=(java.awt.Graphics2D)this.screenImage.getGraphics();
            g2D.drawString(this.gameData.toString(), (int)(Math.random()*width), (int)(Math.random()*height));
            grphcs.drawImage(this.screenImage.getScaledInstance(width, height, java.awt.image.BufferedImage.SCALE_SMOOTH), 0, 0, null);
            //System.out.println("COMPONENT: (w="+width+", h="+height+")");
            //System.out.println("IMAGE: (w="+bgImage.getWidth()+", h="+bgImage.getHeight()+")");
        }else{
        }
        super.paintComponent(grphcs);
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //nextFrames
        repaint();
    }
    
    
    
}
