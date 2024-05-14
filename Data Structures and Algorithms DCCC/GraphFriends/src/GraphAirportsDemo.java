import java.util.*;

class GraphAirports {

    // List of airports and their connections
    private Map<String, List<String>> adjList;

    public GraphAirports() {
        adjList = new HashMap<>();
    }

    public void addFlight(String departure, String destination) {
        adjList.computeIfAbsent(departure, k -> new ArrayList<>()).add(destination);
        adjList.computeIfAbsent(destination, k -> new ArrayList<>()).add(departure); // Assuming bidirectional flights
    }

    public void BFS(String startNode) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startNode);
        visited.add(startNode);

        System.out.println("Breadth-First Search (BFS):");
        while (!queue.isEmpty()) {
            String airport = queue.poll();
            System.out.print(airport + " ");

            List<String> connections = adjList.getOrDefault(airport, new ArrayList<>());
            for (String connection : connections) {
                if (!visited.contains(connection)) {
                    queue.add(connection);
                    visited.add(connection);
                }
            }
        }
        System.out.println();
        System.out.println();
    }

    public void printConnectedAirports() {
        for (Map.Entry<String, List<String>> entry : adjList.entrySet()) {
            String airport = entry.getKey();
            List<String> connectedAirports = entry.getValue();

            System.out.print(airport + " - connected airports: ");
            System.out.println(connectedAirports);
            System.out.println();
        }
    }
}

public class GraphAirportsDemo {
    public static void main(String[] args) {
        GraphAirports graph = new GraphAirports();

        graph.addFlight("Philadelphia", "New York");
        graph.addFlight("Philadelphia", "Denver");
        graph.addFlight("Philadelphia", "Phoenix");
        graph.addFlight("New York", "Denver");
        graph.addFlight("New York", "Phoenix");
        graph.addFlight("Denver", "San Francisco");
        graph.addFlight("Denver", "Atlanta");
        graph.addFlight("Phoenix", "Atlanta");
        graph.addFlight("San Francisco", "Orlando");
        graph.addFlight("San Francisco", "Dallas");
        graph.addFlight("Atlanta", "Charlotte");
        graph.addFlight("Orlando", "Charlotte");
        graph.addFlight("Dallas", "Charlotte");



        graph.BFS("Philadelphia");

        System.out.println("List of connections:");
        graph.printConnectedAirports();
    }
}
