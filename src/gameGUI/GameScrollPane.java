package gameGUI;

/**
 *
 * @author Maciej Żak
 */
public class GameScrollPane extends javax.swing.JScrollPane{

    public GameScrollPane() {
        super();
        this.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.YELLOW));
        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setBackground(java.awt.Color.BLACK);
        this.setCorner(javax.swing.JScrollPane.LOWER_RIGHT_CORNER, panel);
        
        this.getHorizontalScrollBar().setUI(new com.sun.java.swing.plaf.motif.MotifScrollBarUI(){
            @Override 
            protected void configureScrollBarColors(){
                this.thumbColor=java.awt.Color.YELLOW;
                this.trackColor=java.awt.Color.BLACK;
            }

            @Override
            protected javax.swing.JButton createIncreaseButton(int i) {
                javax.swing.JButton button=super.createIncreaseButton(i);
                button.setBackground(java.awt.Color.BLACK);
                return button;
            }

            @Override
            protected javax.swing.JButton createDecreaseButton(int i) {
                javax.swing.JButton button=super.createDecreaseButton(i);
                button.setBackground(java.awt.Color.BLACK);
                return button;
            }
        });
        
        this.getVerticalScrollBar().setUI(new com.sun.java.swing.plaf.motif.MotifScrollBarUI(){
            @Override 
            protected void configureScrollBarColors(){
                this.thumbColor=java.awt.Color.YELLOW;
                this.trackColor=java.awt.Color.BLACK;
            }

            @Override
            protected javax.swing.JButton createIncreaseButton(int i) {
                javax.swing.JButton button=super.createIncreaseButton(i);
                button.setBackground(java.awt.Color.BLACK);
                return button;
            }

            @Override
            protected javax.swing.JButton createDecreaseButton(int i) {
                javax.swing.JButton button=super.createDecreaseButton(i);
                button.setBackground(java.awt.Color.BLACK);
                return button;
            }
        });
        
        
    }

    

    
    
    
}
