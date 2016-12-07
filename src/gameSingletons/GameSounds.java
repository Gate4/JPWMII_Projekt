package gameSingletons;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Maciej Żak
 */
public class GameSounds {
    private static GameSounds instance;

    private GameSounds() {
    }
    
    public static GameSounds getInstance() {
        if (instance == null) {
            instance = new GameSounds();
        }
        return instance;
    }
    
    public void playSound(String sound){
        try{
            Clip c=AudioSystem.getClip();
            BufferedInputStream bIS=new BufferedInputStream(getClass().getResourceAsStream(sound));
            c.open(AudioSystem.getAudioInputStream(bIS));
            c.setFramePosition(0);
            c.start();
            bIS.close();
        }catch(Exception ex){}
        
    }
    
}
