import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import java.util.Iterator;


public class GraphStreamFriends {

    public static void main(String[] args) {
        new GraphStreamFriends();
    }
    public GraphStreamFriends() {
        // Create a new graph
        Graph graph = new SingleGraph("Friends Network");
        Node alice = graph.addNode("New York City");
        Node bob = graph.addNode("Chicago");
        Node charlie = graph.addNode("Philadelphia");
        Node david = graph.addNode("Jacksonville");
        Node eva = graph.addNode("Phoenix");
        Node frank = graph.addNode("Houston");
        Node grace = graph.addNode("Charlotte");

        // Add edges (friend relationships)
        graph.addEdge("Alice-Bob", alice, bob);
       // graph.addEdge("Alice-Charlie", alice, charlie);
        graph.addEdge("Bob-David", bob, david);
        graph.addEdge("Bob-Eva", bob, eva);
        graph.addEdge("Charlie-Frank", charlie, frank);
        graph.addEdge("Eva-Alice", eva, alice);
        graph.addEdge("Frank-Grace", frank, grace);
        graph.addEdge("Frank-David", frank, david);

        for (Node node : graph) {
            node.addAttribute("ui.label", node.getId());
            node.setAttribute("ui.style", "fill-color: red;");
        }


        // Define layout and visualization settings
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.stylesheet", styleSheet);
        // Display the graph
        graph.display();
;
    }
    public void explore(Node source) {
        Iterator<? extends Node> k = source.getBreadthFirstIterator();

        /* Breadth first showup? */

        while (k.hasNext()) {
            Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep();
        }
    }

    protected void sleep() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
    }

    protected String styleSheet =
            "node {" +
                    "   fill-color: blue;" +
                    "}" +
                    "node.marked {" +
                    "   fill-color: blue;" +
                    "}"
                    + "edge {" +
                    "  fill-color: red;" +
                    "}"
                    + "graph {" +
                    "  fill-color: lightblue;" +
                    "}";

}
