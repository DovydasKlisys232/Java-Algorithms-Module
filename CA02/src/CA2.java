// CA2
// By D. Klisys & J. Obong

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class ArgumentException extends Exception {
    ArgumentException(String message) {
        super(message);
    }
}

class FileException extends Exception {
    FileException(String message) {
        super(message);
    }
}

class EmptyFileException extends Exception {

    EmptyFileException(String message) {
        super(message);
    }
}

public class CA2 {
    // class to store records
    private class MileageRec {
        private LocalDate date;
        private int mileage;
        private float liters;
        private float efficiency;

        public MileageRec(LocalDate d, int m, float l) {
            date = d;
            mileage = m;
            liters = l;
        }

        /*
        public void calcEfficiency(){
        
        }
        */
    }


    // Check if file is in arguments and file is a txt type
    static void checkArgs(String[] args) throws ArgumentException, FileException {
        if (args.length != 1) {
            throw new ArgumentException("Incorrect number of arguments ");
        } else {
            if (!args[1].endsWith(".txt")) {
                throw new FileException("Incorrect file structure");
            }
        }
    }

    // Read file function
    void ReadFile(File t) throws FileNotFoundException, EmptyFileException {
        try {
            Scanner scan = new Scanner(t);
            ArrayList<MileageRec> list = new ArrayList<MileageRec>();

            if (!scan.hasNext()) {
                throw new EmptyFileException("File is empty");
            }

            while (scan.hasNext()) {
                list.add(new MileageRec(LocalDate.parse(scan.next()), scan.nextInt(), scan.nextFloat()));
            }
            
            list.sort((a, b) -> a.date.compareTo(b.date));
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).date);
            }

        } 
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    /*
    arraylist<record> l
    while(hasnext) {
        l.add(new records(Dateparse(s.next(), s.nextInt(), s.nextFloat())))
    }
    */

    /*
    efficiency(prev){
        return (this-prev)
    }
    */

    
    public static void main (String[] args) {
        try {
            checkArgs(args);

            File test = new File(args[1]);

            ReadFile(test);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
}
