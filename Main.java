import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    public static AtomicInteger valueOne = new AtomicInteger(0);
    public static AtomicInteger valueTwo = new AtomicInteger(0);
    public static AtomicInteger valueThree = new AtomicInteger(0);

    public static void main(String[] args) {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Runnable logic1 = () -> {
            for (int i = 0; i < texts.length; i++) {
                String word = texts[i];
                if (identicalLetters(word)) {
                    numberLetters(word.length());
                }
            }
        };

        Runnable logic2 = () -> {
            for (int i = 0; i < texts.length; i++) {
                String word = texts[i];
                if (alphabeticalOrder(word) && !identicalLetters(word)) {
                    numberLetters(word.length());
                }
            }
        };

        Runnable logic3 = () -> {
            for (int i = 0; i < texts.length; i++) {
                String word = texts[i];
                if (wordsReverse(word) && !identicalLetters(word)) {
                    numberLetters(word.length());
                }
            }
        };

        Thread threadOne = new Thread(logic1);
        threadOne.start();
        Thread threadTwo = new Thread(logic2);
        threadTwo.start();
        Thread threadThree = new Thread(logic3);
        threadThree.start();
        System.out.println("Красивых слов с длиной 3: " + valueOne);
        System.out.println("Красивых слов с длиной 4: " + valueTwo);
        System.out.println("Красивых слов с длиной 5: " + valueThree);

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean identicalLetters(String text) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) != text.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean wordsReverse(String text) {
        if (text.equals(new StringBuilder(text).reverse().toString())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean alphabeticalOrder(String text) {
        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) > text.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static void numberLetters(int textLength) {
        if (textLength == 3) {
            valueOne.incrementAndGet();
        } else if ((textLength == 4)) {
            valueTwo.incrementAndGet();
        } else if ((textLength == 5)) {
            valueThree.incrementAndGet();
        }
    }
}
