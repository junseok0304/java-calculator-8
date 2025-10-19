package calculator.view;

public class OutputView {
    private static final String RESULT_PRE_MESSAGE = "결과 : ";

    public static void print(int result) {
        System.out.println(RESULT_PRE_MESSAGE + result);
    }
}
