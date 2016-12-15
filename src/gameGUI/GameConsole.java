package gameGUI;

/**
 *
 * @author Maciej Żak
 */
public class GameConsole extends javax.swing.JTextArea{  
    private static final java.awt.Color colorFont=java.awt.Color.YELLOW;
    private static final java.awt.Color colorBackground=java.awt.Color.BLACK; 

    public GameConsole(){
        super("");
        this.setFont(new java.awt.Font(java.awt.Font.DIALOG, java.awt.Font.PLAIN, 14));
        this.setForeground(colorFont);
        this.setBackground(colorBackground);
        this.setOpaque(true);
        this.setFocusable(false);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }
    
    public void clear(){
        this.setText("");
    }
    
    public void write(Object object){
        this.insert(object.toString(),0);
        this.setCaretPosition(0);
    }
    
    public void writeln(Object object){
        write(object.toString()+"\n");
    }
    
}
