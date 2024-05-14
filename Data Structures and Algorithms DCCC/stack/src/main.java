import java.util.Scanner;
import java.util.Stack;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int num = scanner.nextInt();

        Stack<Integer> stack = new Stack<>();

        while (num != 0) {
            int digit = num % 10;
            stack.push(digit);
            num = num / 10;
        }

        int revNum = 0;
        int power = 0;

        while (!stack.isEmpty()) {
            int digit = stack.pop();
            revNum = revNum + digit * (int) Math.pow(10, power);
            power = power + 1;
        }

        System.out.println("Reversed Number: " + revNum);
    }
}