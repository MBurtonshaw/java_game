import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Utility {
    public static void playSound(String path) {
        try {
            File soundFile = new File("C:\\Users\\Student\\Documents\\code\\game\\sounds\\" + path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }
}
