
import java.awt.*;

import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;


//import java.util.concurrent.TimeUnit;


import java.io.*;

//1440 900


public class runBot {
    public static void main(String[] args) throws AWTException, IOException, InterruptedException
    {
        WatchService watcher = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("/Users/kaitrias/Developer/runelite-lul");

        try {
            WatchKey key = path.register(watcher,
                    ENTRY_CREATE,
                    ENTRY_DELETE,
                    ENTRY_MODIFY);
        } catch (IOException x) {
            System.err.println(x);
        }

        boolean valid = true;

        while(valid) {

            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();

                if (kind == ENTRY_MODIFY) {
                    DataInputStream str = new DataInputStream(new FileInputStream("/Users/kaitrias/Developer/runelite-lul/itsgonnawork.txt"));
                    double dd = str.readDouble();
                    System.out.println(dd);
                    System.out.println(filename);
                }
            }

            valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}
