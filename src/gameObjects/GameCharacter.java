package gameObjects;

/**
 *
 * @author Maciej Żak
 */
public class GameCharacter extends GameObject{

    @Override
    public void nextFrame() {
        int graphicX=this.getGraphicX();
        int x=this.getX();
        int graphicY=this.getGraphicY();
        int y=this.getY();
        if(graphicX!=x){
            this.setGraphicX(graphicX+(x-graphicX));
        }
        if(graphicY!=y){
            this.setGraphicY(graphicY+(y-graphicY));
        }
    }
    
}
