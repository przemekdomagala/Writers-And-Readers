package writers.and.readers;

import lombok.Getter;

/**Writer Thread class representing program's writer that wants to write in the library.**/
public class Writer extends Thread{
    /**Library - resource that Writer Thread is using**/
    private final Library library;
    /**Name of created Writer**/
    private final String name;
    /**boolean informing that Thread finished that turn of loop - helps with appropriate testing**/
    @Getter
    private boolean finished;

    /**Initializes a new instance of this class.**/
    public Writer(Library library, String name) {
        this.library = library;
        this.name = name;
        finished = false;
    }

    /**Overridden Thread method that runs Reader Thread.**/
    @Override
    public void run() {
        while (true){
            try{
                library.requestWrite(name);
                Thread.sleep(1000);
                library.finishWrite(name);
                Thread.sleep(3000);
                finished = true;
            }catch (InterruptedException e){
                this.interrupt();
            }
        }
    }
}
