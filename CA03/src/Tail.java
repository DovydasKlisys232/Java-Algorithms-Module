/*
    Student: Dovydas Klisys
    ID: B00165094
    Date: 2/4/26
    Purpose: This program allows users to specify the number of words they get to read from the bottom of a text file. The program describes how a tail command line 
    function would work. A queue interface is used to scan in a full text file and then be able to pick out the last set k (user input) words to push out to read.
*/

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

// Thrown when file is not found or file is empty
class FileException extends Exception {

    FileException(String message) {
        super(message);
    }
}

class ArgumentException extends Exception {

    ArgumentException(String message) {
        super(message);
    }
}

// data class for describing how the data in the queue are stored


public class Tail {
    // check arguments function
    // if arguments are ok then return as integer
    public static int checkArgs(String[] args) throws ArgumentException{
        if (args.length != 1) {
            throw new ArgumentException("Incorrect number of arguments ");
        }
        else if(Integer.parseInt(args[0]) < 0){
            throw new ArgumentException("input a number larger than 0");
        }
        return Integer.parseInt(args[0]);
    }

    // function to read the users input text file through intput stream
    // creates an ArrayBlockingQueue to hold the tail words
    // removes and adds words until last words of text file remain
    public static ArrayBlockingQueue<String> readFile(String[] args) throws FileException, ArgumentException {
        Scanner scan = new Scanner(System.in);
        int k = checkArgs(args);

        if (!scan.hasNext()) {
            scan.close();
            throw new FileException("File is empty");
        }

        ArrayBlockingQueue<String> words = new ArrayBlockingQueue<String>(k);
        while (scan.hasNext()) {
            words.add(scan.next());
            if (words.remainingCapacity() == 0) {
                words.remove();
                words.add(scan.next());
            }
        }
        scan.close();
        return words;
    }
    
    // prints out the tail of text file
    public static void printTail(ArrayBlockingQueue<String> words) {
        Iterator<String> iterator = words.iterator();
        
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }

    public static void main(String[] args) {
        try {
            ArrayBlockingQueue<String> words = readFile(args);
            printTail(words);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
