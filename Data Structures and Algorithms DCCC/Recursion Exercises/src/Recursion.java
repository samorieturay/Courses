public class Recursion {
    public int sum(int n) {
        if (n <= 0) {
            return 0; // Stop condition
        } else {
            return n + sum(n - 1); // Recursive call
        }
    }

    public int factorial(int n) {
        if (n == 0) {
            return 1; // Stop condition
        } else {
            return n * factorial(n - 1); // Recursive call
        }
    }

    public int powerOf10(int n) {
        if (n == 0) {
            return 1; // Stop condition
        } else {
            return 10 * powerOf10(n - 1); // Recursive call
        }
    }

    public int powerOfN(int x, int p) {
        if (p == 0) {
            return 1; // Stop condition
        } else {
            return x * powerOfN(x, p - 1); // Recursive call
        }
    }

    public int bunnyEars(int n) {
        if (n == 0) {
            return 0; // Stop condition
        } else {
            return 2 + bunnyEars(n - 1); // Recursive call
        }
    }

    public static void main(String[] args) {
        Recursion recursion = new Recursion();

        System.out.println("Sum of 6: " + recursion.sum(6));
        System.out.println("Factorial of 5: " + recursion.factorial(5));
        System.out.println("10 to the power of 3: " + recursion.powerOf10(3));
        System.out.println("3 to the power of 4: " + recursion.powerOfN(3, 4));
        System.out.println("Number of bunny ears for 7 bunnies: " + recursion.bunnyEars(7));
    }
}
