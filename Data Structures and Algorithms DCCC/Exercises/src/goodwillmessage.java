import java.util.Random;

public class goodwillmessage
{
    public static void main(String[] args) {
        String[] goodwillMessages = {
                "Have a great day!",
                "Stay cool and keep your head up!",
                "Things are bad but never stay that way!",
                "Love breeds more love!"
        };

        Random random = new Random();
        int randomIndex = random.nextInt(goodwillMessages.length);

        System.out.println(goodwillMessages[randomIndex]);
    }
}
