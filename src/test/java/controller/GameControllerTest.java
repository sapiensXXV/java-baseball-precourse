package controller;

import domain.BaseballNumber;
import domain.NumberGenerator;
import domain.Referee;
import dto.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.*;

class GameControllerTest {

    @Test
    @DisplayName("게임 정상 실행: 오답 -> 정답 -> 종료(2) 시나리오 검증")
    void gameRunSuccessScenario() {
        // 컴퓨터의 입력을 1,2,3 으로 고정
        FixedNumberGenerator fixedGenerator = new FixedNumberGenerator("123");

        // 사용자의 입력을 순서대로 설정
        StubInputView stubInputView = new StubInputView(List.of("145", "123"), "2");

        // 출력을 캡쳐할 뷰 생성
        SpyOutputView spyOutputView = new SpyOutputView();

        // 실제 심판 객체
        Referee referee = new Referee();

        // 컨트롤러 조립
        GameController controller = new GameController(
                stubInputView,
                spyOutputView,
                fixedGenerator,
                referee
        );

        //when
        controller.run();

        // 출력된 메세지들이 예상대로 포함되어 있는지
        List<String> logs = spyOutputView.getLogs();

        assertThat(logs)
                .containsExactly(
                        "숫자 야구 게임을 시작하겠습니다.",
                        "0볼 1스트라이크", // 145 입력 결과
                        "3스트라이크", // 123 입력 결과
                        "3개의 숫자를 모두 맞히셨습니다! 게임 종료" // 정답 메세지(OutputView 구현에 따라 다를 수 있음
                );
    }

    @Test
    @DisplayName("예외 처리: 잘못된 입력 -> 에러 출력 -> 재입력 -> 성공 시나리오")
    void gameRunExceptionScenario() {
        FixedNumberGenerator fixedGenerator = new FixedNumberGenerator("123");
        // "abc" (에러발생) -> "123" (정답) -> "2" (종료)
        StubInputView stubInputView = new StubInputView(List.of("abc", "123"), "2");
        SpyOutputView spyOutputView = new SpyOutputView();
        Referee referee = new Referee();

        GameController controller = new GameController(stubInputView, spyOutputView, fixedGenerator, referee);

        //when
        controller.run();

        //then
        List<String> logs = spyOutputView.getLogs();
        assertThat(logs.toString()).contains("[ERROR]"); // 에러메세지가 출력되었는지 확인
        assertThat(logs).contains("3스트라이크"); // 결국 성공했는지 확인
    }

    /**
     * 테스트를 위한 가짜 객체 정의
     * 무조건 정해진 숫자만 반환하는 가짜 생성기
     */
    static class FixedNumberGenerator implements NumberGenerator {

        private final List<Integer> numbers;

        public FixedNumberGenerator(String input) {
            this.numbers = new ArrayList<>();
            for (String s: input.split("")) {
                numbers.add(Integer.parseInt(s));
            }
        }

        @Override
        public BaseballNumber generate() {
            return new BaseballNumber(numbers);
        }
    }

    /**
     * 미리 정해진 입력을 반환하는 테스트용 객체
     */
    static class StubInputView implements InputView {

        private final Queue<String> numberInputs;
        private final Queue<String> restartInputs;

        public StubInputView(
                List<String> numberInputs,
                String restartInputs
        ) {
            this.numberInputs = new LinkedList<>(numberInputs);
            this.restartInputs = new LinkedList<>(List.of(restartInputs));
        }

        @Override
        public String inputNumbers() {
            return numberInputs.poll(); // 큐에서 하나씩 꺼냄
        }

        @Override
        public String inputRestartCommand() {
            return restartInputs.poll();
        }
    }

    /**
     * 출력된 내용을 리스트에 저장하는 가짜 객체
     */
    static class SpyOutputView implements OutputView {

        private final List<String> logs = new ArrayList<>();

        public List<String> getLogs() {
            return logs;
        }

        @Override
        public void printResult(GameResult result) {
            // 실제 로직을 흉내내거나, 결과 객체 정보를 로그에 저장
            // 여기서는 간단히 문자로 변환해 저장한다고 가정
            String message = result.toString();
            logs.add(message);
        }

        @Override
        public void printGameSuccess() {
            logs.add("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        }

        @Override
        public void printMessage(String message) {
            logs.add(message);
        }
    }
}