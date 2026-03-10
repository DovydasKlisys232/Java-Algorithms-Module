/*
    This program is done to create a method that takes an integer as a parameter and throws an exception
    if the number is negative. Only positive numbers are allowed.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Positive {
    public static void readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            if (scanner.hasNextFloat()) {
                float value = scanner.nextFloat();
                if (value > 0)
                    throw new IllegalArgumentException("Positive number found: " + value);
                else
                    System.out.println(value + " ");
            } else if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value > 0)
                    throw new IllegalArgumentException("Positive number found: " + value);
                else
                    System.out.println(value + " ");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        try {
            readFile(args[0]);
        } 
        catch (FileNotFoundException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}
