/*
    This program reads a file and throws an exception if it is empty.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class empty {
    public static void CheckFile(String fileName) throws FileNotFoundException, EmptyFileException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        if (!scanner.hasNext()) {
            scanner.close();
            throw new EmptyFileException("File is empty: " + fileName);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        try {
            CheckFile(args[0]);
            System.out.println("File is not empty: " + args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        } catch (EmptyFileException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}

class EmptyFileException extends Exception {
    public EmptyFileException(String message) {
        super(message);
    }
}
