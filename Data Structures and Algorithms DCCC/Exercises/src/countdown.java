import java.util.Timer;
import java.util.TimerTask;

public class countdown
{
    public static void main(String[] args) {
        int[] countdownNumbers = { 2, 4, 6, 8 };

        Timer timer = new Timer();
        for (int i = 0; i < countdownNumbers.length; i++) {
            int countdownSeconds = countdownNumbers[i];
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println(countdownSeconds);
                }
            }, i * 1000); // Delay each timer task by i seconds
        }

        int finalDelay = countdownNumbers.length * 1000; // Delay for the final message after the last countdown
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Who do we appreciate!");
                timer.cancel(); // Stop the timer after printing the final message
            }
        }, finalDelay);
    }
}
