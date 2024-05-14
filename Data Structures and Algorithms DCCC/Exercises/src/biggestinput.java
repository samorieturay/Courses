import java.util.Scanner;
import java.util.Random;

public class biggestinput
{
    public static void main(String[] args)
    {
        // Asking player to input 3 Numbers
        Scanner playerInput = new Scanner(System.in);
        System.out.println("Enter 3 numbers");

        double a = playerInput.nextDouble();
        double b = playerInput.nextDouble();
        double c = playerInput.nextDouble();

        // Setting up the If/Else statement to find and print largest number
        if (a>b && a>c)
        {
            System.out.println(a + " is your largest number");
        }
        else if (b>a && b>c)
        {
            System.out.println(b + " is your largest number");
        }
        else if (c>a && c>b)
        {
            System.out.println(c + " is your largest number");
        }
    }
}
