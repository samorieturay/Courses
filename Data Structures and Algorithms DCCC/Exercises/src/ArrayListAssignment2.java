import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ArrayListAssignment2 {
    public static void main(String[] args) {
        // First Exercise
        System.out.println("First Exercise");
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            int x = rand.nextInt(100);
            System.out.println(x);
        }

        // Second Exercise
        System.out.println("\n" + "\n" + "Second Exercise");

        // Add countries to the ArrayList
        ArrayList<String> countries = new ArrayList<>();
        countries.add("France");
        countries.add("Germany");
        countries.add("Italy");
        countries.add("Egypt");
        countries.add("Switzerland");
        countries.add("Argentina");
        countries.add("Spain");

        System.out.println("Contents of countries ArrayList: " + countries);

        Collections.sort(countries);
        System.out.println("Contents of sorted countries ArrayList: " + countries);
        countries.add("Nigeria");
        countries.add("Sierra Leone");
        countries.add("Ghana");
        System.out.println("Contents of new countries ArrayList: " + countries);
        Collections.sort(countries);

        System.out.println("Contents of new sorted countries ArrayList: " + countries);


        // Third Exercise
        System.out.println("\n" + "\n" + "Third Exercise");

        ArrayList<Integer> list1 = new ArrayList<>();

        for (int j = 0; j < 20; j++) {
            int pick = rand.nextInt(100);
            list1.add(pick);
        }
        System.out.println("Contents of unsorted ArrayList: " + list1);
        Collections.sort(list1);
        System.out.println("Contents of sorted ArrayList: " + list1);



        int pick = rand.nextInt(100);
        int pick2 = rand.nextInt(100);
        int pick3 = rand.nextInt(100);
        list1.add(pick);
        list1.add(pick2);
        list1.add(pick3);
        System.out.println("Contents of added ArrayList: " + list1);
        Collections.sort(list1);
        System.out.println("Contents of added sorted ArrayList: " + list1);
    }
}
