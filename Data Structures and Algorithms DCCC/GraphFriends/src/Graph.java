/* import java.util.*;

public class Graph {

    // List of friend groups
    private Map<String, List<String>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(String u, String v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    }

    public void DFS(String startNode) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(startNode);
        visited.add(startNode);

        System.out.println("Depth-First Search (DFS):");
        while (!stack.isEmpty()) {
            String node = stack.pop();
            System.out.print(node + " ");

            List<String> neighbors = adjList.getOrDefault(node, new ArrayList<>());
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        System.out.println();
        System.out.println();
    }

    public void BFS(String startNode) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startNode);
        visited.add(startNode);

        System.out.println("Breadth-First Search (BFS):");
        while (!queue.isEmpty()) {
            String node = queue.poll();
            System.out.print(node + " ");

            List<String> neighbors = adjList.getOrDefault(node, new ArrayList<>());
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        System.out.println();
        System.out.println();
    }

    public void printFriendsList() {
        for (Map.Entry<String, List<String>> entry : adjList.entrySet()) {
            String friend = entry.getKey();
            List<String> friends = entry.getValue();

            System.out.print(friend + "'s friends: ");
            for (String friendName : friends) {
                System.out.print(friendName + " ");
            }
            System.out.println();
        }
    }
}
 */