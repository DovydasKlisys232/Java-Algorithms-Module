/*
    This program is done to create a method that takes a string as a parameter and throws an exception
    if the file is not found. The method will check if the file exists and if it does not, it will throw an exception.
*/
import java.io.File; // Import the File class to check if a file exists

public class FileNotFound {
    public static void checkFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found: " + filename);
        }
    }
    public static void main(String[] args) {
        try {
            checkFile("nonexistentfile.txt"); // This will throw an IllegalArgumentException
            System.out.println("File exists.");
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
        try {
            checkFile("existentfile.txt"); // This will not throw an exception
            System.out.println("existentfile.txt File exists.");
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}
