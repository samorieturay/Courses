import java.util.Scanner;
import java.util.Random;

public class whileloop
{
    public static void main(String[] args)
    {
        //Setting up the user input
        Scanner userInput = new Scanner(System.in);
        int count = 1, product = 1;
        System.out.println("Enter a number");

        // Setting up loop
        Integer num = userInput.nextInt();
        while (num != 0)
        {

            System.out.println("Enter your next number to be multiplied with the previous numbers " + "("+ count +")");
            Integer num1 = userInput.nextInt();
            num = num1;
            if (count == 1)
            {
                product = num1 * product * num1;

            }
            else
            {
                product = num1 * product;

            }
            System.out.println("Current product: " + product);
            count++; // lets user know how many times the statement has looped
        }
    }
}
