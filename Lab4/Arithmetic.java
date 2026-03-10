/*
    This program throws an ArithmeticException exception and catch it using try-catch.

*/

public class Arithmetic {
    public static void divide(int a, int b) {
        if (a == 0 || b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        else {
            System.out.println("Result: " + (a / b));
        }
    }
    
    public static void main(String[] args) {
        try {
            divide(10, 1); // This will throw an ArithmeticException
        }
        catch (ArithmeticException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}
