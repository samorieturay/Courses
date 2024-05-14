package datastructures.dccc.edu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadCSVWithScanner {

    public LinkedList<Flight> getFlightListFromCSV(String filePath) {
        LinkedList<Flight> fltList = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) { // Skipping the header line
                    firstLine = false;
                    continue;
                }

                Flight flt = new Flight();
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");
                int index = 0;
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == 0) {
                        flt.setFlightNumber(data);
                    } else if (index == 1) {
                        flt.setAircraftNumber(data);
                    } else if (index == 2) {
                        flt.setDestinationOrigin(data);
                    } else if (index == 3) {
                        flt.setFlightType(data);
                    } else if (index == 4) {
                        flt.setSchedule(data);
                    } else if (index == 5) {
                        flt.setOperationStatus(data);
                    } else {
                        System.out.println("Invalid data::" + data);
                    }
                    index++;
                }
                index = 0;
                fltList.add(flt);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return fltList;
    }


}