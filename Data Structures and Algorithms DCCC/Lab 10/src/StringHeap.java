import java.util.*;

public class StringHeap {

    public static void main(String[] args) {
        //list of strings
        List<String> stringList = Arrays.asList("Mango", "Tomato", "Banana", "Cherries", "Strawberry", "Orange");

        //Min Heap/Max Heap
        PriorityQueue<String> minHeap = new PriorityQueue<>(stringList);
        PriorityQueue<String> maxHeap = new PriorityQueue<>(stringList.size(), new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        System.out.println("Minimum string in Min Heap: " + minHeap.peek());
        System.out.println("Maximum string in Max Heap: " + maxHeap.peek());
        System.out.println("Contents of Min Heap: " + Arrays.toString(minHeap.toArray()));
        System.out.println("Contents of Max Heap: " + Arrays.toString(maxHeap.toArray()));

        minHeap.poll();
        maxHeap.poll();

        System.out.println("\nContents of Min Heap after removal: " + Arrays.toString(minHeap.toArray()));
        System.out.println("Contents of Max Heap after removal: " + Arrays.toString(maxHeap.toArray()));
        minHeap.addAll(Arrays.asList("Apple", "Blueberry"));
        maxHeap.addAll(Arrays.asList("Pear", "Grapefruit"));

        System.out.println("\nContents of Min Heap after adding items back: " + Arrays.toString(minHeap.toArray()));
        System.out.println("Contents of Max Heap after adding items back: " + Arrays.toString(maxHeap.toArray()));
    }
}
