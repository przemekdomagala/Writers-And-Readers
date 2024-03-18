import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import writers.and.readers.Library;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void requestReadTest() throws InterruptedException {
        Library library = new Library(5, 0);
        library.requestRead("Czytelnik");
        String[] lines = outputStream.toString().split(System.lineSeparator());
        assertEquals("Czytelnik chce wejść do biblioteki.", lines[0]);
    }

    @Test
    void requestWriteTest() throws InterruptedException {
        Library library = new Library(5, 1);
        library.requestWrite("Pisarz");
        String[] lines = outputStream.toString().split(System.lineSeparator());
        assertEquals("Pisarz chce wejść do biblioteki.", lines[0]);
    }

    @Test
    void finishReadTest() {
        Library library = new Library(5, 0);
        library.finishRead("Czytelnik");
        String[] lines = outputStream.toString().split(System.lineSeparator());
        assertEquals("Czytelnik opuszcza bibliotekę.", lines[0]);
    }

    @Test
    void finishWriteTest() {
        Library library = new Library(5, 1);
        library.finishWrite("Pisarz");
        String[] lines = outputStream.toString().split(System.lineSeparator());
        assertEquals("Pisarz opuszcza bibliotekę.", lines[0]);
    }
}