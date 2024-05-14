import java.util.Scanner;

public class printnum
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num;
        while (true) {
            System.out.print("Enter a number: ");
            num = scanner.nextInt();
            System.out.println("You entered: " + num);

            if (num == -3) {
                System.out.println("Exiting the program. Number -3 was entered.");
                break;
            }
        }
    }
}
