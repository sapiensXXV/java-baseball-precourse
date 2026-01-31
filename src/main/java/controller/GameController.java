package controller;

import domain.NumberGenerator;
import dto.GameResult;
import domain.BaseballNumber;
import utils.InputConverter;
import domain.RandomNumberGenerator;
import domain.Referee;
import view.ConsoleOutputView;
import view.InputView;
import view.OutputView;


public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator randomNumberGenerator;
    private final Referee referee;

    public GameController(
            InputView inputView,
            OutputView outputView,
            NumberGenerator randomNumberGenerator,
            Referee referee
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.randomNumberGenerator = randomNumberGenerator;
        this.referee = referee;
    }

    public void run() {
        System.out.println("숫자 야구 게임을 시작합니다."); // 게임 시작 문구 (선택 사항)

        // 게임 전체 반복 (재시작 로직)
        while (true) {
            // 1. 컴퓨터 숫자 생성
            BaseballNumber computerNumber = randomNumberGenerator.generate();

            // 2. 한 판 플레이 (3스트라이크 맞출 때까지 반복)
            play(computerNumber);

            // 3. 게임 종료 후 재시작 여부 확인
            // "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."
            String restartCommand = inputView.inputRestartCommand();

            if (restartCommand.equals("2")) {
                break; // while문 탈출 -> 프로그램 종료
            }
            // 1번이면 while문 처음으로 돌아가서 새 숫자 생성
        }
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
