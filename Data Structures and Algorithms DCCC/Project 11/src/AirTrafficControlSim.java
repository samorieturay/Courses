import java.util.*;

public class AirTrafficControlSim {
    private static final int MIN_FLIGHT_SPACING = 10;
    private static final int MAX_QUEUE_SIZE = 5;

    private int timeInterval = 0;
    private int fltSpacingCounter = 0;
    private int numberOfArrivals = 0;
    private int numberOfDepartures = 0;
    private int numberOfRefusedArrivals = 0;
    private int numberOfRefusedDepartures = 0;
    private int numberOfDivertedArrivals = 0;
    private int arrivalQueueEmpty = 0;
    private int departureQueueEmpty = 0;

    private int flightNumberCounter = 1;

    private ArrayDeque<Flight> arrivalQueue = new ArrayDeque<>();
    private ArrayDeque<Flight> departureQueue = new ArrayDeque<>();
    private ArrayList<Flight> arrivalStatistics = new ArrayList<>();
    private ArrayList<Flight> departureStatistics = new ArrayList<>();

    private Random random = new Random(System.nanoTime());

    public static void main(String[] args) {
        AirTrafficControlSim atc = new AirTrafficControlSim();
        atc.doSimulation();
    }

    public void doSimulation() {
        double meanArrivalFreq = 0.0;
        double meanDepartureFreq = 0.0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter mean departure frequency (0.0 > df < 1.0): ");
        if (scanner.hasNextDouble())
            meanDepartureFreq = scanner.nextDouble();
        System.out.println("Enter mean arrival frequency   0.0 > af < 1.0): ");
        if (scanner.hasNextDouble())
            meanArrivalFreq = scanner.nextDouble();
        // Check if total probability of arrivals + departures > 100%
        if (meanDepartureFreq + meanArrivalFreq > 1.0) {
            System.out.println("Mean departure frequency plus mean arrival frequency cannot exceed 100%. Try again...");
            return;
        }
        for (int i = 0; i < 1440; i++) {
            processArrival(meanArrivalFreq);
            processDeparture(meanDepartureFreq);
            if (arrivalQueue.size() == 0)
                arrivalQueueEmpty++;
            if (departureQueue.size() == 0)
                departureQueueEmpty++;
            timeInterval++;
            fltSpacingCounter++;
        }
        printSimSummaryStatistics();
    }

    public void processArrival(double meanArrivalFreq) {
        int count = 0;
        if ((count = getPoissonRandom(meanArrivalFreq)) > 0)
            addToArrivalQueue(count);
        if (fltSpacingCounter >= MIN_FLIGHT_SPACING) {
            if (arrivalQueue.size() > 0) {
                removeFromArrivalQueue();
                fltSpacingCounter = 0;
            }
        }
    }

    public void processDeparture(double meanDepartureFreq) {
        int count = 0;
        if ((count = getPoissonRandom(meanDepartureFreq)) > 0)
            addToDepartureQueue(count);
        if (fltSpacingCounter >= MIN_FLIGHT_SPACING) {
            if (departureQueue.size() > 0 && arrivalQueue.size() == 0) {
                removeFromDepartureQueue();
                fltSpacingCounter = 0;
            }
        }
    }

    public void addToArrivalQueue(int count) {
        for (int i = 0; i < count; i++) {
            Flight arrivalFlight = new Flight("AA" + flightNumberCounter++, FlightType.Arrival);
            if (arrivalQueue.size() < MAX_QUEUE_SIZE) {
                arrivalFlight.setMinuteInQueue(timeInterval);
                arrivalQueue.add(arrivalFlight);
            } else {
                this.numberOfRefusedArrivals++;
                System.out.println("Arrival queue full. Flight " + arrivalFlight + " rerouted at: " + timeInterval / 60 + ":" + String.format("%02d", timeInterval % 60) + " hours");
            }
        }
    }

    public void removeFromArrivalQueue() {
        if (arrivalQueue.size() > 0) {
            Flight arrivalFlight = arrivalQueue.removeFirst();
            arrivalFlight.setMinuteOutQueue(timeInterval);
            arrivalStatistics.add(arrivalFlight);
            System.out.println("Flight " + arrivalFlight + " arrived at: " + timeInterval / 60 + ":" + String.format("%02d", timeInterval % 60) + " hours");
            numberOfArrivals++;
        }
    }

    public void addToDepartureQueue(int count) {
        for (int i = 0; i < count; i++) {
            Flight departureFlight = new Flight("AA" + flightNumberCounter++, FlightType.Departure);
            if (departureQueue.size() < MAX_QUEUE_SIZE) {
                departureFlight.setMinuteInQueue(timeInterval);
                departureQueue.add(departureFlight);
            } else {
                this.numberOfRefusedDepartures++;
                System.out.println("Departure queue full. Flight " + departureFlight + " rerouted at: " + timeInterval / 60 + ":" + String.format("%02d", timeInterval % 60) + " hours");
            }
        }
    }

    public void removeFromDepartureQueue() {
        if (departureQueue.size() > 0) {
            Flight departureFlight = departureQueue.removeFirst();
            departureFlight.setMinuteOutQueue(timeInterval);
            departureStatistics.add(departureFlight);
            System.out.println("Flight " + departureFlight + " departed at: " + timeInterval / 60 + ":" + String.format("%02d", timeInterval % 60) + " hours");
            numberOfDepartures++;
        }
    }

    public int getPoissonRandom(double mean) {
        double L = Math.exp(-mean);
        int x = 0;
        double p = 1.0;
        do {
            p = p * random.nextDouble();
            x++;
        } while (p > L);
        return x - 1;
    }

    public void printSimSummaryStatistics() {
        // Print simulation summary statistics here
    }

    enum FlightType {Arrival, Departure}

    class Flight {
        String flightNumber;
        FlightType flightType;
        int minuteInQueue;
        int minuteOutQueue;

        // constructor
        public Flight(String flightNumber, FlightType flightType) {
            this.flightNumber = flightNumber;
            this.flightType = flightType;
        }

        public String toString() {
            return flightType + ": " + flightNumber;
        }

        // "minute" that flight entered the queue
        public void setMinuteInQueue(int minute) {
            this.minuteInQueue = minute;
        }

        // "minute" that flight exits the queue
        // difference is time in queue
        public void setMinuteOutQueue(int minute) {
            this.minuteOutQueue = minute;
        }

        public int timeInQueue() {
            return minuteOutQueue - minuteInQueue;
        }
    }
}
