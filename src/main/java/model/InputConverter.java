package model;

import java.util.ArrayList;
import java.util.List;

public class InputConverter {

    private InputConverter() {}

    public static List<Integer> convertStringToIntegerList(String input) {
        // 사용자의 공백 입력 실수 방지
        String trimmedInput = input.trim();

        List<Integer> numbers = new ArrayList<>();

        for (char character : trimmedInput.toCharArray()) {
            if (!Character.isDigit(character)) {
                throw new IllegalArgumentException("[ERROR] 숫자만 입력해주세요");
            }

            int number = Character.getNumericValue(character);
            numbers.add(number);
        }

        return numbers;
    }
}
