package gameGUI;

import gameObjects.GameAttack;
import gameObjects.GameCharacter;
import gameObjects.GameData;
import gameObjects.GameItem;
import gameObjects.GameRoom;
import gameSingletons.GameLogic;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author Maciej Żak
 */
public class GameScreen extends javax.swing.JComponent implements ActionListener{
    private gameObjects.GameData gameData;
    private GameRoom renderedRoom;
    private java.awt.image.BufferedImage backgroundImage;
    private java.awt.image.BufferedImage foregroundImage;
    private final static int DEFAULT_WIDTH=352;
    private final static int DEFAULT_HEIGHT=352;
    private Timer timer;
    
    private final String GO_N="n";
    private final String GO_S="s";
    private final String GO_E="e";
    private final String GO_W="w";
    private final String GO_NE="ne";
    private final String GO_NW="nw";
    private final String GO_SE="se";
    private final String GO_SW="sw";
    private final String HELP="h";
    private final String ATTACK_N="An";
    private final String ATTACK_S="As";
    private final String ATTACK_E="Ae";
    private final String ATTACK_W="Aw";
    private final String ATTACK_NE="Ane";
    private final String ATTACK_NW="Anw";
    private final String ATTACK_SE="Ase";
    private final String ATTACK_SW="Asw";
    private final String SPELL_N="Sn";
    private final String SPELL_S="Ss";
    private final String SPELL_E="Se";
    private final String SPELL_W="Sw";
    private final String SPELL_NE="Sne";
    private final String SPELL_NW="Snw";
    private final String SPELL_SE="Sse";
    private final String SPELL_SW="Ssw";
    private final String REST="res";
    private final String PICK="pick";
    

    
    public GameScreen(GameData gameData) {  
        this.gameData = gameData;
    }

    public GameScreen() {
        super();
        this.renderedRoom=null;
        this.backgroundImage=new java.awt.image.BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        this.foregroundImage=new java.awt.image.BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        this.timer=new Timer(15, this);
        this.timer.start();
        
        this.getActionMap().put(GO_N, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameSingletons.GameLogic.getInstance().moveObject(gameData.getPlayer(),gameSingletons.GameLogic.NORTH);
            }
        });
        
        this.getActionMap().put(GO_S, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameSingletons.GameLogic.getInstance().moveObject(gameData.getPlayer(),gameSingletons.GameLogic.SOUTH);
            }
        });
        
        this.getActionMap().put(GO_E, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameSingletons.GameLogic.getInstance().moveObject(gameData.getPlayer(),gameSingletons.GameLogic.EAST);
            }
        });
        
        this.getActionMap().put(GO_W, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameSingletons.GameLogic.getInstance().moveObject(gameData.getPlayer(),gameSingletons.GameLogic.WEST);
            }
        });
        
        this.getActionMap().put(GO_NE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameSingletons.GameLogic.getInstance().moveObject(gameData.getPlayer(),gameSingletons.GameLogic.NORTHEAST);
            }
        });
        
        this.getActionMap().put(GO_NW, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameSingletons.GameLogic.getInstance().moveObject(gameData.getPlayer(),gameSingletons.GameLogic.NORTHWEST);
            }
        });
        
        this.getActionMap().put(GO_SE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameSingletons.GameLogic.getInstance().moveObject(gameData.getPlayer(),gameSingletons.GameLogic.SOUTHEAST);
            }
        });
        
        this.getActionMap().put(GO_SW, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameSingletons.GameLogic.getInstance().moveObject(gameData.getPlayer(),gameSingletons.GameLogic.SOUTHWEST);
            }
        });
        
        this.getActionMap().put(HELP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message="Sterowanie:\nW, S, D, A, E, Q, C, Z - kolejno:"
                        + " ruch na N, S, E, W, NE, NW, SE, SW\nShift+kierunek - "
                        + "atak w podanym kierunku\nCtrl+kierunek - rzucenie"
                        + "wybranego zaklęcia w podanym kierunku\nSpacja - "
                        + "odpoczynek (regeneracja życia) - niemożliwy w pobliżu "
                        + "przeciwników\nF - podniesienie przedmiotu\nH - wyświetlenie pomocy";
                gameSingletons.GameLogic.getInstance().writelnInConsole(message);
            }
        });
        
        this.getActionMap().put(ATTACK_N, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().swordAttackInDirection(GameLogic.NORTH);
            }
        });
        
        this.getActionMap().put(ATTACK_S, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().swordAttackInDirection(GameLogic.SOUTH);
            }
        });
        
        this.getActionMap().put(ATTACK_E, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().swordAttackInDirection(GameLogic.EAST);
            }
        });
        
        this.getActionMap().put(ATTACK_W, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().swordAttackInDirection(GameLogic.WEST);
            }
        });

        this.getActionMap().put(ATTACK_NE, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().swordAttackInDirection(GameLogic.NORTHEAST);
            }
        });
        
        this.getActionMap().put(ATTACK_NW, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().swordAttackInDirection(GameLogic.NORTHWEST);
            }
        });
        
        this.getActionMap().put(ATTACK_SE, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().swordAttackInDirection(GameLogic.SOUTHEAST);
            }
        });
        
        this.getActionMap().put(ATTACK_SW, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().swordAttackInDirection(GameLogic.SOUTHWEST);
            }
        });
        
        this.getActionMap().put(SPELL_N, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().spellInDirection(gameData.getPlayer().getSpell(), GameLogic.NORTH);
            }
        });
        
        this.getActionMap().put(SPELL_S, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().spellInDirection(gameData.getPlayer().getSpell(), GameLogic.SOUTH);
            }
        });
       
        this.getActionMap().put(SPELL_E, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().spellInDirection(gameData.getPlayer().getSpell(), GameLogic.EAST);
            }
        });
        
        this.getActionMap().put(SPELL_W, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().spellInDirection(gameData.getPlayer().getSpell(), GameLogic.WEST);
            }
        });
        
        this.getActionMap().put(SPELL_NE, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().spellInDirection(gameData.getPlayer().getSpell(), GameLogic.NORTHEAST);
            }
        });
        
        this.getActionMap().put(SPELL_NW, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().spellInDirection(gameData.getPlayer().getSpell(), GameLogic.NORTHWEST);
            }
        });
        
        this.getActionMap().put(SPELL_SE, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().spellInDirection(gameData.getPlayer().getSpell(), GameLogic.SOUTHEAST);
            }
        });
        
        this.getActionMap().put(SPELL_SW, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().spellInDirection(gameData.getPlayer().getSpell(), GameLogic.SOUTHWEST);
            }
        });
        
        
        this.getActionMap().put(REST, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().rest();
            }
        });
        
        this.getActionMap().put(PICK, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLogic.getInstance().pickItemAtXY(gameData.getPlayer().getX(), gameData.getPlayer().getY());
            }
        });
        
        

        InputMap input=this.getInputMap();
        input.put(KeyStroke.getKeyStroke("W"), GO_N);
        input.put(KeyStroke.getKeyStroke("S"), GO_S);
        input.put(KeyStroke.getKeyStroke("D"), GO_E);
        input.put(KeyStroke.getKeyStroke("A"), GO_W);
        input.put(KeyStroke.getKeyStroke("E"), GO_NE);
        input.put(KeyStroke.getKeyStroke("Q"), GO_NW);
        input.put(KeyStroke.getKeyStroke("C"), GO_SE);
        input.put(KeyStroke.getKeyStroke("Z"), GO_SW);
        input.put(KeyStroke.getKeyStroke("H"), HELP);
        input.put(KeyStroke.getKeyStroke("shift W"), ATTACK_N);
        input.put(KeyStroke.getKeyStroke("shift S"), ATTACK_S);
        input.put(KeyStroke.getKeyStroke("shift D"), ATTACK_E);
        input.put(KeyStroke.getKeyStroke("shift A"), ATTACK_W);
        input.put(KeyStroke.getKeyStroke("shift E"), ATTACK_NE);
        input.put(KeyStroke.getKeyStroke("shift Q"), ATTACK_NW);
        input.put(KeyStroke.getKeyStroke("shift C"), ATTACK_SE);
        input.put(KeyStroke.getKeyStroke("shift Z"), ATTACK_SW);
        input.put(KeyStroke.getKeyStroke("control W"), SPELL_N);
        input.put(KeyStroke.getKeyStroke("control S"), SPELL_S);
        input.put(KeyStroke.getKeyStroke("control D"), SPELL_E);
        input.put(KeyStroke.getKeyStroke("control A"), SPELL_W);
        input.put(KeyStroke.getKeyStroke("control E"), SPELL_NE);
        input.put(KeyStroke.getKeyStroke("control Q"), SPELL_NW);
        input.put(KeyStroke.getKeyStroke("control C"), SPELL_SE);
        input.put(KeyStroke.getKeyStroke("control Z"), SPELL_SW);
        input.put(KeyStroke.getKeyStroke(' '), REST);
        input.put(KeyStroke.getKeyStroke("F"), PICK);
        
    }
    
    @Override
    protected void paintComponent(java.awt.Graphics grphcs) {
        this.requestFocus();
        int width=getWidth();
        int height=getHeight();
        if(width<1||height<1){
            width=1;
            height=1;
        }
        if(this.gameData!=null){
            Graphics2D g=(Graphics2D)grphcs.create();
            if(this.renderedRoom!=gameData.getCurrentRoom()){
                System.out.println("RYSUJE NOWY POKOJ");
                this.backgroundImage.flush();
                java.awt.Graphics2D g2D=(java.awt.Graphics2D)this.backgroundImage.getGraphics().create();
                paintRoom(g2D, gameData.getCurrentRoom());
                this.renderedRoom=gameData.getCurrentRoom();
            }
            java.awt.Graphics2D g2D=(java.awt.Graphics2D)this.foregroundImage.getGraphics().create();
            g2D.setComposite(AlphaComposite.Clear);
            g2D.fillRect(0, 0, width, height); 
            g2D.setComposite(AlphaComposite.SrcOver);
            for(GameCharacter gC:gameData.getCurrentRoom().getEnemies())paintObject(g2D, gC);
            for(GameItem gI:gameData.getCurrentRoom().getItems())paintObject(g2D, gI);
            paintObject(g2D, gameData.getPlayer());
            for(GameAttack gA:gameData.getAttacks())if(!gA.isFinished())paintObject(g2D, gA);
            g.drawImage(this.backgroundImage.getScaledInstance(width, height, java.awt.image.BufferedImage.SCALE_FAST), 0, 0, null);
            g.drawImage(this.foregroundImage.getScaledInstance(width, height, java.awt.image.BufferedImage.SCALE_SMOOTH), 0, 0, null);
        }else{
            
        }
        //super.paintComponent(g);
    }
    
    private void paintRoom(Graphics2D g2D,GameRoom gR){
        gR.paint(g2D);
    }
    
    private void paintObject(Graphics2D g2D,gameObjects.GameObject gO){
        gO.paint(g2D);
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
        if(gameData!=null){
            gameData.getPlayer().nextFrame();
            for(GameCharacter gC:gameData.getCurrentRoom().getEnemies())gC.nextFrame();
            for(GameAttack gA:gameData.getAttacks())if(!gA.isFinished())gA.nextFrame();
            repaint();
        }
    }
    
}
