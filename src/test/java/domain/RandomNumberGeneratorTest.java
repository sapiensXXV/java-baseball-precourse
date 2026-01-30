package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RandomNumberGeneratorTest {

    // 3개의 숫자가 담긴 BaseballNumber를 반환해주는지만 간단하게 테스트하면 좋을 것 같습니다.
    @Test
    @DisplayName("랜덤 숫자 3개 생성")
    void testGenerateRandomThreeNumber() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        BaseballNumber numbers = generator.generate();

        assertThat(numbers.getNumbers())
                .isNotNull()
                .hasSize(3);
    }

}