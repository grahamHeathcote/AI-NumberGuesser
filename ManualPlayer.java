package AINumberGuesser;
import java.util.Scanner;

public class ManualPlayer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumberGame numberGame = new NumberGame();
        System.out.println(numberGame.playGame(scanner.nextInt()));
        scanner.close();
    }
}
