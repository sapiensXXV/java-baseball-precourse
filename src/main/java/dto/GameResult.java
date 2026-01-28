package dto;

public class GameResult {
    private final int ball;
    private final int strike;

    public GameResult(int ball, int strike) {
        this.ball = ball;
        this.strike = strike;
    }

    public boolean isNothing() {
        return ball == 0 && strike == 0;
    }

    public boolean isThreeStrike() {
        return strike == 3;
    }

    public int getBall() {
        return ball;
    }

    public int getStrike() {
        return strike;
    }
}
