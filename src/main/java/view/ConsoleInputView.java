package view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private static final String INPUT_MESSAGE = "숫자를 입력해 주세요 : ";
    private static final String RESTART_MESSAGE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private final Scanner scanner;

    public ConsoleInputView() {
        this.scanner = new Scanner(System.in);
    }

    public String inputNumbers() {
        System.out.println(INPUT_MESSAGE);

        String input = scanner.nextLine();

        return input.trim();
    }

    public String inputRestartCommand() {
        System.out.println(RESTART_MESSAGE); // 안내 문구 출력
        return scanner.nextLine().trim();    // 입력받은 값(1 또는 2) 반환
    }
}
