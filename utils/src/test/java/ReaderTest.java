import org.junit.jupiter.api.Test;
import writers.and.readers.Library;
import writers.and.readers.Reader;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReaderTest {

    @Test
    void readerTest() {
        Library library = new Library(5, 0);
        Reader reader = new Reader(library, "Czytelnik");
        reader.start();
        await().atMost(4000, TimeUnit.SECONDS).untilAsserted(() -> assertTrue(reader.isFinished()));
    }

}