import java.util.Scanner;
import java.util.Random;

public class arrayandmethod
{
    public static void mean(int[] arr)
    {
        int sum=0;
        for ( int i=0;i < arr.length ;i++ )
        {
            sum += arr[i];
        }
        double average = sum / arr.length;
        System.out.println("Average of the array is: " + average);
    }
    public static void main(String[] args)
    {
        int[] array1= {1,2,3,4,5,6,7,8,9};
        mean(array1);
    }
}
