import java.util.Scanner;
import java.util.Stack;

public class SentenceReverser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sentences (type 'exit' to end):");

        while (scanner.hasNext()) {
            String sentence = scanner.nextLine();
            if (sentence.equalsIgnoreCase("exit")) {
                break;
            }

            String reversedSentence = reverse(sentence);
            System.out.println("Reversed Sentence: " + reversedSentence);

        }
    }

    public static String reverse(String sentence) {
        String reversed = "";
        Stack<String> stack = new Stack<>();
        Scanner scanner = new Scanner(sentence);

        while (scanner.hasNext()) {
            String word = scanner.next();
            if (word.endsWith(".")) {
                stack.push(word.substring(0, word.length() - 1));
                processSentence(scanner, stack, reversed);
            } else {
                stack.push(word);
            }
        }
        return reversed;
    }

    public static void processSentence(Scanner scanner, Stack<String> stack, String reversed) {
        while (!stack.isEmpty()) {
            String poppedWord = stack.pop();
            poppedWord = poppedWord.replace(".", "");
            String firstLetter = poppedWord.substring(0, 1);
            poppedWord = firstLetter.toUpperCase() + poppedWord.substring(1).toLowerCase();
            reversed += poppedWord + " ";
        }
        reversed = reversed.trim() + ".";
        System.out.println(reversed);
    }
}
