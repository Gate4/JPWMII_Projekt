package gameObjects;

import java.awt.Graphics;

/**
 *
 * @author Maciej Żak
 */
public abstract class GameObject {
    private byte x;
    private byte y;
    private byte graphicIndex;
    private byte frameIndex;
    private int graphicX;
    private int graphicY;
    private double graphicScale;
    private double graphicRotation;
    
    public static final byte NORTH=0;
    public static final byte NORTHEAST=1;
    public static final byte EAST=2;
    public static final byte SOUTHEAST=3;
    public static final byte SOUTH=4;
    public static final byte SOUTHWEST=5;
    public static final byte WEST=6;
    public static final byte NORTHWEST=7;
    
    public abstract void paint(Graphics graphic);
    public abstract void nextFrame();
    public abstract void moveToPoint(byte newX,byte newY);
    public abstract void moveInDirection(byte direction);

    public byte getX() {
        return x;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public byte getY() {
        return y;
    }

    public void setY(byte y) {
        this.y = y;
    }

    public byte getGraphicIndex() {
        return graphicIndex;
    }

    public void setGraphicIndex(byte graphicIndex) {
        this.graphicIndex = graphicIndex;
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

    public double getGraphicScale() {
        return graphicScale;
    }

    public void setGraphicScale(double graphicScale) {
        this.graphicScale = graphicScale;
    }

    public double getGraphicRotation() {
        return graphicRotation;
    }

    public void setGraphicRotation(double graphicRotation) {
        this.graphicRotation = graphicRotation;
    }
    
    
}
