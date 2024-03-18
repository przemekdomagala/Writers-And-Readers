package writers.and.readers;

import java.util.Scanner;

/**Application's Main class with main method.**/
public class Main {
    /**Application's main method that creates requested number of Readers and Writers, and runs them.
     * @param args The command line arguments.
     **/
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Podaj liczbę czytelników: ");
        int numberOfReaders;
        int numberOfWriters;
        try {
            numberOfReaders = Integer.parseInt(s.nextLine());
        }catch (Exception e) {
            System.out.println("Podany ciąg znaków jest pusty lub nie jest liczbą, przypisano liczbę czytelników: 10");
            numberOfReaders = 10;
        }
        System.out.println("Podaj liczbę pisarzy: ");
        try {
            numberOfWriters = Integer.parseInt(s.nextLine());
        }catch (Exception e) {
            System.out.println("Podany ciąg znaków jest pusty lub nie jest liczbą, przypisano liczbę pisarzy: 3");
            numberOfWriters = 3;
        }
        s.close();
        Library library = new Library(numberOfReaders, numberOfWriters);
        for(int i=0; i<numberOfWriters; i++){
            Writer writer = new Writer(library, "Pisarz "+(i+1));
            writer.start();
        }
        for(int i=0; i<numberOfReaders; i++){
            Reader reader = new Reader(library, "Czytelnik "+(i+1));
            reader.start();
        }
    }
}

