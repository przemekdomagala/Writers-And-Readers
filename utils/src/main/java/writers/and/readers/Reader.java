package writers.and.readers;

import lombok.Getter;

/**Reader Thread class representing program's reader that wants to read in the library.**/
public class Reader extends Thread{
    /**Library - resource that Reader Thread is using**/
    private final Library library;
    /**Name of created Reader**/
    private final String name;
    /**boolean informing that Thread finished that turn of loop - helps with appropriate testing**/
    @Getter
    private boolean finished;

    /**Initializes a new instance of this class.**/
    public Reader(Library library, String name) {
        this.library = library;
        this.name = name;
        finished = false;
    }

    /**Overridden Thread method that runs Reader Thread.**/
    @Override
    public void run() {
        while (true){
            try{
                library.requestRead(name);
                Thread.sleep(1000);
                library.finishRead(name);
                Thread.sleep(6000);
                finished = true;
            }catch (InterruptedException e){
                this.interrupt();
            }
        }
    }
}
