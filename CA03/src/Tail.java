/*
    Student: Dovydas Klisys
    ID: B00165094
    Date: 2/4/26
    Purpose: This program allows users to specify the number of words they get to read from the bottom of a text file. The program describes how a tail command line 
    function would work. The program has a simple flow:
    - check the arguments: there must be only one argument, it must be an integer larger than 0
    - After check, save argument to int variable. This will be used to as the size of the tail queue
    - initialize the tail object with user input value
    - read the file and save words to queue. Once queue is full remove front word and add new word afterwards to back of queue. this continues until last k words are saved
    - finally print out the tail.
*/

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

// Thrown when file is not found or file is empty
class FileException extends Exception {
    FileException(String message) {
        super(message);
    }
}

// Thrown when arguments don't match specification of program
class ArgumentException extends Exception {
    ArgumentException(String message) {
        super(message);
    }
}

public class Tail {
    // Attributes: k is the user input, words stores the tail in a queue
    private ArrayBlockingQueue<String> words; 
    private int k;  

    Tail(int max) {
        k = max;
        words = new ArrayBlockingQueue<String>(max);
    }

    // function to read the users input text file through intput stream
    // creates an ArrayBlockingQueue to hold the tail words
    // removes and adds words until tail of text file remain
    public void readFile() throws FileException, ArgumentException {
        Scanner scan = new Scanner(System.in);

        if (!scan.hasNext()) {
            scan.close();
            throw new FileException("File is empty");
        }

        while (scan.hasNext()) {
            if (words.remainingCapacity() == 0) {
                words.remove();
            }
            words.add(scan.next());
        }

        scan.close();
    }
    
    public void printTail() {
        for (String s : words) {
            System.out.print(s + " ");
        }
        System.out.println(" ");
    }

    // check arguments function
    // if arguments are ok then return as integer
    // if user inputs --help then instructions will be shown
    // there must only be one argument and it must be an int or else an exception is thrown
    public static void checkArgs(String[] args) throws ArgumentException {
        try {
            if (args.length != 1) {
                throw new ArgumentException("Incorrect number of arguments ");
            }
            if (args[0].equals("--help")) {
                System.out.println("You must specify an integer value for how many words from the bottom of the file are to be read");
                System.out.println("Don't forget to pass text file into system input!");
                System.exit(0);
            } 
            else if (Integer.parseInt(args[0]) <= 0) {
                throw new ArgumentException("input a number larger than 0");
            }
        }
        catch (NumberFormatException e) {
            throw new ArgumentException("Argument must be an integer");
        }
    }

    public static void main(String[] args) {
        try {
            checkArgs(args);
            int k = Integer.parseInt(args[0]);
            Tail tail = new Tail(k);
            tail.readFile();
            tail.printTail();
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
