import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArrayListTest {
    private ArrayList<Integer> randomNumbers = new ArrayList<>();
    private ArrayList<String> countryList = new ArrayList<>();

    public void randomNums() {
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            randomNumbers.add(random.nextInt(100));
        }

        System.out.println("The simple array random numbers are:");
        System.out.println(randomNumbers);

        Collections.sort(randomNumbers);

        System.out.println("The simple array numbers in ascending order are:");
        System.out.println(randomNumbers);

        System.out.println("The ArrayList random numbers unsorted are:");
        System.out.println(randomNumbers);

        Collections.sort(randomNumbers);

        System.out.println("The ArrayList random numbers sorted are:");
        System.out.println(randomNumbers);

        int additionalNumber = random.nextInt(100);
        randomNumbers.add(additionalNumber);

        System.out.println("The ArrayList random numbers add a number:");
        System.out.println(randomNumbers);

        Collections.sort(randomNumbers);

        System.out.println("The ArrayList random numbers added number sorted:");
        System.out.println(randomNumbers);
    }


    public void simpleCountries() {
        countryList.add("Germany");
        countryList.add("Brazil");
        countryList.add("Japan");
        countryList.add("China");
        countryList.add("Nigeria");

        System.out.println("\nSimple Array Listed countries:");
        System.out.println("Array: Non-Alphabetical Countries:");
        System.out.println(countryList);

        Collections.sort(countryList);

        System.out.println("Array: Alphabetical Countries:");
        System.out.println(countryList);
    }

    public void listCountries() {
        countryList.add("Germany");
        countryList.add("Italy");
        countryList.add("Spain");
        countryList.add("Russian Federation");
        countryList.add("South Korea");
        countryList.add("United States");
        countryList.add("France");
        countryList.add("Sweden");
        countryList.add("Poland");

        System.out.println("\nArrayList Countries:");
        System.out.println("ArrayList Countries: non-Alphabetical:");
        System.out.println(countryList);

        Collections.sort(countryList);

        System.out.println("ArrayList Countries: Alphabetically:");
        System.out.println(countryList);

        countryList.add("Nicaragua");

        System.out.println("\nArrayList Countries add one: Non-alphabetic:");
        System.out.println(countryList);

        Collections.sort(countryList);

        System.out.println("ArrayList Countries add one: Alphabetically:");
        System.out.println(countryList);
    }

    public static void main(String[] args) {
        ArrayListTest aS = new ArrayListTest();
        aS.randomNums();

        System.out.println("\nSimple Array listed countries:");
        aS.simpleCountries();

        System.out.println("\nArrayList countries:");
        aS.listCountries();
    }
}
