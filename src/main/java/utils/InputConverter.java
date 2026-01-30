package utils;

import java.util.ArrayList;
import java.util.List;

import static constant.ErrorMessage.*;

public class InputConverter {

    private InputConverter() {}

    public static List<Integer> convertStringToIntegerList(String input) {
        // null 및 공백처리
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(NOT_NUMBER);
        }

        String trimmedInput = input.trim();

        // 길이검증
        validateLength(trimmedInput);
        List<Integer> numbers = new ArrayList<>();

        for (char character: trimmedInput.toCharArray()) {
            validateDigit(character);
            int number = Character.getNumericValue(character);
            validateRange(number);
            numbers.add(number);
        }
        return numbers;
    }

    private static void validateLength(String input) {
        if (input.length() != 3) {
            throw new IllegalArgumentException(OUT_OF_RANGE);
        }
    }

    private static void validateDigit(char number) {
        if (!Character.isDigit(number)) {
            throw new IllegalArgumentException(OUT_OF_RANGE);
        }
    }

    private static void validateRange(int number) {
        if (number == 0) {
            throw new IllegalArgumentException(OUT_OF_RANGE);
        }
    }


}
