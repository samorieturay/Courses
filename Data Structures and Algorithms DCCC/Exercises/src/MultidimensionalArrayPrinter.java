import java.util.Scanner;

public class MultidimensionalArrayPrinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the dimensions of the array from the user
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt();

        // Create the multidimensional array
        int[][] array = new int[rows][columns];

        // Get values for the array from the user
        System.out.println("Enter the elements of the array:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Element at position (" + i + ", " + j + "): ");
                array[i][j] = scanner.nextInt();
            }
        }

        // Print the array
        System.out.println("The multidimensional array is:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}