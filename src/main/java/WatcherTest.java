import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;


public class WatcherTest {

    public void watchFile() throws IOException, InterruptedException {
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

        while (true) {
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
                    System.out.println(filename);
                }

            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }

        };

    }
}
