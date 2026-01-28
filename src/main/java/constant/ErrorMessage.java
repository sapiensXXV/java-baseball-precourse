package constant;

public class ErrorMessage {
    public static final String ERROR_PREFIX = "[ERROR] ";
    public static final String NOT_NUMBER = ERROR_PREFIX + "숫자만 입력해주세요";
    public static final String OUT_OF_RANGE = ERROR_PREFIX + "숫자는 1부터 9까지의 수여야 합니다.";
    public static final String DUPLICATE_NUMBER = ERROR_PREFIX + "숫자는 중복될 수 없습니다.";
    public static final String INVALID_SIZE = ERROR_PREFIX + "숫자는 3자리여야 합니다.";

    private ErrorMessage() { } // 인스턴스 생성 방지
}