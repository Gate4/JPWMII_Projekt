package gameObjects;

import gameSingletons.GameLogic;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Maciej Żak
 */
public abstract class GameObject extends Observable{
    private List<Observer> observers;
    private int x;
    private int y;
    private int imageIndex;
    private int frameIndex;
    private int graphicX;
    private int graphicY;
    private double graphicRotation;
    private boolean walkable;
    private boolean physical;
    private boolean animating;

    public GameObject(int x, int y, int imageIndex, int frameIndex, int graphicX, int graphicY, double graphicRotation, boolean walkable, boolean physical) {
        this.animating=false;
        this.observers = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.imageIndex = imageIndex;
        this.frameIndex = frameIndex;
        this.graphicX = graphicX;
        this.graphicY = graphicY;
        this.graphicRotation = graphicRotation;
        this.walkable = walkable;
        this.physical = physical;
    }
    
    public void paint(java.awt.Graphics graphic){
        java.awt.Graphics2D g2D=(java.awt.Graphics2D)graphic.create();
        java.awt.image.BufferedImage frame=gameSingletons.GameGraphics.getInstance().getGameImage(this.imageIndex).getFrame(this.frameIndex);
        //g2D.rotate(Math.toRadians(this.graphicRotation),this.x+frame.getWidth()/2,this.y+frame.getHeight()/2);
        g2D.drawImage(frame.getScaledInstance(gameSingletons.GameGraphics.DEFAULT_TILE_SIZE, gameSingletons.GameGraphics.DEFAULT_TILE_SIZE,java.awt.image.BufferedImage.SCALE_SMOOTH),this.graphicX, this.graphicY, null);
    }
    
    
    
    public void move(int xDirection, int yDirection){
        this.x+=xDirection;
        this.y+=yDirection;
    }
    
    public void updateGraphicPosition(){
        int width=gameSingletons.GameGraphics.DEFAULT_TILE_SIZE;
        int height=gameSingletons.GameGraphics.DEFAULT_TILE_SIZE;
        this.graphicX=this.x*width;
        this.graphicY=this.y*height;
    }
    
    public void moveTo(int newX, int newY){
        this.setX(newX);
        this.setY(newY);
    }
    
    public boolean isColliding(GameObject o){
        return this.physical&&o.physical&&this.x==o.x&&this.y==o.y;
    }
    
    public boolean isCollidingAtXY(int x,int y){
        return this.physical&&this.x==x&&this.y==y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        notifyObservers();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        notifyObservers();
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

    public boolean isAnimating() {
        return animating;
    }

    public void setAnimating(boolean animating) {
        this.animating = animating;
    }
    
    
    
    @Override
    public synchronized boolean hasChanged() {
        return true;
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this, this);
        }
    }

    @Override
    public synchronized void addObserver(Observer obsrvr) {
        observers.add(obsrvr);
        notifyObservers();
    }

    @Override
    public void notifyObservers(Object o) {
        for (Observer observer : observers) {
            observer.update(this, this);
        }
    }
    
    

}
