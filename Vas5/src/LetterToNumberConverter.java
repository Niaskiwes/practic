import java.util.Scanner;
public class LetterToNumberConverter {
    private static final String RUSSIAN_ALPHABET = "àáâãäåæçèéêëìíîïğñòóôõö÷øùúûüışÿ";
    private static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ââåäèòå òåêñò:");
            String input = scanner.nextLine();
            StringBuilder lettersOutput = new StringBuilder();
            StringBuilder numbersOutput = new StringBuilder();

            for (char c : input.toLowerCase().toCharArray()) {
                int index = RUSSIAN_ALPHABET.indexOf(c);
                if (index >= 0) {
                    int number = index + 1;
                    lettersOutput.append(c).append("\t");
                    numbersOutput.append(number).append("\t");
                } else {
                    index = ENGLISH_ALPHABET.indexOf(c);
                    if (index >= 0) {
                        int number = index + 1;
                        lettersOutput.append(c).append("\t");
                        numbersOutput.append(number).append("\t");
                    } else {
                        lettersOutput.append(" \t");
                        numbersOutput.append(" \t");
                    }
                }
            }
            System.out.println("Áóêâû:\n" + lettersOutput.toString().trim());
            System.out.println("Íîìåğà:\n" + numbersOutput.toString().trim());
        }
    }
}
