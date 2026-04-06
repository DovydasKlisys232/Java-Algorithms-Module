/* 
    Title: Mileage.java
    By: D. Klisys & J. Obong
    Date: 12/3/26
    Purpose: This program takes in a text file that stores data on a car's mileage and how much they fill up a car till full through different days.
    The program first ensures that the user passes arguments correctly on the terminal (must be .txt files with correctly formatted data).
    It then stores all the data and computes the efficiency in liters per 100km for each subsequent date.
    after computing all efficiencies it find the minimum efficiency, max efficiency and the average over all dates.
*/ 

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// Thrown when there are insufficient arguments or argument is not a .txt file
class ArgumentException extends Exception {

    ArgumentException(String message) {
        super(message);
    }
}

// Thrown when file is not found or file is empty
class FileException extends Exception {

    FileException(String message) {
        super(message);
    }
}

/*---------------------------------------------- Main Program ---------------------------------------------*/

public class CA2 {
    // class to store records and efficiency calculations
    private static class MileageRec {

        private LocalDate date;
        private int mileage;
        private float liters;
        private float efficiency;

        public MileageRec(LocalDate d, int m, float l) {
            date = d;
            mileage = m;
            liters = l;
        }

        // calculates efficiency in liters per 100km
        public float calcEfficiency(MileageRec previous) {
            efficiency = this.liters / (this.mileage - previous.mileage) * 100;
            return efficiency;
        }

    }

    // Check if file is in arguments and file is a txt type
    // ArgumentException is caught in main
    static void checkArgs(String[] args) throws ArgumentException {
        if (args.length == 0) {
            throw new ArgumentException("Incorrect number of arguments ");
        }

        if (!args[0].endsWith(".txt")) {
            throw new ArgumentException("Argument is not a .txt");
        }
    }

    // Read file function
    // Makes sure file exists and is not empty, then declares arraylist to store records and formats date to EU format
    // reads in all data into array list
    // sorts date by order (bubble sort)
    static ArrayList<MileageRec> ScanThenSort(File t) throws FileException {
        try {
            Scanner scan = new Scanner(t);
            ArrayList<MileageRec> list = new ArrayList<MileageRec>();
            DateTimeFormatter EUFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // file empty check
            if (!scan.hasNext()) {
                scan.close();
                throw new FileException("File is empty");
            }

            // Skip header line
            if (scan.hasNextLine()) {
                scan.nextLine();
            }

            // add new data to list
            while (scan.hasNext()) {
                list.add(new MileageRec(LocalDate.parse(scan.next(), EUFormat), scan.nextInt(), scan.nextFloat()));
            }

            list.sort((a, b) -> a.date.compareTo(b.date));

            System.out.println();
            scan.close();
            return list;

        } catch (FileNotFoundException e) {
            throw new FileException("File not found");
        }
    }

    // Calculations interface
    // calculates the efficiency from list that has all text file data
    // computes the minimum, maximum and average efficiency
    public static class Calculations implements Runnable {

        private ArrayList<MileageRec> list;

        Calculations(ArrayList<MileageRec> list) {
            this.list = list; 
        }

        // overriden run method 
        public void run() {
            for (int i = 1; i < list.size(); i++) {
                MileageRec current = list.get(i);
                MileageRec previous = list.get(i - 1);
                current.calcEfficiency(previous);
            }

            float max = list.get(1).efficiency;
            float min = list.get(1).efficiency;
            float total_efficiency = 0;

            for (int i = 1; i < list.size(); i++) {
                if (min > list.get(i).efficiency) {
                    min = list.get(i).efficiency;
                }

                if (max < list.get(i).efficiency) {
                    max = list.get(i).efficiency;
                }
                total_efficiency += list.get(i).efficiency;
            }

            float average = total_efficiency / (list.size() - 1);

            // prints out values to 2 decimal points
            System.out.printf("min: %.2f%n", min);
            System.out.printf("max: %.2f%n", max);
            System.out.printf("average: %.2f%n", average);
        }
    }

    public static void main(String[] args) {
        try {
            checkArgs(args);

            // for multiple text files
            for (int i = 0; i < args.length; i++) {
                ArrayList<MileageRec> list = ScanThenSort(new File(args[i]));

                Calculations c = new Calculations(list);
                Thread calculationThread = new Thread(c);
                calculationThread.start();
            }
                
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
