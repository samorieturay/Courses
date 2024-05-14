import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;


public class ArraylistAssignment
{
    public static void main(String[] args)
    {
        // First Exercise
        System.out.println("First Exercise");
        Random rand = new Random();

        for (int i = 0; i < 20; i++)
        {
            int x = rand.nextInt(100);
            System.out.println(x);
        }
        // Second Exercise

        System.out.println("\n" + "\n" + "Second Exercise");
        String [] array =
                {
                        "Egypt", "Switzerland", "Argentina", "Spain", "Portugal", "Luxemburg", "Indonesia"
                };

        System.out.println("unsorted array is " + Arrays.toString(array));
        Arrays.sort(array);
        System.out.println("sorted array is " + Arrays.toString(array));


        // Third Exercise
        System.out.println("\n" + "\n" +  "Third Exercise");

        ArrayList list1 = new ArrayList();
        for (int j = 0; j < 20; j++)
        {
            int pick = rand.nextInt(100);
            list1.add(pick);
        }
        System.out.println("Contents of unsorted Arraylist: " + list1);
                Collections.sort(list1);
        System.out.println("Contents of sorted Arraylist: " + list1);
        int pick = rand.nextInt(100);
        int pick2 = rand.nextInt(100);
        int pick3 = rand.nextInt(100);
        list1.add(pick);
        list1.add(pick2);
        list1.add(pick3);
        System.out.println("Contents of added Arraylist: " + list1);
        Collections.sort(list1);
        System.out.println("Contents of added sorted Arraylist: " + list1);


    }
}
