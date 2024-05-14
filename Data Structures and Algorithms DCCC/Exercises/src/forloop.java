import java.util.Scanner;
import java.util.Random;


public class forloop
{
    public static void main(String[] args)
    {
        // Setting up the sum and scanners
        Scanner userInput = new Scanner(System.in);
        int count = 0, sum = 0;
        // Setting up the loop
        for (int i = 0; i < 10; i++)
        {

            System.out.println("Enter a number to be added into the sum");
            Integer num = userInput.nextInt();

            sum = sum + num;
            if (num == -6)
            {
                break;
            }
            count++;
            System.out.println("Type in your number " + "'" + count + "'");
            System.out.println("Current sum: " + sum);
        }
    }
}
