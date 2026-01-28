package domain;

import constant.GameConfig;
import dto.GameResult;

public class Referee {

    public GameResult judge(BaseballNumber computer, BaseballNumber user) {
        int balls = 0;
        int strikes = 0;

        for (int i = 0; i < GameConfig.NUMBERS_SIZE; i++) {
            int userNumber = user.getNumber(i);

            // 스트라이크인지 확인 (위치와 숫자가 모두 같음);
            if (computer.getNumber(i) == userNumber) {
                strikes++;
                continue; // 볼 확인은 건너뜀
            }
            if (computer.contain(userNumber)) {
                balls++;
            }
        }

        return new GameResult(balls, strikes);
    }
}
