package gameObjects;

/**
 *
 * @author Maciej Żak
 */
public abstract class GameObject implements java.io.Serializable{

    private int x;
    private int y;
    private byte imageIndex;
    private byte frameIndex;
    private int graphicX;
    private int graphicY;
    private double graphicScaleX;
    private double graphicScaleY;
    private double graphicRotation;

    public void paint(java.awt.Graphics graphic){
        java.awt.Graphics2D g2D=(java.awt.Graphics2D)graphic.create();
        java.awt.image.BufferedImage frame=gameSingletons.GameGraphics.getInstance().getGameImage(this.imageIndex).getFrame(this.frameIndex);
        g2D.rotate(Math.toRadians(this.graphicRotation),this.x+frame.getWidth()/2,this.y+frame.getHeight()/2);
        g2D.scale(this.graphicScaleX, this.graphicScaleY);
        g2D.drawImage(frame,this.graphicX, this.graphicY, null);
    }

    public abstract void nextFrame();
    
    public void move(int xDirection, int yDirection, int distanceX, int distanceY){
        if(xDirection!=0)xDirection/=Math.abs(xDirection);
        if(yDirection!=0)yDirection/=Math.abs(yDirection);
        this.x+=(distanceX)*xDirection;
        this.y+=(distanceY)*yDirection;
    }
    
    public void move(int xDirection, int yDirection){
        this.move(xDirection, yDirection,1, 1);
    }

    public void moveTo(int newX, int newY){
        this.setX(newX);
        this.setY(newY);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public byte getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(byte imageIndex) {
        this.imageIndex = imageIndex;
    }

    public byte getFrameIndex() {
        return frameIndex;
    }

    public void setFrameIndex(byte frameIndex) {
        this.frameIndex = frameIndex;
    }

    public int getGraphicX() {
        return graphicX;
    }

    public void setGraphicX(int graphicX) {
        this.graphicX = graphicX;
    }

    public int getGraphicY() {
        return graphicY;
    }

    public void setGraphicY(int graphicY) {
        this.graphicY = graphicY;
    }

    public double getGraphicScaleX() {
        return graphicScaleX;
    }

    public void setGraphicScaleX(double graphicScaleX) {
        this.graphicScaleX = graphicScaleX;
    }

    public double getGraphicScaleY() {
        return graphicScaleY;
    }

    public void setGraphicScaleY(double graphicScaleY) {
        this.graphicScaleY = graphicScaleY;
    }

    public double getGraphicRotation() {
        return graphicRotation;
    }

    public void setGraphicRotation(double graphicRotation) {
        this.graphicRotation = graphicRotation;
    }

}
