package view;

import dto.GameResult;

public interface OutputView {

    public void printResult(GameResult result);
    public void printGameSuccess();
    public void printMessage(String message);
}
