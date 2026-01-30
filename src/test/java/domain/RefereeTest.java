package domain;

import dto.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RefereeTest {
    // judge 메서드를 테스트 해야함.
    // 반환되는 GameResult 인스턴스에서 결과를 추출하고,
    // 올바른 볼, 스트라이크 결과를 넘겨주는지에 대해 중점적으로 테스트한다.
    // 잘못된 입력이 주어지는 예외 케이스에 대해서는 InputConverter에서 검사하기 때문에 따로 테스트하지 않는다.

    // 입력값과 기댓값만 바뀌고 검증로직은 똑같기 때문에 파라미터화 테스트가 적합하다고 생각했습니다.

    @DisplayName("다양한 경기 결과 판정 테스트")
    @ParameterizedTest(name = "컴퓨터:{0}, 유저:{1} -> {2}볼 {3}스트라이크")
    @CsvSource({
            "123, 123, 0, 3", // 3 스트라이크
            "123, 124, 0, 2", // 2 스트라이크
            "123, 456, 0, 0", // 낫싱 (0볼 0스트라이크)
            "123, 312, 3, 0", // 3 볼
            "123, 132, 2, 1", // 2볼 1스트라이크
            "123, 415, 1, 0"  // 1볼
    })
    void judge_parameterized_test(String computerStr, String userStr, int expectedBall, int expectedStrike) {
        // given
        BaseballNumber computer = new BaseballNumber(toList(computerStr));
        BaseballNumber user = new BaseballNumber(toList(userStr));
        Referee referee = new Referee();

        // when
        GameResult result = referee.judge(computer, user);

        // then
        // 볼과 스트라이크 개수를 동시에 검증
        assertThat(result)
                .extracting("ball", "strike") // 필드명 혹은 getter 이름
                .containsExactly(expectedBall, expectedStrike);
    }

    private List<Integer> toList(String input) {
        List<Integer> numbers = new ArrayList<>();
        for (String s : input.split("")) {
            numbers.add(Integer.parseInt(s));
        }
        return numbers;
    }
}