import controller.GameController;
import domain.RandomNumberGenerator;
import domain.Referee;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        GameController gameController = new GameController(
                new InputView(),
                new OutputView(),
                new RandomNumberGenerator(),
                new Referee()
        );

        gameController.run();
    }
}
