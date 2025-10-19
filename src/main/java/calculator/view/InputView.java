package calculator.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    private static final String START_MESSAGE = "덧셈할 문자열을 입력해 주세요.";

    public static String readInput() {
        System.out.println(START_MESSAGE);
        return readLine();
    }

    public static String readNextLine() {
        return readLine();
    }
}
