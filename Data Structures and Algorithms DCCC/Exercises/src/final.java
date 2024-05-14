import java.util.Scanner;

// Program with a selection statement
class SelectionStatementExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        scanner.close();

        if (number % 2 == 0) {
            System.out.println(number + " is even.");
        } else {
            System.out.println(number + " is odd.");
        }
    }
}

// Inheritance
class Parent {
    private int parentVariable;

    public Parent(int parentVariable) {
        this.parentVariable = parentVariable;
    }

    public int getParentVariable() {
        return parentVariable;
    }

    public void setParentVariable(int parentVariable) {
        this.parentVariable = parentVariable;
    }
}

class Child extends Parent {
    private int childVariable;

    public Child(int parentVariable, int childVariable) {
        super(parentVariable);
        this.childVariable = childVariable;
    }

    public int getChildVariable() {
        return childVariable;
    }

    public void setChildVariable(int childVariable) {
        this.childVariable = childVariable;
    }
}

// 3. Program to print a multidimensional array
class MultidimensionalArrayExample {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// 4. Program to calculate the average of three numbers
class AverageCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter three numbers: ");
        double num1 = scanner.nextDouble();
        double num2 = scanner.nextDouble();
        double num3 = scanner.nextDouble();
        scanner.close();

        double average = calculateAverage(num1, num2, num3);
        System.out.println("Average: " + average);
    }

    public static double calculateAverage(double num1, double num2, double num3) {
        return (num1 + num2 + num3) / 3;
    }
}

// 5. Error handling program with a custom error message
class Main2 {
    public static void main(String[] args) {
        int numberone = 9;
        int numbertwo = 0;

        try {
            System.out.println(numberone / numbertwo);
        } catch (Exception e) {
            System.out.println("An error occurred: Division by zero");
        }
    }
}