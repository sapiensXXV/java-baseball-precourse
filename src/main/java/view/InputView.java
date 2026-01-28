package view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_MESSAGE = "숫자를 입력해 주세요 : ";
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String inputNumbers() {
        System.out.println(INPUT_MESSAGE);

        String input = scanner.nextLine();

        return input.trim();
    }
}
