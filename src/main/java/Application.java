import controller.GameController;
import domain.RandomNumberGenerator;
import domain.Referee;
import view.ConsoleInputView;
import view.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        GameController gameController = new GameController(
                new ConsoleInputView(),
                new ConsoleOutputView(),
                new RandomNumberGenerator(),
                new Referee()
        );

        gameController.run();
    }
}
