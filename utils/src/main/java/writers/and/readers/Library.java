package writers.and.readers;

import java.util.concurrent.Semaphore;

/**Library for Writers and Readers to use.**/
public class Library {
    /**readerSemaphore is a semaphore used to manage Reader threads**/
    private final Semaphore readerSemaphore;
    /**writerSemaphore is a semaphore used to manage Reader threads**/
    private final Semaphore writerSemaphore;
    /**readers is int tracking number of readers currently in the library**/
    private int readers;
    /**writers is int tracking number of writers currently in the library**/
    private int writers;
    /**number of created Reader objects**/
    private final int numberOfReaders;
    /**number of created Writer objects**/
    private final int numberOfWriters;
    /**int counting how many Readers went into the library in this turn for readers**/
    private int counterReadsInThisTurn;
    /**int counting how many Writers went into the library in this turn for writers**/
    private int counterWritesInThisTurn;
    /**int tracking number of writers that are not inside the library**/
    private int writersQueue;
    /**int tracking number of readers that are not inside the library**/
    private int readersQueue;

    /**Initializes a new instance of this class.
     * @param numberOfWriters number of writers created in the queue to library
     * @param numberOfReaders number of readers created in the queue to library
     **/
    public Library(int numberOfReaders, int numberOfWriters){
        this.readerSemaphore = new Semaphore(5);
        this.writerSemaphore = new Semaphore(1);
        writers = 0;
        writersQueue = numberOfWriters;
        readers = 0;
        readersQueue = numberOfReaders;
        counterReadsInThisTurn = numberOfReaders;
        if(numberOfWriters==0) {
            counterReadsInThisTurn = numberOfWriters;
        }
        counterWritesInThisTurn = 0;
        this.numberOfReaders = numberOfReaders;
        this.numberOfWriters = numberOfWriters;
    }

    /**
     * Requests Permit to Read by Reader.
     * @param name name of created reader
     * @throws InterruptedException Thrown when a Reader thread is waiting, sleeping, or otherwise occupied.**/
    public void requestRead(String name) throws InterruptedException {
        System.out.println(name + " chce wejść do biblioteki.");
        synchronized (this) {
            while (writers > 0 || readers >= 5 || counterReadsInThisTurn >= numberOfReaders) {
                wait(1000);
            }
            readerSemaphore.acquire();
            readersQueue--;
            System.out.println(name + " wchodzi i czyta.");
            System.out.println("Pisarze w kolejce: "+writersQueue+" Czytelnicy w kolejce: "+(readersQueue));
            System.out.println("Pisarze w bibliotece: "+writers+" Czytelnicy w bibliotece: "+(readers+1));
            readers++;
            if(counterReadsInThisTurn+1==numberOfReaders){
                counterWritesInThisTurn = 0;
            }
            counterReadsInThisTurn++;
        }
    }

    /**
     * Requests Permit to Write by Writer.
     * @param name name of created writer
     * @throws InterruptedException Thrown when a Writer thread is waiting, sleeping, or otherwise occupied.**/
    public void requestWrite(String name) throws InterruptedException {
        System.out.println(name + " chce wejść do biblioteki.");
        synchronized (this) {
            while (readers > 0 || writers > 0 || counterWritesInThisTurn >= numberOfWriters) {
                wait(1000);
            }
            writerSemaphore.acquire();
            writersQueue--;
            System.out.println(name + " wchodzi i pisze.");
            System.out.println("Pisarze w kolejce: "+(writersQueue)+" Czytelnicy w kolejce: "+readersQueue);
            System.out.println("Pisarze w bibliotece: "+(writers+1)+" Czytelnicy w bibliotece: "+(readers));
            writers++;
            if(counterWritesInThisTurn+1==numberOfWriters){
                counterReadsInThisTurn = 0;
            }
            counterWritesInThisTurn++;
        }
    }

    /**
     * Finishes Reader action.
     * @param name name of created reader
     **/
    public void finishRead(String name){
        readerSemaphore.release();
        synchronized (this) {
            System.out.println(name + " opuszcza bibliotekę.");
            readers--;
            readersQueue++;
        }
    }

    /**
     * Finishes Writer action.
     * @param name name of created writer
     **/
    public void finishWrite(String name){
        writerSemaphore.release();
        synchronized (this) {
            System.out.println(name + " opuszcza bibliotekę.");
            writers--;
            writersQueue++;
        }
    }
}

