import org.junit.jupiter.api.Test;
import writers.and.readers.Library;
import writers.and.readers.Writer;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WriterTest {

    @Test
    void writerTest() {
        Library library = new Library(5, 1);
        Writer writer = new Writer(library, "Pisarz");
        writer.start();
        await().atMost(4000, TimeUnit.SECONDS).untilAsserted(() -> assertTrue(writer.isFinished()));
    }

}