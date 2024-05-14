import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Scanner;

public class DoublyLinkedListPhoneBook {

    class Node {
        PhonebookData data;
        Node previous;
        Node next;

        public Node(PhonebookData data) {
            this.data = data;
        }
    }

    Node head, tail = null;

    public void addNode(PhonebookData data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = tail = newNode;
            head.previous = null;
            tail.next = null;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
            tail.next = null;
        }
    }

    public void display() {
        Node current = head;
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Nodes of doubly linked list: ");
        while (current != null) {
            System.out.println(current.data); // Assuming PhonebookData has overridden toString()
            current = current.next;
        }
    }

    public SortedSet<PhonebookData> search(String searchItem) {
        SortedSet<PhonebookData> sortedSet = new TreeSet<>();
        Node current = head;

        while (current != null) {
            if (current.data.getName().toLowerCase().contains(searchItem.toLowerCase().strip())
                    || current.data.getMobilePhone().contains(searchItem)) {
                sortedSet.add(current.data);
            }
            current = current.next;
        }
        return sortedSet;
    }

    public SortedSet<PhonebookData> searchTailFirst(String searchItem) {
        SortedSet<PhonebookData> sortedSet = new TreeSet<>();
        Node current = tail;

        while (current != null) {
            if (current.data.getName().toLowerCase().contains(searchItem.toLowerCase().strip())
                    || current.data.getMobilePhone().contains(searchItem)) {
                sortedSet.add(current.data);
            }
            current = current.previous;
        }
        return sortedSet;
    }

    public static void main(String[] args) {
        DoublyLinkedListPhoneBook dList = new DoublyLinkedListPhoneBook();

        // Add nodes to the list
        dList.addNode(new PhonebookData("Miqun Robinson", "908-239-2822"));
        dList.addNode(new PhonebookData("Michael Davis", "443-904-9432"));
        dList.addNode(new PhonebookData("Jackson Evers", "484-904-0122"));
        dList.addNode(new PhonebookData("Allison Whitehead", "650-455-2782"));
        dList.addNode(new PhonebookData("David Lamm", "484-885-2612"));
        dList.addNode(new PhonebookData("Zachary Whitehead", "484-223-7824"));

        // Display the nodes present in the list
        dList.display();

        Scanner scanner = new Scanner(System.in);

        // Head-first search
        System.out.println("Starting head-first search test...");
        System.out.print("Enter search item (or q to quit): ");
        String searchItem = scanner.nextLine();

        while (!searchItem.equals("q")) {
            SortedSet<PhonebookData> sortedSet = dList.search(searchItem);

            if (!sortedSet.isEmpty()) {
                for (PhonebookData data : sortedSet) {
                    System.out.println(data);
                }
            } else {
                System.out.println("No search results found...");
            }

            System.out.print("\nEnter search item (or q to quit): ");
            searchItem = scanner.nextLine();
        }

        // Tail-first search
        System.out.println("\nStarting tail-first search test...");
        System.out.print("Enter search item (or q to quit): ");
        searchItem = scanner.nextLine();

        while (!searchItem.equals("q")) {
            SortedSet<PhonebookData> sortedSet = dList.searchTailFirst(searchItem);

            if (!sortedSet.isEmpty()) {
                for (PhonebookData data : sortedSet) {
                    System.out.println(data);
                }
            } else {
                System.out.println("No search results found...");
            }

            System.out.print("\nEnter search item (or q to quit): ");
            searchItem = scanner.nextLine();
        }
    }
}
