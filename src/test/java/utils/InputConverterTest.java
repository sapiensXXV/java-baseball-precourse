package utils;

import constant.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class InputConverterTest {

    @Test
    @DisplayName("정상 입력: 3자리 숫자가 입력되면 리스트 [1,2,3]을 반환한다.")
    void convertSuccess() {
        // given
        String input = "123";

        // when
        List<Integer> result = InputConverter.convertStringToIntegerList(input);

        // then
        assertThat(result)
                .hasSize(3)
                .containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("예외 발생: 입력값의 길이가 3자리가 아니면(2자리) 예외가 발생한다.")
    void convertFailShortLength() {
        // given
        String input = "12";

        // when & then
        assertThatThrownBy(() -> InputConverter.convertStringToIntegerList(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_SIZE);
    }

    @Test
    @DisplayName("예외 발생: 입력값의 길이가 3자리가 아니면(4자리) 예외가 발생한다.")
    void convertFailLongLength() {
        // given
        String input = "1234";

        // when & then
        assertThatThrownBy(() -> InputConverter.convertStringToIntegerList(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_SIZE);
    }

    @Test
    @DisplayName("예외 발생: 0이 포함되면 예외가 발생한다.")
    void convertFailContainsZero() {
        // given
        String input = "012";

        // when & then
        assertThatThrownBy(() -> InputConverter.convertStringToIntegerList(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OUT_OF_RANGE);
    }

    @Test
    @DisplayName("예외 발생: 숫자가 아닌 문자가 포함되면 예외가 발생한다.")
    void convertFailNonNumeric() {
        // given
        String input = "1a3";

        // when & then
        assertThatThrownBy(() -> InputConverter.convertStringToIntegerList(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OUT_OF_RANGE);

    }

    @Test
    @DisplayName("엣지 케이스: 앞뒤 공백은 제거하고 숫자만 3개라면 통과한다.")
    void convertSuccessWithWhitespace() {
        // given
        String input = " 123 ";

        // when & then
        List<Integer> result = InputConverter.convertStringToIntegerList(input);
        assertThat(result)
                .hasSize(3)
                .containsExactly(1, 2, 3);
    }

}