package gameGUI;

/**
 *
 * @author Maciej Żak
 */
public class GameImage{
    private int frameWidth;
    private int frameHeight;
    private int length;
    private java.awt.image.BufferedImage image;

    
    public GameImage(int frameWidth, int frameHeight, int length, java.awt.image.BufferedImage image) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.length = length;
        this.image=image;
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
    
    public java.awt.image.BufferedImage getFrame(int index){
        try{
            return this.image.getSubimage(this.frameWidth*index, 0, this.frameWidth,
                    this.frameHeight);
        }catch(java.awt.image.RasterFormatException ex){
            return new java.awt.image.BufferedImage(1, 1, java.awt.image.BufferedImage.TYPE_INT_RGB);
        }
    }
    
}
