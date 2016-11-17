package gameObjects;

/**
 *
 * @author Maciej Żak
 */
public abstract class GameObject implements java.io.Serializable{

    private int x;
    private int y;
    private int imageIndex;
    private int frameIndex;
    private int graphicX;
    private int graphicY;
    private double graphicRotation;

    public GameObject(int x, int y, int imageIndex, int frameIndex, int graphicX, int graphicY, double graphicRotation) {
        this.x = x;
        this.y = y;
        this.imageIndex = imageIndex;
        this.frameIndex = frameIndex;
        this.graphicX = graphicX;
        this.graphicY = graphicY;
        this.graphicRotation = graphicRotation;
    }
    
    public void paint(java.awt.Graphics graphic, double scaleX, double scaleY){
        java.awt.Graphics2D g2D=(java.awt.Graphics2D)graphic.create();
        java.awt.image.BufferedImage frame=gameSingletons.GameGraphics.getInstance().getGameImage(this.imageIndex).getFrame(this.frameIndex);
        g2D.rotate(Math.toRadians(this.graphicRotation),this.x+frame.getWidth()/2,this.y+frame.getHeight()/2);
        g2D.scale(scaleX, scaleY);
        g2D.drawImage(frame,this.graphicX, this.graphicY, null);
    }

    public void nextFrame(){
        java.awt.image.BufferedImage frame=gameSingletons.GameGraphics.getInstance().getGameImage(this.imageIndex).getFrame(this.frameIndex);
        int width=frame.getWidth();
        int height=frame.getHeight();
        
        if(graphicX!=x*width){
            this.setGraphicX(graphicX+(x-graphicX));
        }
        if(graphicY!=y){
            this.setGraphicY(graphicY+(y-graphicY));
        }
    }
    
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

    public int getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public void setFrameIndex(int frameIndex) {
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

    public double getGraphicRotation() {
        return graphicRotation;
    }

    public void setGraphicRotation(double graphicRotation) {
        this.graphicRotation = graphicRotation;
    }

}
