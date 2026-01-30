package domain;

import constant.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BaseballNumberTest {

    // BaseballNumber 객체를 생성하면서 검증로직도 함께 수행되기 때문에
    // 정상적으로 생성되는지, 검증로직이 제대로 동작하는지 테스트하면 좋을 것 같습니다.

    @Test
    @DisplayName("성공: 정상적으로 객체를 생성")
    void success() {
        BaseballNumber numbers = new BaseballNumber(List.of(1, 2, 3));
        assertThat(numbers.getNumbers())
                .hasSize(3)
                .containsExactly(1, 2, 3);
    }

    // 정상적인 사이즈가 아닌경우, 중복된 원소가 있는경우, 1~9 사이의 범위를 벗어난 경우 예외가 발생합니다.
    @Test
    @DisplayName("예외: 사이즈가 4개인 야구번호 생성 시도")
    void failWrongSize() {
        assertThatThrownBy(() -> new BaseballNumber(List.of(1, 2, 3, 4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_SIZE);
    }

    @Test
    @DisplayName("예외: 중복된 원소로 야구번호 생성 시도")
    void failDuplicateNumber() {
        assertThatThrownBy(() -> new BaseballNumber(List.of(1, 1, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_NUMBER);
    }

    @Test
    @DisplayName("예외: 1~9 이외의 범위로 야구번호 생성 시도")
    void failWrongRange() {
        assertThatThrownBy(() -> new BaseballNumber(List.of(0, 1, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OUT_OF_RANGE);
    }

}