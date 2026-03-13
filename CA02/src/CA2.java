// CA2
// By D. Klisys & J. Obong

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
            return (this.mileage - previous.mileage / this.liters) * 100;
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
    // sorts date by order (bubble sort) and prints out sorted dates
    static void ScanThenSort(File t) throws FileException {
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

            while (scan.hasNext()) {
                list.add(new MileageRec(LocalDate.parse(scan.next(), EUFormat), scan.nextInt(), scan.nextFloat()));
            }

            list.sort((a, b) -> a.date.compareTo(b.date));

            for (int i = 0; i < list.size(); i++) {
                MileageRec current = list.get(i+1);
                MileageRec previous = list.get(i-1);

                System.out.println(list.get(i).date + " ");
                System.out.print(current.calcEfficiency(previous));
            }
            System.out.println();
            scan.close();

        } catch (FileNotFoundException e) {
            throw new FileException("File not found");
        }

    }
    
    public static void main (String[] args) {
        try {
            checkArgs(args);

            // for multiple text files
            for (int i = 0; i < args.length ; i++) {
                ScanThenSort(new File(args[i]));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
}

