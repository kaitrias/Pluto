
import java.awt.*;

//for watcher methods
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.*;


//import java.util.concurrent.TimeUnit;


import java.io.*;

//1440 900


public class runBot {
    public static void main(String[] args) throws AWTException, IOException, InterruptedException
    {
        DataInputStream str = new DataInputStream(new FileInputStream("/Users/kaitrias/Developer/runelite-lul/itsgonnawork.txt"));

        while(true) {
            while (str.available() > 0) {
                double dd = str.readDouble();
                System.out.println(dd);
                System.out.println(str.available());
            }

            WatcherTest ts = new WatcherTest();
            ts.watchFile();

        }
    }
}
