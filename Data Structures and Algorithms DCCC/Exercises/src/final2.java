class Main {
    public static void main(String[] args) {
        int numberone = 9;
        int numbertwo = 0;

        try {
            double average = calculateAverage(numberone, numbertwo);
            System.out.println("Average: " + average);
        }
        catch (ArithmeticException e) {
            System.out.println("Error: Division by zero");
        }
    }

    public static double calculateAverage(int num1, int num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return (double) (num1 + num2) / 2;
    }
}