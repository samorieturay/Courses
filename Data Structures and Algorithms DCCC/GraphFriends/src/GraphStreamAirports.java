import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class GraphStreamAirports {

    public static void main(String[] args) {
        new GraphStreamAirports();
    }

    public GraphStreamAirports() {
        // Create a new graph
        Graph graph = new SingleGraph("Flight Network");

        // Add nodes (cities)
        Node sfo = graph.addNode("Philadelphia"); //Philadelphia
        Node lax = graph.addNode("New York"); //New York
        Node den = graph.addNode("Denver"); // Denver
        Node phx = graph.addNode("Phoenix"); //Phoenix
        Node sea = graph.addNode("San Francisco"); //San Francisco
        Node atl = graph.addNode("Atlanta");//Atlanta
        Node dfw = graph.addNode("Orlanda"); // Orlanda
        Node ord = graph.addNode("Dallas"); // Dallas
        Node mia = graph.addNode("Charlotte"); // Charlotte

        // Add edges (flight connections)
        graph.addEdge("Philadelphia - New York", sfo, lax);
        graph.addEdge("Philadelphia - Denver", sfo, den);
        graph.addEdge("Philadelphia - Phoenix", sfo, phx);
        graph.addEdge("New York - Denver", lax, den);
        graph.addEdge("New York - Phoenix", lax, phx);
        graph.addEdge("Denver - San Francisco", den, sea);
        graph.addEdge("Denver - Atlanta", den, atl);
        graph.addEdge("Phoenix - Atlanta", phx, atl);
        graph.addEdge("San Francisco - Orlanda", sea, dfw);
        graph.addEdge("San Francisco - Dallas", sea, ord);
        graph.addEdge("Atlanta - Charlotte", atl, mia);
        graph.addEdge("Orlanda - Charlotte", dfw, mia);
        graph.addEdge("Dallas - Charlotte", ord, mia);

        // Set visual attributes for nodes
        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
            node.setAttribute("ui.style", "fill-color: yellow;");
        }

        // Define layout and visualization settings
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.stylesheet", styleSheet);

        // Display the graph
        graph.display();
    }

    protected String styleSheet =
            "node {" +
                    "   fill-color: blue;" +
                    "}" +
                    "edge {" +
                    "  fill-color: red;" +
                    "}" +
                    "graph {" +
                    "  fill-color: lightblue;" +
                    "}";
}
