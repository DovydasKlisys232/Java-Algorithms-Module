/*
    This program is done to create a method that takes an integer as a parameter and throws an exception
    if the number is odd. Only even numbers are allowed.
*/



public class Odd {
    public static void checkEven(int num) {
        if (num % 2 != 0) {
            throw new IllegalArgumentException("The number " + num + " is odd.");
        }
    }

    public static void main(String[] args) {
        int n = 18;
        try {
            checkEven(n);
            System.out.println(n + " is an even number.");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
        int m = 17;
        try {
            checkEven(m);
            System.out.println(m + " is an even number.");
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }
}
