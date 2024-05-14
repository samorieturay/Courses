import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

interface BinarySearch {
    public int binarySearch(int key);

    public void printElements();

    public void remove(int index);

    public void add(int value);

    public boolean contains(int value);

    public void initializeArray();

    public void sort();
}

class BinarySearchArrayList implements BinarySearch {
    ArrayList<Integer> aL = new ArrayList<>();

    @Override
    public void initializeArray() {
        // Initializing the first 10 digits of ArrayList with random numbers 1 - 50
        for (int i = 0; i < 10; i++) {
            int num = (int) ((Math.random() * 50) + 1);
            if (contains(num))
                i--;
            else
                aL.add(num);
        }
    }

    @Override
    public boolean contains(int value) {
        return aL.contains(Integer.valueOf(value));
    }

    @Override
    public void sort() {
        Collections.sort(aL);
    }

    @Override
    public int binarySearch(int key) {
        return Collections.binarySearch(aL, key);
    }

    @Override
    public void printElements() {
        System.out.println("ArrayList of numbers:");
        for (int x : aL)
            System.out.print(x + " ");
        System.out.println();
    }

    @Override
    public void remove(int index) {
        aL.remove(index);
        sort();
        printElements();
    }

    @Override
    public void add(int value) {
        if (!contains(value)) {
            aL.add(value);
            sort();
            printElements();
        } else {
            System.out.println("Item " + value + " already exists in the list. Not added.");
        }
    }
}

class BinarySearchLinkedList implements BinarySearch {
    LinkedList<Integer> linkedList = new LinkedList<>();

    @Override
    public void initializeArray() {
        // Initializing the first 10 digits of LinkedList with random numbers 1 - 50
        for (int i = 0; i < 10; i++) {
            int num = (int) ((Math.random() * 50) + 1);
            if (contains(num))
                i--;
            else
                linkedList.add(num);
        }
    }

    @Override
    public boolean contains(int value) {
        return linkedList.contains(Integer.valueOf(value));
    }

    @Override
    public void sort() {
        Collections.sort(linkedList);
    }

    @Override
    public int binarySearch(int key) {
        int index = 0;
        for (int element : linkedList) {
            if (element == key) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public void printElements() {
        System.out.println("LinkedList of numbers:");
        for (int element : linkedList)
            System.out.print(element + " ");
        System.out.println();
    }

    @Override
    public void remove(int index) {
        linkedList.remove(index);
        sort();
        printElements();
    }

    @Override
    public void add(int value) {
        if (!contains(value)) {
            linkedList.add(value);
            sort();
            printElements();
        } else {
            System.out.println("Item " + value + " already exists in the list. Not added.");
        }
    }
}

public class BinarySearchArray implements BinarySearch {
    int[] array = new int[15];

    @Override
    public void initializeArray() {
        // Initializing the first 10 digits of the array with random numbers 1 - 50
        for (int i = 0; i < 10; i++) {
            int num = (int) ((Math.random() * 50) + 1);
            if (contains(num)) {
                i--;
            } else
                array[i] = num;
        }
    }

    @Override
    public boolean contains(int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value)
                return true;
        }
        return false;
    }

    @Override
    public void sort() {
        Arrays.sort(array);
    }

    @Override
    public int binarySearch(int key) {
        return Arrays.binarySearch(array, key);
    }

    @Override
    public void printElements() {
        System.out.println("Array of numbers:");
        for (int x : array)
            System.out.print(x + " ");
        System.out.println();
    }

    @Override
    public void remove(int index) {
        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = 0;
        sort();
        printElements();
    }

    @Override
    public void add(int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = value;
                break;
            } else {
                System.out.println("There are no more spots left to fill.");
                break;
            }
        }
        sort();
        printElements();
    }

    public void printWelcomeMessage(String mode) {
        System.out.println("\nWelcome to the Binary Search Test (" + mode + "):\n");
    }

    public void testBinarySearch(BinarySearch searchObject) {
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        while (value != -1) {
            System.out.print("\n\nEnter an integer to search (or -1 to quit): ");
            String ss = scanner.nextLine();
            value = Integer.parseInt(ss);
            if (value == -1) {
                break;
            }
            int index;
            if ((index = searchObject.binarySearch(value)) >= 0) {
                System.out.println("Value " + value + " found." + " Do you want to remove it? y/n? ");
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    searchObject.remove(index);
                }
            } else {
                System.out.println("Value " + value + " not found." + " Do you want to add it? y/n? ");
                String answer = scanner.nextLine();
                if (answer.equals("y"))
                    searchObject.add(value);
            }
        }
        System.out.println("Goodbye...");
    }

    public static void main(String[] args) {
        BinarySearchArray bsArr = new BinarySearchArray();
        bsArr.initializeArray();
        bsArr.sort();
        String mode = bsArr.getClass().getSimpleName();
        bsArr.printWelcomeMessage(mode);
        bsArr.printElements();
        bsArr.testBinarySearch(bsArr);

        BinarySearchArrayList bsArrL = new BinarySearchArrayList();
        bsArrL.initializeArray();
        bsArrL.sort();
        mode = bsArrL.getClass().getSimpleName();
        bsArrL.printWelcomeMessage(mode);
        bsArrL.printElements();
        bsArrL.testBinarySearch(bsArrL);

    }
}

        
