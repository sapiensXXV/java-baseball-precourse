package controller;

import dto.GameResult;
import model.BaseballNumber;
import model.InputConverter;
import model.RandomNumberGenerator;
import model.Referee;
import view.InputView;
import view.OutputView;


public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RandomNumberGenerator randomNumberGenerator;
    private final Referee referee;

    public GameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.randomNumberGenerator = new RandomNumberGenerator();
        this.referee = new Referee();
    }

    public void run() {
        BaseballNumber computerNumber = randomNumberGenerator.generate();

        System.out.println("컴퓨터 숫자 생성 완료 (테스트용): " + computerNumber.getNumbers());
    }

    private void play(BaseballNumber computerNumber) {
        while(true) {
            try {
                String inputString = inputView.inputNumbers();
                BaseballNumber userNumber = new BaseballNumber(InputConverter.convertStringToIntegerList(inputString));
                GameResult result = referee.judge(computerNumber, userNumber);
                outputView.printResult(result);

                if (result.isThreeStrike()) {
                    outputView.printGameSuccess();
                    break;
                }
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

}
