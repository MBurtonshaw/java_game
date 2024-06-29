import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {

    public static String getBLACK() {
        return "\u001B[30m";
    }

    public static String getBLUE() {
        return "\u001B[34m";
    }

    public static String getCYAN() {
        return "\u001B[36m";
    }

    public static String getGREEN() {
        return "\u001B[32m";
    }

    public static String getMAGENTA() {
        return "\u001B[35m";
    }

    public static String getRED() {
        return "\u001B[31m";
    }

    public static String getWHITE() {
        return "\u001B[37m";
    }

    public static String getYELLOW() {
        return "\u001B[33m";
    }

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

    public static void logToFile(String description) {
        final String LOG_FILE = "highScores.csv";
        LocalDateTime localDate = LocalDateTime.now();
        try (FileWriter fileWriter = new FileWriter(LOG_FILE, true);
             PrintWriter writer = new PrintWriter(fileWriter, true)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String formattedDate = localDate.format(formatter);
            writer.println(formattedDate + " " + description);
        } catch (IOException e) {
            System.out.println("Fail to log data: " + e.getMessage());
        }
    }

}
