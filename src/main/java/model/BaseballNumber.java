package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static model.Constant.*;

/**
 * 숫자 3개를 담고 있는 객체
 * Computer, Referee, View 등의 객체에서 사용됩니다.
 */
public class BaseballNumber {
    // 생성한 숫자라는 것은 숫자를 픽한것 뿐만 아니라 순서 정보도 가지고 있어야한다.
    private final List<Integer> numbers;

    public BaseballNumber(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        // TODO: 검증로직 작성
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException("[ERROR] 숫자는 3자리여야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        // TODO: 검증로직 작성
        for (int number: numbers) {
            if (number < NUMBER_MIN_RANGE || number > NUMBER_MAX_RANGE) {
                throw new IllegalArgumentException("[ERROR] 숫자는 1부터 9까지의 수여야 합니다.");
            }
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        // TODO: 검증로직 작성
        Set<Integer> nonDuplicateNumbers = new HashSet<>(numbers);
        if (nonDuplicateNumbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException("[ERROR] 숫자는 중복될 수 업습니다.");
        }
    }

    // 유틸리티 메서드들

    //특정 위치의 숫자를 가져오기
    public int getNumber(int index) {
        return numbers.get(index);
    }

    // 특정 숫자가 포함되어 있는지 확인
    public boolean contain(int number) {
        return numbers.contains(number);
    }

    // TODO: 불변리스트 반환 메서드 작성
    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
