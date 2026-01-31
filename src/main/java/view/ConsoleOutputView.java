package view;

import dto.GameResult;

public class ConsoleOutputView implements OutputView {

    public void printResult(GameResult result) {
        if (result.isNothing()) {
            System.out.println("낫싱");
            return;
        }

        StringBuilder sb = new StringBuilder();
        if (result.getBall() > 0) {
            sb.append(result.getBall()).append("볼 ");
        }
        if (result.getStrike() > 0) {
            sb.append(result.getStrike()).append("스트라이크 ");
        }

        System.out.println(sb.toString().trim());
    }

    public void printGameSuccess() {
        System.out.println("3개의 숫자를 모두 맞추셨습니다! 게임 종료");
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
