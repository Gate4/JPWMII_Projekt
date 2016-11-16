package gameGUI;

import java.io.IOException;

/**
 *
 * @author Maciej Żak
 */
public class GameImage{
    private int frameWidth;
    private int frameHeight;
    private int length;
    private java.awt.image.BufferedImage image;
    private String filename;

    
    public GameImage(int frameWidth, int frameHeight, int length, String filename) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.length = length;
        this.filename = filename;
        try {
            this.image=javax.imageio.ImageIO.read(getClass().getResource("/"+filename));
        } catch (IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Błąd wczytywania pliku "+filename,
                    "Błąd "+this.toString(), javax.swing.JOptionPane.ERROR_MESSAGE);
            this.image=new java.awt.image.BufferedImage(0, 0, java.awt.image.BufferedImage.TYPE_INT_RGB);
        }
    }
    
    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public java.awt.image.BufferedImage getFrame(int index){
        try{
            return this.image.getSubimage(this.frameWidth*index, 0, this.frameWidth, this.frameHeight);
        }catch(java.awt.image.RasterFormatException ex){
            javax.swing.JOptionPane.showMessageDialog(null, "Błąd wczytywania klatki "+index+" w pliku "+filename,
                    "Błąd "+this.toString(), javax.swing.JOptionPane.ERROR_MESSAGE);
            return new java.awt.image.BufferedImage(1, 1, java.awt.image.BufferedImage.TYPE_INT_RGB);
        }
    }
    
}
