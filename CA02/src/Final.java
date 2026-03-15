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

public class Final {

    // task for thread that will implement calculations
    public class CalculationThread implements Runnable {
        private ArrayList<MileageRec> list;

        float min, max, average;

        CalculationThread(ArrayList<MileageRec> list) {
            this.list = list;  // same list object — no copying
        }

        public void run() {
            for (int i = 0; i < list.size(); i++) {
                MileageRec current = list.get(i);
                if (i != 0) {
                    MileageRec previous = list.get(i - 1);
                    current.calcEfficiency(previous);
                }
            }

            float max = list.get(1).efficiency;
            float min = list.get(1).efficiency;
            float total_efficiency = 0;

            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    if (min < list.get(i).efficiency) {
                        min = list.get(i).efficiency;
                    }

                    if (max > list.get(i).efficiency) {
                        max = list.get(i).efficiency;
                    }
                    total_efficiency += list.get(i).efficiency;
                }
            }

            float average = total_efficiency / (list.size() - 1);

            System.out.println("Statistics on car efficiency: ");
            System.out.println("min: " + min);
            System.out.println("max: " + max);
            System.out.println("average: " + average);
            System.out.println(" ");
        }
    }

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
    // sorts date by order (bubble sort) and prints out sorted dates
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

            for (int i = 0; i < list.size(); i++) {
                System.out.println("date: " + list.get(i).date.format(EUFormat) + " mileage: " + list.get(i).mileage + " fill-up: " + list.get(i).liters);
            }

            System.out.println();
            scan.close();
            return list;

        } catch (FileNotFoundException e) {
            throw new FileException("File not found");
        }

    }

    // calculates the efficiency of each reading
    // ignore first element (0) of list, it doesnt contain an efficiency reading
    static void calculation(ArrayList<MileageRec> list) {
        System.out.println("<--------------------------Print dates with efficieny---------------------------->");
        for (int i = 0; i < list.size(); i++) {
            MileageRec current = list.get(i);
            if (i != 0) {
                MileageRec previous = list.get(i - 1);
                current.calcEfficiency(previous);
                System.out.println(list.get(i).efficiency);
            }
        }

        float max = list.get(1).efficiency;
        float min = list.get(1).efficiency;
        float total_efficiency = 0;

        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                if (min < list.get(i).efficiency) {
                    min = list.get(i).efficiency;
                }

                if (max > list.get(i).efficiency) {
                    max = list.get(i).efficiency;
                }
                total_efficiency += list.get(i).efficiency;
            }
        }

        float average = total_efficiency / (list.size() - 1);

        System.out.println("min: " + min);
        System.out.println("max: " + max);
        System.out.println("average: " + average);
        System.out.println(" ");
    }

    public static void main(String[] args) {
        try {
            checkArgs(args);

            // for multiple text files
            for (int i = 0; i < args.length; i++) {
                ArrayList<MileageRec> list = ScanThenSort(new File(args[i]));
                calculation(list);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
